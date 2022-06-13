package com.pusilkom.base.service;

import com.pusilkom.base.dto.TemaDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.KelompokTktMapper;
import com.pusilkom.base.model.mapper.TemaMapper;
import com.pusilkom.base.util.DebugUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ars on 11/03/2020.
 */
@Service
@Transactional
public class TemaService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TemaMapper temaMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    KelompokTktMapper kelompokTktMapper;
    @Autowired
    DebugUtil d;

    public List<TemaDTO> getListTema(TemaDTO searchform) {
        List<TemaDTO> listTema = temaMapper.getListTemaDTO(searchform);
        return listTema;
    }

    public Tema toTema(TemaDTO temaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(temaDTO, Tema.class);
    }

    public void save(TemaDTO temaDTO) {
        logAktivitasService.addLog(temaDTO.getUsername(), "POST", temaDTO.toString(), "/tema/" + temaDTO.getId());
        temaMapper.insert(toTema(temaDTO));
    }

    public void update(TemaDTO temaDTO) {
        logAktivitasService.addLog(temaDTO.getUsername(), "PUT", temaDTO.toString(), "/tema/" + temaDTO.getId());
        TemaExample ex = new TemaExample();
        ex.createCriteria().andIdEqualTo(temaDTO.getId());
        temaMapper.updateByExampleSelective(toTema(temaDTO), ex);
    }

    public void delete(Integer id, String username) {
        TemaExample ex = new TemaExample();
        ex.createCriteria().andIdEqualTo(id);;
        temaMapper.deleteByExample(ex);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/tema/" + id);
    }

    public List<KelompokTkt> getAllKelompokTkt() {
        KelompokTktExample ex = new KelompokTktExample();
        return kelompokTktMapper.selectByExample(ex);
    }
}
