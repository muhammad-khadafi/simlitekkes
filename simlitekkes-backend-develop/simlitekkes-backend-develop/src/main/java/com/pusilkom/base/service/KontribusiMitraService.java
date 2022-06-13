package com.pusilkom.base.service;

import com.pusilkom.base.dto.KontribusiMitraDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.JenisMitraMapper;
import com.pusilkom.base.model.mapper.KontribusiMitraMapper;
import com.pusilkom.base.model.mapper.MitraPenelitianMapper;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * Created by ars on 05/05/2020.
 */

@Service
@Transactional
public class KontribusiMitraService {
    @Autowired
    KontribusiMitraMapper kontribusiMitraMapper;
    @Autowired
    MitraPenelitianMapper mitraPenelitianMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    JenisMitraMapper jenisMitraMapper;

    public KontribusiMitra toKontribusiMitra(KontribusiMitraDTO kontribusiMitraDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(kontribusiMitraDTO, KontribusiMitra.class);
    }

    public void save(KontribusiMitraDTO kontribusiMitraDTO) {
        logAktivitasService.addLog(kontribusiMitraDTO.getUsername(), "POST", kontribusiMitraDTO.toString(), "/penelitian/" + kontribusiMitraDTO.getIdPenelitian() + "/mitra-penelitian");
        kontribusiMitraMapper.insert(toKontribusiMitra(kontribusiMitraDTO));
    }

    public void update(KontribusiMitraDTO kontribusiMitraDTO) {
        KontribusiMitraExample ex = new KontribusiMitraExample();
        ex.createCriteria().andIdEqualTo(kontribusiMitraDTO.getId());
        kontribusiMitraMapper.updateByExample(toKontribusiMitra(kontribusiMitraDTO), ex);
        logAktivitasService.addLog(kontribusiMitraDTO.getUsername(), "PUT", kontribusiMitraDTO.toString(), "/penelitian/" + kontribusiMitraDTO.getIdPenelitian() + "/mitra-penelitian");
    }

    public void delete(Integer idMitra, String username) {
        MitraPenelitian mitra = mitraPenelitianMapper.selectByPrimaryKey(idMitra);
        KontribusiMitraExample ex = new KontribusiMitraExample();
        ex.createCriteria().andIdMitraPenelitianEqualTo(idMitra);
        kontribusiMitraMapper.deleteByExample(ex);
        logAktivitasService.addLog(username, "DELETE", idMitra.toString(), "/penelitian/" + mitra.getIdPenelitian() + "/mitra-penelitian");
    }

    public List<KontribusiMitra> getKontribusiMitraByIdMitra(Integer idMitra){
        KontribusiMitraExample ex = new KontribusiMitraExample();
        ex.createCriteria().andIdMitraPenelitianEqualTo(idMitra);
        return kontribusiMitraMapper.selectByExample(ex);
    }

    public void deleteByPk(Integer id, Integer idPenelitian, String username) {
        kontribusiMitraMapper.deleteByPrimaryKey(id);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/penelitian/" + idPenelitian + "/mitra-penelitian");
    }
}

