/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.service;

import com.pusilkom.base.dto.PenelitiDTO;
import com.pusilkom.base.dto.TimPenelitiDTO;
import com.pusilkom.base.model.MitraPenelitian;
import com.pusilkom.base.model.TimPeneliti;
import com.pusilkom.base.model.TimPenelitiExample;
import com.pusilkom.base.model.mapper.PenelitiMapper;
import com.pusilkom.base.model.mapper.RumpunIlmuMapper;
import com.pusilkom.base.model.mapper.TimPenelitiMapper;
import com.pusilkom.base.util.DebugUtil;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Helmi Fakhriandi
 */
@Service
@Transactional
public class TimPenelitiService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    DebugUtil d;

    @Autowired
    TimPenelitiMapper timPenelitiMapper;
    @Autowired
    PenelitiMapper penelitiMapper;
    @Autowired
    RumpunIlmuMapper rumpunIlmuMapper;

    public TimPeneliti getKetuaTimPeneliti(Integer idPenelitian) {
        TimPenelitiExample timPenelitiExample = new TimPenelitiExample();
        timPenelitiExample.createCriteria()
                .andIdPenelitianEqualTo(idPenelitian)
                .andIdPeranPenelitiEqualTo(1);
        List<TimPeneliti> listTimPeneliti = timPenelitiMapper.selectByExample(timPenelitiExample);
        return listTimPeneliti.get(0);
    }

    public TimPenelitiDTO getDataKetuaFromIdPenelitian(Integer idPenelitian) {
        List<TimPenelitiDTO> timPenelitiDTOs = timPenelitiMapper.getDataKetuaFromIdPenelitian(idPenelitian);
        return timPenelitiDTOs.get(0);
    }

    public List<PenelitiDTO> getListPenelitiFromIdPenelitian(Integer idPenelitian, boolean isPoltekkes) {
        List<PenelitiDTO> penelitiDTOs = penelitiMapper.getListPenelitiFromIdPenelitian(idPenelitian, isPoltekkes);
        return penelitiDTOs;
    }

    public void save(TimPenelitiDTO timPenelitiDTO) {
        logAktivitasService.addLog(timPenelitiDTO.getUsername(), "POST", timPenelitiDTO.toString(), "/usulan/penelitian");
        timPenelitiMapper.insert(toTimPeneliti(timPenelitiDTO));
    }

    public TimPeneliti toTimPeneliti(TimPenelitiDTO timPenelitiDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(timPenelitiDTO, TimPeneliti.class);
    }

    public void delete(Integer id, String username) {
        TimPeneliti timPeneliti = timPenelitiMapper.selectByPrimaryKey(id);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/penelitian/" + timPeneliti.getIdPenelitian() + "/identitas-usulan");
        timPenelitiMapper.deleteByPrimaryKey(id);
    }
}
