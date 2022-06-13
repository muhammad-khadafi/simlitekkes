package com.pusilkom.base.service;

import com.pusilkom.base.dto.DokumenOrganisasiDTO;
import com.pusilkom.base.model.DokumenOrganisasi;
import com.pusilkom.base.model.mapper.DokumenOrganisasiMapper;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

/**
 * Created by muhammad.khadafi on 30/03/2020.
 */

@Service
@Transactional
public class DokumenOrganisasiService {

    @Autowired
    DokumenOrganisasiMapper dokumenOrganisasiMapper;

    @Autowired
    LogAktivitasService logAktivitasService;

    public List<DokumenOrganisasiDTO> getListData(Integer idOrganisasi) {
       return dokumenOrganisasiMapper.getListData(idOrganisasi);
    }

    public DokumenOrganisasi getDokumenOrganisasi(Integer id) {
        return dokumenOrganisasiMapper.selectByPrimaryKey(id);
    }

    public void saveDokumenOrganisasi(DokumenOrganisasiDTO dokumenOrganisasiDTO) {
        logAktivitasService.addLog(dokumenOrganisasiDTO.getUsername(), "POST", dokumenOrganisasiDTO.toString(), "/dokumen-organisasi");
        dokumenOrganisasiMapper.insert(toDokumenOrganisasi(dokumenOrganisasiDTO));
    }

    private DokumenOrganisasi toDokumenOrganisasi(DokumenOrganisasiDTO dokumenOrganisasiDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dokumenOrganisasiDTO, DokumenOrganisasi.class);
    }

    public void deleteFile(Integer id) {
        DokumenOrganisasi dokumenOrganisasi = dokumenOrganisasiMapper.selectByPrimaryKey(id);
        if (dokumenOrganisasi.getFilePath() != null) {
            File file = new File(dokumenOrganisasi.getFilePath());
            file.delete();
        }
    }

    public void deleteDokumenOrganisasi(Integer id, String username) {
        logAktivitasService.addLog(username, "DELETE", null, "/dokumen-organisasi/" + id + "/" + username);
        dokumenOrganisasiMapper.deleteByPrimaryKey(id);
    }

    public String getFile(Integer id) {
        DokumenOrganisasi dokumenOrganisasi = dokumenOrganisasiMapper.selectByPrimaryKey(id);
        byte[] fileContent = new byte[0];
        try {
            fileContent = FileUtils.readFileToByteArray(new File(dokumenOrganisasi.getFilePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encodedFile = "data:application/pdf;base64," + Base64.getEncoder().encodeToString(fileContent);
        return encodedFile;
    }

    public void updateDokumenOrganisasi(DokumenOrganisasiDTO dokumenOrganisasiDTO) {
        logAktivitasService.addLog(dokumenOrganisasiDTO.getUsername(), "PUT", dokumenOrganisasiDTO.toString(), "/dokumen-organisasi");
        deleteFile(dokumenOrganisasiDTO.getId());
        dokumenOrganisasiMapper.updateByPrimaryKeySelective(toDokumenOrganisasi(dokumenOrganisasiDTO));
    }

    public List<DokumenOrganisasiDTO> getDokumenPenugasanReviewer(Integer idOrganisasi, String tahunUsulan, String kataKunci) {
        return dokumenOrganisasiMapper.getDokumenPenugasanReviewer(idOrganisasi, tahunUsulan, "%"+kataKunci.trim()+"%");
    }
}
