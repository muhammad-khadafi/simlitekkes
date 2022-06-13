package com.pusilkom.base.service;

import com.pusilkom.base.dto.HkiDTO;
import com.pusilkom.base.model.Hki;
import com.pusilkom.base.model.HkiExample;
import com.pusilkom.base.model.mapper.HkiMapper;
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
public class HkiService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HkiMapper hkiMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    PenulisOutputService penulisOutputService;
    @Autowired
    OutputService outputService;
    @Autowired
    DebugUtil d;

    public List<HkiDTO> getListHkiDTO(HkiDTO searchform) {
        List<HkiDTO> listHki = hkiMapper.getListHkiDTO(searchform);
        for(HkiDTO detail: listHki){
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
        return listHki;
    }

    public Hki getHkiByIdOutput(Integer id){
        HkiExample ex = new HkiExample();
        ex.createCriteria().andIdOutputEqualTo(id);
        List<Hki> listHki = hkiMapper.selectByExample(ex);
        return listHki.get(0);
    }

    public Hki toHki(HkiDTO HkiDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(HkiDTO, Hki.class);
    }

    public void save(HkiDTO HkiDTO) {
        logAktivitasService.addLog(HkiDTO.getUsername(), "POST", HkiDTO.toString(), "/hki-dosen");
        hkiMapper.insert(toHki(HkiDTO));
    }

    public void update(HkiDTO HkiDTO) {
        logAktivitasService.addLog(HkiDTO.getUsername(), "PUT", HkiDTO.toString(), "/hki-dosen/" + HkiDTO.getIdOutput());
        HkiExample ex = new HkiExample();
        ex.createCriteria().andIdOutputEqualTo(HkiDTO.getIdOutput());
        hkiMapper.updateByExampleSelective(toHki(HkiDTO), ex);
    }

    public void delete(Integer id, String username) {
        HkiExample ex = new HkiExample();
        ex.createCriteria().andIdOutputEqualTo(id);
        hkiMapper.deleteByExample(ex);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/hki-dosen/" + id);
    }
}

