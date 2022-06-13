package com.pusilkom.base.service;

import com.pusilkom.base.dto.PenulisOutputDTO;
import com.pusilkom.base.model.PenulisOutput;
import com.pusilkom.base.model.PenulisOutputExample;
import com.pusilkom.base.model.PeranPenulisOutput;
import com.pusilkom.base.model.PeranPenulisOutputExample;
import com.pusilkom.base.model.mapper.PenulisOutputMapper;
import com.pusilkom.base.model.mapper.PeranPenulisOutputMapper;
import com.pusilkom.base.util.DebugUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ars on 04/03/2020.
 */

@Service
@Transactional
public class PenulisOutputService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PenulisOutputMapper penulisOutputMapper;
    @Autowired
    PeranPenulisOutputMapper peranPenulisOutputMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    DebugUtil d;

    public PenulisOutput toPenulisOutput(PenulisOutputDTO penulisOutputDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(penulisOutputDTO, PenulisOutput.class);
    }

    public void save(PenulisOutputDTO penulisOutputDTO) {
        logAktivitasService.addLog(penulisOutputDTO.getUsername(), "POST", penulisOutputDTO.toString(), "/profil-dosen");
        penulisOutputMapper.insert(toPenulisOutput(penulisOutputDTO));
    }

    public void update(PenulisOutputDTO penulisOutputDTO) {
        logAktivitasService.addLog(penulisOutputDTO.getUsername(), "PUT", penulisOutputDTO.toString(), "/profil-dosen/" + penulisOutputDTO.getId());
        penulisOutputMapper.updateByPrimaryKeySelective(toPenulisOutput(penulisOutputDTO));
    }

    public void delete(Integer idOutput, String username) {
        PenulisOutputExample ex = new PenulisOutputExample();
        ex.createCriteria().andIdOutputEqualTo(idOutput);
        penulisOutputMapper.deleteByExample(ex);
        logAktivitasService.addLog(username, "DELETE", idOutput.toString(), "/profil-dosen/" + idOutput);
    }

    public PenulisOutput getPenulisOutputByIdOutput(Integer id){
        PenulisOutputExample ex = new PenulisOutputExample();
        ex.createCriteria().andIdOutputEqualTo(id);
        return penulisOutputMapper.selectByExample(ex).get(0);
    }
    public PenulisOutput getPenulisOutputById(Integer id){
        return penulisOutputMapper.selectByPrimaryKey(id);
    }

    public List<PeranPenulisOutput> getAllPeranPenulisOutput(){
        PeranPenulisOutputExample ex = new PeranPenulisOutputExample();
        return peranPenulisOutputMapper.selectByExample(ex);
    }
}

