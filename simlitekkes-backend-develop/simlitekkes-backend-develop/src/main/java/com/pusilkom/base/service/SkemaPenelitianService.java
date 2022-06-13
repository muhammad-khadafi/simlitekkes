package com.pusilkom.base.service;

import com.pusilkom.base.dto.MenuDTO;
import com.pusilkom.base.dto.SkemaPenelitianDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.MenuMapper;
import com.pusilkom.base.model.mapper.PenelitianMapper;
import com.pusilkom.base.model.mapper.SkemaPenelitianMapper;
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
public class SkemaPenelitianService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SkemaPenelitianMapper skemaPenelitianMapper;
    @Autowired
    PenelitianMapper penelitianMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    DebugUtil d;

    public List<SkemaPenelitianDTO> getListSkemaPenelitian(SkemaPenelitianDTO searchForm) {
        List<SkemaPenelitianDTO> listItem = skemaPenelitianMapper.getListSkemaPenelitian(searchForm);
        for(SkemaPenelitianDTO skema : listItem){
            String angkaAwal = "";
            String angkaAkhir = "";
            if(skema.getJumlahDanaMin() != null && skema.getJumlahDanaMax() != null) {
                angkaAwal = this.thousandSeparator(skema.getJumlahDanaMin());
                angkaAkhir = this.thousandSeparator(skema.getJumlahDanaMax());
            }
            skema.setJumlahDanaString(angkaAwal + "-" +angkaAkhir);
        }
        log.info("list item : {}", d.toString(listItem));
        return listItem;
    }

    public SkemaPenelitian findById(Long id){
        SkemaPenelitianExample skemaPenelitianExample = new SkemaPenelitianExample();
        skemaPenelitianExample.createCriteria().andIdEqualTo(id.intValue());
        List<SkemaPenelitian> listSkemaPenelitian = skemaPenelitianMapper.selectByExample(skemaPenelitianExample);
        return listSkemaPenelitian.get(0);
    }

    public void save(SkemaPenelitianDTO skemaPenelitianDTO) {
        logAktivitasService.addLog(skemaPenelitianDTO.getUsername(), "POST", skemaPenelitianDTO.toString(), "/skema-penelitian/" + skemaPenelitianDTO.getId());
        skemaPenelitianMapper.insert(toSkemaPenelitian(skemaPenelitianDTO));
    }

    public SkemaPenelitian toSkemaPenelitian(SkemaPenelitianDTO skemaPenelitianDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(skemaPenelitianDTO, SkemaPenelitian.class);
    }

    public void delete(Long id) {
        skemaPenelitianMapper.deleteByPrimaryKey(id.intValue());
    }

    public void update(SkemaPenelitianDTO skemaPenelitianDTO) {
        logAktivitasService.addLog(skemaPenelitianDTO.getUsername(), "PUT", skemaPenelitianDTO.toString(), "/skema-penelitian/" + skemaPenelitianDTO.getId());
        skemaPenelitianMapper.updateByPrimaryKeySelective(toSkemaPenelitian(skemaPenelitianDTO));
    }

    public String thousandSeparator(int num){
        String str = String.format("%,d", num).replace(',', '.');
        return str;
    }

    public List<KategoriSkemaPenelitian> getListKategoriByKlasifikasi(SkemaPenelitianDTO skemaPenelitianDTO){
        List<KategoriSkemaPenelitian> listKategori = skemaPenelitianMapper.getListKategoriByKlasifikasi(skemaPenelitianDTO);
        return listKategori;
    }

    public List<SkemaPenelitian> getListSkemaPenelitianByIdKategoriAndKlasifikasi(Integer idKategori, Integer klasifikasi){
        SkemaPenelitianExample ex = new SkemaPenelitianExample();
        if(klasifikasi == 1) {
            ex.createCriteria().andIdKategoriSkemaPenelitianEqualTo(idKategori).
                    andKlasifikasi1EqualTo(true);
        } else if(klasifikasi == 2) {
            ex.createCriteria().andIdKategoriSkemaPenelitianEqualTo(idKategori).
                    andKlasifikasi2EqualTo(true);
        } else if(klasifikasi == 3) {
            ex.createCriteria().andIdKategoriSkemaPenelitianEqualTo(idKategori).
                    andKlasifikasi3EqualTo(true);
        }
        List<SkemaPenelitian> listSkema = skemaPenelitianMapper.selectByExample(ex);
        return listSkema;
    }

    public SkemaPenelitian getListSkemaPenelitianByIdPenelitian(Integer idPenelitian){
        Penelitian penelitian = penelitianMapper.selectByPrimaryKey(idPenelitian);
        SkemaPenelitian skemaPenelitian = skemaPenelitianMapper.selectByPrimaryKey(penelitian.getIdSkemaPenelitian());
        return skemaPenelitian;
    }
}

