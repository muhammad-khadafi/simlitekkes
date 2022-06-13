/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.service;

import com.pusilkom.base.dto.JenisOutputDTO;
import com.pusilkom.base.model.mapper.JenisOutputMapper;
import com.pusilkom.base.util.DebugUtil;
import java.util.List;
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
public class JenisOutputService {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    DebugUtil d;
    @Autowired
    JenisOutputMapper jenisOutputMapper;
    
    public List<JenisOutputDTO> getListWajib(Integer idSkemaPenelitian, Integer idPenelitian){
        List<JenisOutputDTO> jenisOutputDTOs = jenisOutputMapper.getListWajib(idSkemaPenelitian, idPenelitian);
        return jenisOutputDTOs;
    }
    
    public List<JenisOutputDTO> getListTambahan(Integer idPenelitian){
        List<JenisOutputDTO> jenisOutputDTOs = jenisOutputMapper.getListTambahan(idPenelitian);
        return jenisOutputDTOs;
    }
    
    public List<JenisOutputDTO> getAll(){
        List<JenisOutputDTO> jenisOutputs = jenisOutputMapper.getAll();
        return jenisOutputs;
    }   
}
