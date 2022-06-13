package com.pusilkom.base.service;

import com.pusilkom.base.dto.BukuDTO;
import com.pusilkom.base.dto.JadwalKegiatanDTO;
import com.pusilkom.base.model.Buku;
import com.pusilkom.base.model.BukuExample;
import com.pusilkom.base.model.JadwalKegiatan;
import com.pusilkom.base.model.JadwalKegiatanExample;
import com.pusilkom.base.model.mapper.JadwalKegiatanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ars on 04/24/2020.
 */

@Service
@Transactional
public class JadwalKegiatanService {
    @Autowired
    JadwalKegiatanMapper jadwalKegiatanMapper;
    @Autowired
    LogAktivitasService logAktivitasService;

    public List<JadwalKegiatan> getListJadwalKegiatanByIdPenelitian(Integer idPenelitian, Integer tahun){
        JadwalKegiatanExample ex = new JadwalKegiatanExample();
        ex.createCriteria().andIdPenelitianEqualTo(idPenelitian).andTahunEqualTo(tahun.shortValue());
        return jadwalKegiatanMapper.selectByExample(ex);
    }

    public List<JadwalKegiatan> getAllJadwalKegiatanByIdPenelitian(Integer idPenelitian){
        JadwalKegiatanExample ex = new JadwalKegiatanExample();
        ex.createCriteria().andIdPenelitianEqualTo(idPenelitian);
        return jadwalKegiatanMapper.selectByExample(ex);
    }

    public List<Integer> getListTahunJadwalKegiatanByIdPenelitian(Integer idPenelitian){
        return jadwalKegiatanMapper.getListTahunJadwalKegiatanByIdPenelitian(idPenelitian);
    }

    public JadwalKegiatan toJadwalKegiatan(JadwalKegiatanDTO jadwalKegiatanDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(jadwalKegiatanDTO, JadwalKegiatan.class);
    }

    public void save(JadwalKegiatanDTO jadwalKegiatanDTO) {
        logAktivitasService.addLog(jadwalKegiatanDTO.getUsername(), "POST", jadwalKegiatanDTO.toString(), "/penelitian/" + jadwalKegiatanDTO.getIdPenelitian() + "/rencana-kegiatan");
        jadwalKegiatanMapper.insert(toJadwalKegiatan(jadwalKegiatanDTO));
    }

    public void update(JadwalKegiatanDTO jadwalKegiatanDTO) {
        logAktivitasService.addLog(jadwalKegiatanDTO.getUsername(), "PUT", jadwalKegiatanDTO.toString(), "/penelitian/" + jadwalKegiatanDTO.getIdPenelitian() + "/rencana-kegiatan");
        JadwalKegiatanExample ex = new JadwalKegiatanExample();
        ex.createCriteria().andIdEqualTo(jadwalKegiatanDTO.getId());
        jadwalKegiatanMapper.updateByExampleSelective(toJadwalKegiatan(jadwalKegiatanDTO), ex);
    }

    public void delete(Integer id, String username) {
        JadwalKegiatan jadwalKegiatan = jadwalKegiatanMapper.selectByPrimaryKey(id);
        JadwalKegiatanExample ex = new JadwalKegiatanExample();
        ex.createCriteria().andIdEqualTo(id);;
        jadwalKegiatanMapper.deleteByExample(ex);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/penelitian/" + jadwalKegiatan.getIdPenelitian() + "/rencana-kegiatan");
    }
}

