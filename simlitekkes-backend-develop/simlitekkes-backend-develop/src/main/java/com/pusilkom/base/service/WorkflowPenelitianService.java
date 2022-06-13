package com.pusilkom.base.service;

import com.pusilkom.base.dto.PenelitianDTO;
import com.pusilkom.base.dto.WorkflowPenelitianDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.StatusTahapPenelitianMapper;
import com.pusilkom.base.model.mapper.WorkflowPenelitianMapper;
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
public class WorkflowPenelitianService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WorkflowPenelitianMapper workflowPenelitianMapper;
    @Autowired
    StatusTahapPenelitianMapper statusTahapPenelitianMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    DebugUtil d;

    public WorkflowPenelitian toWorkflowPenelitian(WorkflowPenelitianDTO workflowPenelitianDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(workflowPenelitianDTO, WorkflowPenelitian.class);
    }

    public void save(WorkflowPenelitianDTO workflowPenelitianDTO) {
        logAktivitasService.addLog(workflowPenelitianDTO.getUsername(), "POST", workflowPenelitianDTO.toString(), "/penelitian/" + workflowPenelitianDTO.getIdPenelitian() + "/kirim-usulan");
        workflowPenelitianMapper.insert(toWorkflowPenelitian(workflowPenelitianDTO));
    }

    public void update(WorkflowPenelitianDTO workflowPenelitianDTO) {
        workflowPenelitianMapper.updateIsLast(workflowPenelitianDTO.getIdPenelitian());
        logAktivitasService.addLog(workflowPenelitianDTO.getUsername(), "PUT", workflowPenelitianDTO.toString(), "/penelitian/" + workflowPenelitianDTO.getIdPenelitian() + "/kirim-usulan");
    }

    public List<WorkflowPenelitianDTO> getListWorkflowPenelitian(Integer idPenelitian){
        List<WorkflowPenelitianDTO> workflowPenelitianDTOs = workflowPenelitianMapper.getListWorkflowPenelitian(idPenelitian);
        return workflowPenelitianDTOs;
    }

    public List<WorkflowPenelitianDTO> getListWorkflowDraft(){
        return workflowPenelitianMapper.getListWorkflowDraft();
    }

    public StatusTahapPenelitian getStatusTahapPenelitianByKode(String kode) {
        StatusTahapPenelitianExample ex = new StatusTahapPenelitianExample();
        ex.createCriteria().andKodeStatusLikeInsensitive(kode);
        List<StatusTahapPenelitian> status = statusTahapPenelitianMapper.selectByExample(ex);
        return status.get(0);
    }

    public void delete(Integer idPenelitian, Integer idStatusTahapPenelitian, String username) {
        WorkflowPenelitianExample ex = new WorkflowPenelitianExample();
        ex.createCriteria().andIdPenelitianEqualTo(idPenelitian).andIdStatusTahapPenelitianEqualTo(idStatusTahapPenelitian);
        String logDel = "idPenelitian: " + idPenelitian + ", idStatusTahapPenelitian: " + idStatusTahapPenelitian;
        logAktivitasService.addLog(username, "DELETE", logDel, "/penelitian/" + idPenelitian + "/kirim-usulan");
        workflowPenelitianMapper.deleteByExample(ex);
    }
}

