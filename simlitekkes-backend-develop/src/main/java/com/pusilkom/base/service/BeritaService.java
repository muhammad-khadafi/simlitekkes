package com.pusilkom.base.service;

import com.pusilkom.base.dto.BeritaDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.BeritaMapper;
import com.pusilkom.base.model.mapper.OrganisasiMapper;
import com.pusilkom.base.model.mapper.UserwebMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by ars on 04/02/2020.
 */

@Service
@Transactional
public class BeritaService {

    @Autowired
    BeritaMapper beritaMapper;

    @Autowired
    LogAktivitasService logAktivitasService;

    @Autowired
    UserwebMapper userwebMapper;

    public Berita toBerita(BeritaDTO beritaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(beritaDTO, Berita.class);
    }

    public List<BeritaDTO> getAllBerita(Integer idUser, String roleCode) {
        //admin berita adalah operator ppm, pengelola pusat dan admin ppsdm
        //operator ppm (institusinya sendiri + ppsdm) W (institusinya sendiri)
        //pengelola pusat R seluruh institusi W institusinya sendiri
        //admin ppsdm RW seluruh institusi
        //selain admin berita hanya bisa R (institusinya sendiri + ppsdm) dan is_published = true

        Integer idOrganisasi = userwebMapper.selectByPrimaryKey(idUser).getIdOrganisasi();
        //untuk admin ppsdm dan pengelola pusat yang bisa read berita semua institusi
        if (idOrganisasi == 1 ) {
            return beritaMapper.getAllBerita();
        } else { //selain admin ppsdm dan pengelola pusat, hanya bisa read berita institusinya sendiri dan ppsdm
            return beritaMapper.getBeritaForNonPpsdm(idOrganisasi, roleCode);
        }
    }

    public void addBerita(BeritaDTO beritaDTO) {
        beritaDTO.setPublishTimestamp(Calendar.getInstance().getTime());
        beritaMapper.insert(toBerita(beritaDTO));
        logAktivitasService.addLog(beritaDTO.getUserPublish(), "POST", beritaDTO.toString(), "/berita");
    }

    public Berita getBeritaById(Integer id) {
        return beritaMapper.selectByPrimaryKey(id);
    }

    public void updateBerita(BeritaDTO beritaDTO) {
        beritaDTO.setPublishTimestamp(Calendar.getInstance().getTime());
        beritaMapper.updateByPrimaryKeySelective(toBerita(beritaDTO));
        logAktivitasService.addLog(beritaDTO.getUserPublish(), "PUT", beritaDTO.toString(), "/berita");
    }

    public void deleteBerita(Integer id, String username) {
        beritaMapper.deleteByPrimaryKey(id);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/berita");
    }

    public BeritaDTO getDetailBeritaById(Integer idBerita) {
        return beritaMapper.getDetailBeritaById(idBerita);
    }

}