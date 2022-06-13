package com.pusilkom.base.service;

import com.pusilkom.base.dto.JenisOutputDTO;
import com.pusilkom.base.dto.OutputDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.JenisOutputMapper;
import com.pusilkom.base.model.mapper.OutputMapper;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by ars on 04/02/2020.
 */

@Service
@Transactional
public class OutputService {
    @Autowired
    OutputMapper outputMapper;
    @Autowired
    JenisOutputMapper jenisOutputMapper;
    @Autowired
    LogAktivitasService logAktivitasService;

    public Output getOutputById(Integer id){
        return outputMapper.selectByPrimaryKey(id);
    }

    public void deleteFile(Integer id) {
        Output output = outputMapper.selectByPrimaryKey(id);
        if (output.getPathFile() != null) {
            File file = new File(output.getPathFile());
            file.delete();
        }
    }

    public Output toOutput(OutputDTO outputDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(outputDTO, Output.class);
    }

    public void save(OutputDTO outputDTO) {
        logAktivitasService.addLog(outputDTO.getUsername(), "POST", outputDTO.toString(), "/profil-dosen");
        outputMapper.insert(toOutput(outputDTO));
    }

    public void update(OutputDTO outputDTO) {
        logAktivitasService.addLog(outputDTO.getUsername(), "PUT", outputDTO.toString(), "/profil-dosen/" + outputDTO.getId());
        outputMapper.updateByPrimaryKeySelective(toOutput(outputDTO));
    }

    public void delete(Integer id, String username) {
        outputMapper.deleteByPrimaryKey(id);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/profil-dosen/" + id);
    }

    public List<JenisOutput> getAllJenisOutput(String kelompokOutput) {
        JenisOutputExample ex = new JenisOutputExample();
        if(kelompokOutput != "") ex.createCriteria().andKelompokOutputLikeInsensitive(kelompokOutput);
        return jenisOutputMapper.selectByExample(ex);
    }

    public String getFile(Integer id) {
        Output dokumen = outputMapper.selectByPrimaryKey(id);
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(new File(dokumen.getPathFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encodedFile = "data:application/pdf;base64," + Base64.getEncoder().encodeToString(fileContent);
        return encodedFile;
    }

    public String pathToFileName(String path){
        String[] parts = path.split("\\\\");
        return parts[parts.length - 1];
    }

    public List<JenisOutputDTO> getInfoJumlahOutputPerJenisOutput(Integer idUser){
        List<JenisOutput> allJenis = this.getAllJenisOutput("");
        List<JenisOutputDTO> jenisOutputDTOList = new ArrayList<>();
        for(JenisOutput jenis : allJenis){
            List<Output> listOutput = outputMapper.getOutputByParam(idUser, jenis.getId());
            if(listOutput.size() > 0) {
                JenisOutputDTO jenisOutputDTO = new JenisOutputDTO();
                jenisOutputDTO.setId(jenis.getId());
                jenisOutputDTO.setNama(jenis.getNama());
                jenisOutputDTO.setJumlahOutput(listOutput.size());
                jenisOutputDTOList.add(jenisOutputDTO);
            }
        }
        return jenisOutputDTOList;
    }
}

