package com.pusilkom.base.service;

import com.pusilkom.base.dto.KontribusiMitraDTOGroupByMitraPenelitian;
import com.pusilkom.base.dto.MitraPenelitianDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.JenisMitraMapper;
import com.pusilkom.base.model.mapper.KontribusiMitraMapper;
import com.pusilkom.base.model.mapper.MitraPenelitianMapper;
import com.pusilkom.base.util.CommonUtil;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * Created by ars on 05/05/2020.
 */

@Service
@Transactional
public class MitraPenelitianService {
    @Autowired
    MitraPenelitianMapper mitraPenelitianMapper;
    @Autowired
    KontribusiMitraMapper kontribusiMitraMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    JenisMitraMapper jenisMitraMapper;
    @Autowired
    OutputService outputService;
    @Autowired
    CommonUtil commonUtil;

    public List<MitraPenelitianDTO> getListMitraPenelitianDTO(Integer idPenelitian){
        List<MitraPenelitianDTO> mitraPenelitianDTOList = mitraPenelitianMapper.getListMitraPenelitianDTO(idPenelitian);
        for (MitraPenelitianDTO mitra: mitraPenelitianDTOList) {
            if(mitra.getFilePath() != null){
                mitra.setFileName(outputService.pathToFileName(mitra.getFilePath()));
            }
            KontribusiMitraExample kontriEx = new KontribusiMitraExample();
            kontriEx.createCriteria().andIdMitraPenelitianEqualTo(mitra.getId());
            kontriEx.setOrderByClause("tahun asc");
            List<KontribusiMitra> listKontribusi = kontribusiMitraMapper.selectByExample(kontriEx);
            mitra.setListKontri(listKontribusi);
        }
        return mitraPenelitianDTOList;
    }

    public MitraPenelitian toMitraPenelitian(MitraPenelitianDTO mitraPenelitianDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mitraPenelitianDTO, MitraPenelitian.class);
    }

    public Integer save(MitraPenelitianDTO mitraPenelitianDTO) {
        logAktivitasService.addLog(mitraPenelitianDTO.getUsername(), "POST", mitraPenelitianDTO.toString(), "/penelitian/" + mitraPenelitianDTO.getIdPenelitian() + "/mitra-penelitian");
        MitraPenelitian mitra = toMitraPenelitian(mitraPenelitianDTO);
        mitraPenelitianMapper.insert(mitra);
        return mitra.getId();
    }

    public void update(MitraPenelitianDTO mitraPenelitianDTO) {
        logAktivitasService.addLog(mitraPenelitianDTO.getUsername(), "PUT", mitraPenelitianDTO.toString(), "/penelitian/" + mitraPenelitianDTO.getIdPenelitian() + "/mitra-penelitian");
        MitraPenelitianExample ex = new MitraPenelitianExample();
        ex.createCriteria().andIdEqualTo(mitraPenelitianDTO.getId());
        mitraPenelitianMapper.updateByExampleSelective(toMitraPenelitian(mitraPenelitianDTO), ex);
    }

    public void delete(Integer id, String username) {
        MitraPenelitian mitraPenelitian = mitraPenelitianMapper.selectByPrimaryKey(id);
        MitraPenelitianExample ex = new MitraPenelitianExample();
        ex.createCriteria().andIdEqualTo(id);;
        mitraPenelitianMapper.deleteByExample(ex);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/penelitian/" + mitraPenelitian.getIdPenelitian() + "/mitra-penelitian");
    }

    public MitraPenelitian getMitraPenelitianById(Integer idMitra){
        return mitraPenelitianMapper.selectByPrimaryKey(idMitra);
    }

    public List<JenisMitra> getAllJenisMitra(){
        JenisMitraExample ex = new JenisMitraExample();
        return jenisMitraMapper.selectByExample(ex);
    }

    public String getFile(Integer id) {
        MitraPenelitian dokumen = mitraPenelitianMapper.selectByPrimaryKey(id);
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(new File(dokumen.getFilePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encodedFile = "data:application/pdf;base64," + Base64.getEncoder().encodeToString(fileContent);
        return encodedFile;
    }

    public void deleteFile(Integer id) {
        MitraPenelitian mitraPenelitian = mitraPenelitianMapper.selectByPrimaryKey(id);
        if (mitraPenelitian.getFilePath() != null) {
            File file = new File(mitraPenelitian.getFilePath());
            file.delete();
        }
    }
    
    public KontribusiMitraDTOGroupByMitraPenelitian getRekapMitraPenelitianFromPenelitian(Integer idPenelitian){
        KontribusiMitraDTOGroupByMitraPenelitian kmdtogbmp = new KontribusiMitraDTOGroupByMitraPenelitian();
        List<MitraPenelitianDTO> mitraPenelitianDTOs = mitraPenelitianMapper.getRekapMitraPenelitianFromPenelitian(idPenelitian);
        int totalDanaMitra = 0;
        List<String> listRekapBarangMitra = new ArrayList();
        for (MitraPenelitianDTO mitraPenelitianDTO : mitraPenelitianDTOs) {
            listRekapBarangMitra.add(mitraPenelitianDTO.getRekapBarang());
            totalDanaMitra += mitraPenelitianDTO.getJumlahKontribusi();
        }
        kmdtogbmp.setRekapBarangMitra(listRekapBarangMitra);
        kmdtogbmp.setTotalDanaMitra(commonUtil.toIdrFormat(new Double(totalDanaMitra)));
        return kmdtogbmp;
    }
}

