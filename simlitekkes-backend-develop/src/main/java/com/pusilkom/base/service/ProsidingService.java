package com.pusilkom.base.service;

import com.pusilkom.base.dto.ProsidingDTO;
import com.pusilkom.base.model.Prosiding;
import com.pusilkom.base.model.ProsidingExample;
import com.pusilkom.base.model.mapper.ProsidingMapper;
import com.pusilkom.base.util.DebugUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ars on 14/04/2020.
 */

@Service
@Transactional
public class ProsidingService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProsidingMapper prosidingMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    PenulisOutputService penulisOutputService;
    @Autowired
    OutputService outputService;
    @Autowired
    DebugUtil d;

    public List<ProsidingDTO> getListProsidingDTO(ProsidingDTO searchform) {
        List<ProsidingDTO> listProsiding = prosidingMapper.getListProsidingDTO(searchform);
        for(ProsidingDTO detail: listProsiding){
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
        return listProsiding;
    }

    public Prosiding getProsidingByIdOutput(Integer id){
        ProsidingExample ex = new ProsidingExample();
        ex.createCriteria().andIdOutputEqualTo(id);
        List<Prosiding> listProsiding = prosidingMapper.selectByExample(ex);
        return listProsiding.get(0);
    }

    public Prosiding toProsiding(ProsidingDTO ProsidingDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(ProsidingDTO, Prosiding.class);
    }

    public void save(ProsidingDTO ProsidingDTO) {
        logAktivitasService.addLog(ProsidingDTO.getUsername(), "POST", ProsidingDTO.toString(), "/prosiding-dosen");
        prosidingMapper.insert(toProsiding(ProsidingDTO));
    }

    public void update(ProsidingDTO ProsidingDTO) {
        logAktivitasService.addLog(ProsidingDTO.getUsername(), "PUT", ProsidingDTO.toString(), "/prosiding-dosen/" + ProsidingDTO.getIdOutput());
        ProsidingExample ex = new ProsidingExample();
        ex.createCriteria().andIdOutputEqualTo(ProsidingDTO.getIdOutput());
        prosidingMapper.updateByExampleSelective(toProsiding(ProsidingDTO), ex);
    }

    public void delete(Integer id, String username) {
        ProsidingExample ex = new ProsidingExample();
        ex.createCriteria().andIdOutputEqualTo(id);
        prosidingMapper.deleteByExample(ex);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/prosiding-dosen/" + id);
    }
}

