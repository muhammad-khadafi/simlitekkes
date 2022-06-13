package com.pusilkom.base.service;

import com.pusilkom.base.dto.JurnalDTO;
import com.pusilkom.base.model.Jurnal;
import com.pusilkom.base.model.JurnalExample;
import com.pusilkom.base.model.mapper.JurnalMapper;
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
public class JurnalService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JurnalMapper jurnalMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    PenulisOutputService penulisOutputService;
    @Autowired
    OutputService outputService;
    @Autowired
    DebugUtil d;

    public List<JurnalDTO> getListJurnal(JurnalDTO searchform) {
        List<JurnalDTO> listJurnal = jurnalMapper.getListJurnalDTO(searchform);
        for(JurnalDTO detail: listJurnal){
            if(detail.getPathFile() != null){
                detail.setFileName(outputService.pathToFileName(detail.getPathFile()));
            }
            if(detail.getIdOutput() != null){
                detail.setOutput(outputService.getOutputById(detail.getIdOutput()));
            }
            if(detail.getIdPenulisOutput() != null){
                detail.setPenulisOutput(penulisOutputService.getPenulisOutputById(detail.getIdPenulisOutput()));
            }
        }
        return listJurnal;
    }

    public Jurnal getJurnalByIdOutput(Integer id){
        JurnalExample ex = new JurnalExample();
        ex.createCriteria().andIdOutputEqualTo(id);
        List<Jurnal> listJurnal = jurnalMapper.selectByExample(ex);
        return listJurnal.get(0);
    }

    public Jurnal toJurnal(JurnalDTO jurnalDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(jurnalDTO, Jurnal.class);
    }

    public void save(JurnalDTO jurnalDTO) {
        logAktivitasService.addLog(jurnalDTO.getUsername(), "POST", jurnalDTO.toString(), "/jurnal-dosen");
        jurnalMapper.insert(toJurnal(jurnalDTO));
    }

    public void update(JurnalDTO jurnalDTO) {
        logAktivitasService.addLog(jurnalDTO.getUsername(), "PUT", jurnalDTO.toString(), "/jurnal-dosen/" + jurnalDTO.getIdOutput());
        JurnalExample ex = new JurnalExample();
        ex.createCriteria().andIdOutputEqualTo(jurnalDTO.getIdOutput());
        jurnalMapper.updateByExampleSelective(toJurnal(jurnalDTO), ex);
    }

    public void delete(Integer id, String username) {
        JurnalExample ex = new JurnalExample();
        ex.createCriteria().andIdOutputEqualTo(id);
        jurnalMapper.deleteByExample(ex);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/jurnal-dosen/" + id);
    }
}

