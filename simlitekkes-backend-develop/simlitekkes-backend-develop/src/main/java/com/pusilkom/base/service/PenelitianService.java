/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.service;

import com.pusilkom.base.dto.BukuDTO;
import com.pusilkom.base.dto.PenelitiDTO;
import com.pusilkom.base.dto.PenelitianDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.*;
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
public class PenelitianService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PenelitianMapper penelitianMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    SkemaPenelitianMapper skemaPenelitianMapper;
    @Autowired
    StatusTahapPenelitianMapper statusTahapPenelitianMapper;
    @Autowired
    TargetOutputPenelitianMapper targetOutputPenelitianMapper;
    @Autowired
    TargetOutputPenelitianService targetOutputPenelitianService;
    @Autowired
    JadwalKegiatanMapper jadwalKegiatanMapper;
    @Autowired
    JadwalKegiatanService jadwalKegiatanService;
    @Autowired
    RencanaAnggaranMapper rencanaAnggaranMapper;
    @Autowired
    RencanaAnggaranService rencanaAnggaranService;
    @Autowired
    MitraPenelitianMapper mitraPenelitianMapper;
    @Autowired
    MitraPenelitianService mitraPenelitianService;
    @Autowired
    WorkflowPenelitianMapper workflowPenelitianMapper;
    @Autowired
    WorkflowPenelitianService workflowPenelitianService;
    @Autowired
    TimPenelitiMapper timPenelitiMapper;
    @Autowired
    TimPenelitiService timPenelitiService;
    @Autowired
    KontribusiMitraMapper kontribusiMitraMapper;
    @Autowired
    KontribusiMitraService kontribusiMitraService;
    @Autowired
    DebugUtil d;

    public List<PenelitianDTO> getListUsulan(Integer idUser){
        List<PenelitianDTO> listUsulan = penelitianMapper.getListUsulan(idUser, false);
        return listUsulan;
    }
    public List<PenelitianDTO> getListUsulanRiwayat(Integer idUser){
        List<PenelitianDTO> listUsulan = penelitianMapper.getListUsulan(idUser, true);
        return listUsulan;
    }

    public List<PenelitianDTO> getRekap(Integer idOrganisasi){
        List<PenelitianDTO> rekap = penelitianMapper.getRekap(idOrganisasi);
        for (PenelitianDTO penelitianDTO : rekap) {
            String tahunPelaksanaan = "";
            if(penelitianDTO.getTahunMulaiPelaksanaan()!= null){
                tahunPelaksanaan += penelitianDTO.getTahunMulaiPelaksanaan();
                if(penelitianDTO.getTahunSelesaiPelaksanaan()!= null){
                    tahunPelaksanaan += " - " + penelitianDTO.getTahunSelesaiPelaksanaan();
                }
            }
            else{
                tahunPelaksanaan += "-";
            }
            penelitianDTO.setTahunPelaksanaan(tahunPelaksanaan);
        }
        return rekap;
    }

    public List<PenelitianDTO> getDaftarBySkemaTahun(Integer idOrganisasi, Integer idSkemaPenelitian, String tahunUsulan, String tahunMulai, String tahunSelesai){
        List<PenelitianDTO> daftarUsulan = penelitianMapper.getDaftarBySkemaTahun(idOrganisasi, idSkemaPenelitian, tahunUsulan, tahunMulai, tahunSelesai);
        return daftarUsulan;
    }

    public Penelitian getPenelitianById(Integer id){
        PenelitianExample penelitianExample = new PenelitianExample();
        penelitianExample.createCriteria().andIdEqualTo(id);
        List<Penelitian> listPenelitian = penelitianMapper.selectByExample(penelitianExample);
        return listPenelitian.get(0);
    }

    public List<PenelitianDTO> getListByIdPeneliti(Integer idUser){
        List<PenelitianDTO> penelitianDTOs = penelitianMapper.getListByIdPeneliti(idUser);
        for (PenelitianDTO penelitianDTO : penelitianDTOs) {
            if(penelitianDTO.getTahunMulaiPelaksanaan() != null){
                if(penelitianDTO.getTahunSelesaiPelaksanaan() != null){
                    String tahunPelaksanaan = penelitianDTO.getTahunMulaiPelaksanaan()
                            + " - " +penelitianDTO.getTahunSelesaiPelaksanaan();
                    penelitianDTO.setTahunPelaksanaan(tahunPelaksanaan);
                }
                else{
                    penelitianDTO.setTahunPelaksanaan(penelitianDTO.getTahunMulaiPelaksanaan());
                }
            }
            else{
                penelitianDTO.setTahunPelaksanaan("-");
            }
        }
        return penelitianDTOs;
    }

    public void updatePenelitian(PenelitianDTO penelitianDTO){
        logAktivitasService.addLog(penelitianDTO.getUsername(), "PUT", penelitianDTO.toString(), "penelitian");
        penelitianMapper.updateByPrimaryKeySelective(toPenelitian(penelitianDTO));
    }

    public Penelitian toPenelitian(PenelitianDTO penelitianDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(penelitianDTO, Penelitian.class);
    }

    public PenelitianDTO getPenelitianDetailStatusById(Integer idPenelitian) {
        List<PenelitianDTO> penelitianDTOList = penelitianMapper.getPenelitianDetailStatusById(idPenelitian);
        if (penelitianDTOList != null && penelitianDTOList.size() > 0) {
            return penelitianDTOList.get(0);
        } else {
            return new PenelitianDTO();
        }
    }

    public StatusTahapPenelitian getStatusTahapanPenelitianBaru (Integer idStatusSaatIni, String role, String kodeStatus) {
        List<StatusTahapPenelitian> list = statusTahapPenelitianMapper.getListStatusTahapPenelitianBaru(idStatusSaatIni, role, kodeStatus);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return new StatusTahapPenelitian();
        }
    }

    public Integer save(PenelitianDTO penelitianDTO) {
        Penelitian penelitian = toPenelitian(penelitianDTO);
        logAktivitasService.addLog(penelitianDTO.getUsername(), "POST", penelitianDTO.toString(), "/usulan/penelitian");
        penelitianMapper.insert(toPenelitian(penelitianDTO));
        return penelitian.getId();
    }

    public void delete (Integer id, String username) {
        Penelitian penelitian = penelitianMapper.selectByPrimaryKey(id);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/penelitian/" + penelitian.getId() + "/identitas-usulan");
        penelitianMapper.deleteByPrimaryKey(id);
    }

    public void deleteRiwayatPenelitian(Integer idPenelitian, String username) {
        //tbl target_output_penelitian
        TargetOutputPenelitianExample exTarget = new TargetOutputPenelitianExample();
        exTarget.createCriteria().andIdPenelitianEqualTo(idPenelitian);
        List<TargetOutputPenelitian> listTarget = targetOutputPenelitianMapper.selectByExample(exTarget);
        for (TargetOutputPenelitian target : listTarget) {
            targetOutputPenelitianService.deleteTargetOutputPenelitian(target.getId(), username);
        }

        //tbl rencana_anggaran
        RencanaAnggaranExample exRab = new RencanaAnggaranExample();
        exRab.createCriteria().andIdPenelitianEqualTo(idPenelitian);
        List<RencanaAnggaran> listRencana = rencanaAnggaranMapper.selectByExample(exRab);
        for (RencanaAnggaran rencana : listRencana) {
            rencanaAnggaranService.deleteRencanaAnggaran(rencana.getId());
        }

        //tbl jadwal_kegiatan
        JadwalKegiatanExample exJadwal = new JadwalKegiatanExample();
        exJadwal.createCriteria().andIdPenelitianEqualTo(idPenelitian);
        List<JadwalKegiatan> listJadwal = jadwalKegiatanMapper.selectByExample(exJadwal);
        for (JadwalKegiatan jadwal : listJadwal) {
            jadwalKegiatanService.delete(jadwal.getId(), username);
        }

        //tbl mitra_penelitian & filenya serta tbl kontribusi_mitra
        MitraPenelitianExample exMitra = new MitraPenelitianExample();
        exMitra.createCriteria().andIdPenelitianEqualTo(idPenelitian);
        List<MitraPenelitian> listMitra = mitraPenelitianMapper.selectByExample(exMitra);
        for (MitraPenelitian mitra : listMitra) {
            kontribusiMitraService.delete(mitra.getId(), username);
            mitraPenelitianService.deleteFile(mitra.getId());
            mitraPenelitianService.delete(mitra.getId(), username);
        }

        //tbl workflow_penelitian
        WorkflowPenelitianExample exWorkflow = new WorkflowPenelitianExample();
        exWorkflow.createCriteria().andIdPenelitianEqualTo(idPenelitian);
        List<WorkflowPenelitian> listWorkflow = workflowPenelitianMapper.selectByExample(exWorkflow);
        for (WorkflowPenelitian workflow : listWorkflow) {
            workflowPenelitianService.delete(workflow.getIdPenelitian(), workflow.getIdStatusTahapPenelitian(), username);
        }

        //tbl tim_peneliti
        TimPenelitiExample exTim = new TimPenelitiExample();
        exTim.createCriteria().andIdPenelitianEqualTo(idPenelitian);
        List<TimPeneliti> listTimPeneliti = timPenelitiMapper.selectByExample(exTim);
        for (TimPeneliti timPeneliti : listTimPeneliti) {
            timPenelitiService.delete(timPeneliti.getId(), username);
        }

        //tbl penelitian
        this.delete(idPenelitian, username);
    }
}
