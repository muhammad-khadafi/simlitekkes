/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.service;

import com.pusilkom.base.dto.TargetOutputPenelitianDTO;
import com.pusilkom.base.model.Penelitian;
import com.pusilkom.base.model.TargetOutputPenelitian;
import com.pusilkom.base.model.TargetOutputPenelitianExample;
import com.pusilkom.base.model.mapper.PenelitianMapper;
import com.pusilkom.base.model.mapper.TargetOutputPenelitianMapper;
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
public class TargetOutputPenelitianService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    DebugUtil d;
    @Autowired
    TargetOutputPenelitianMapper targetOutputPenelitianMapper;
    @Autowired
    PenelitianMapper penelitianMapper;

    public List<TargetOutputPenelitianDTO> getListTargetOutputPenelitian(Integer idPenelitian) {
        List<TargetOutputPenelitianDTO> targetOutputPenelitianDTOs = targetOutputPenelitianMapper.getListTargetOutputPenelitian(idPenelitian);
        return targetOutputPenelitianDTOs;
    }

    public TargetOutputPenelitian toTargetOutputPenelitian(TargetOutputPenelitianDTO targetOutputPenelitianDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(targetOutputPenelitianDTO, TargetOutputPenelitian.class);
    }

    public void deleteTargetOutputPenelitian(Integer id, String username){
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/target-output-penelitian");
        targetOutputPenelitianMapper.deleteByPrimaryKey(id);
    }

    public void updateTargetOutputPenelitian(TargetOutputPenelitianDTO targetOutputPenelitianDTO){
        logAktivitasService.addLog(targetOutputPenelitianDTO.getUsername(), "PUT", targetOutputPenelitianDTO.toString(), "/target-output-penelitian");
        targetOutputPenelitianMapper.updateByPrimaryKeySelective(toTargetOutputPenelitian(targetOutputPenelitianDTO));
    }

    public void insertTargetOutputPenelitian(TargetOutputPenelitianDTO targetOutputPenelitianDTO){
        logAktivitasService.addLog(targetOutputPenelitianDTO.getUsername(), "POST", targetOutputPenelitianDTO.toString(), "/target-output-penelitian");
        targetOutputPenelitianMapper.insert(toTargetOutputPenelitian(targetOutputPenelitianDTO));
    }

    public Boolean validasiLuaranWajib(Integer idPenelitian) {

        Penelitian penelitian = penelitianMapper.selectByPrimaryKey(idPenelitian);
        Integer durasiTahun = Integer.parseInt(penelitian.getTahunSelesaiPelaksanaan())
            - Integer.parseInt(penelitian.getTahunMulaiPelaksanaan()) + 1;

        TargetOutputPenelitianExample targetOutputPenelitianExample = new TargetOutputPenelitianExample();
        targetOutputPenelitianExample.createCriteria().andIdPenelitianEqualTo(idPenelitian).andIsWajibEqualTo(true);

        List<TargetOutputPenelitian> targetOutputPenelitians = targetOutputPenelitianMapper.selectByExample(targetOutputPenelitianExample);
        if (targetOutputPenelitians.size() == durasiTahun)
            return true;
         else
             return false;
    }
}
