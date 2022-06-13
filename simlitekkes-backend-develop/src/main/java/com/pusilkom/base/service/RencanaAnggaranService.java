/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.service;

import com.pusilkom.base.dto.RencanaAnggaranDTO;
import com.pusilkom.base.dto.RencanaAnggaranDTOGroupByYear;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.KomponenRabMapper;
import com.pusilkom.base.model.mapper.PenelitianMapper;
import com.pusilkom.base.model.mapper.RencanaAnggaranMapper;
import com.pusilkom.base.model.mapper.SkemaPenelitianMapper;
import com.pusilkom.base.util.CommonUtil;
import com.pusilkom.base.util.DebugUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Helmi Fakhriandi
 */
@Service
@Transactional
public class RencanaAnggaranService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    DebugUtil d;
    @Autowired
    RencanaAnggaranMapper rencanaAnggaranMapper;
    @Autowired
    KomponenRabMapper komponenRabMapper;
    @Autowired
    CommonUtil commonUtil;
    @Autowired
    SkemaPenelitianMapper skemaPenelitianMapper;
    @Autowired
    PenelitianMapper penelitianMapper;

    public List<RencanaAnggaranDTO> getTotalBiayaPerTahun(Integer idPenelitian) {
        List<RencanaAnggaranDTO> result = rencanaAnggaranMapper.getTotalBiayaPerTahun(idPenelitian);
        return result;
    }

    public List<RencanaAnggaranDTO> getDetailFromPenelitianTahun(Integer idPenelitian, Integer tahun) {
        List<RencanaAnggaranDTO> result = rencanaAnggaranMapper.getDetailFromPenelitianTahun(idPenelitian, tahun);
        for (RencanaAnggaranDTO item :
                result) {
            item.setSubTotal(item.getBiayaSatuan().multiply(item.getVolume()));
            item.setSubTotalFormatted(commonUtil.toIdrFormat(item.getSubTotal().doubleValue()));
            item.setBiayaSatuanFormatted(commonUtil.toIdrFormat(item.getBiayaSatuan().doubleValue()));
        }
        return result;
    }

    public List<RencanaAnggaranDTOGroupByYear> getRencanaAnggaran(Integer idPenelitian) {
        Penelitian penelitian = penelitianMapper.selectByPrimaryKey(idPenelitian);
        SkemaPenelitian skemaPenelitian = skemaPenelitianMapper.selectByPrimaryKey(penelitian.getIdSkemaPenelitian());
        boolean hasLamaPenelitian = penelitian.getTahunMulaiPelaksanaan() != null && penelitian.getTahunSelesaiPelaksanaan() != null;
        Integer lamaPenelitian = 0;
        if (hasLamaPenelitian){
            lamaPenelitian = Integer.parseInt(penelitian.getTahunSelesaiPelaksanaan()) - Integer.parseInt(penelitian.getTahunMulaiPelaksanaan()) + 1;
        }
        List<RencanaAnggaranDTO> headRencanaAnggaran = new ArrayList<>();
        for(short year = 1; year <= lamaPenelitian; year++){
            RencanaAnggaranDTO rencanaAnggaranDTO = rencanaAnggaranMapper.getTotalBiayaByTahun(idPenelitian, year);
            if (rencanaAnggaranDTO == null) {
                rencanaAnggaranDTO = new RencanaAnggaranDTO();
                rencanaAnggaranDTO.setTahun(year);
                rencanaAnggaranDTO.setTotalBiaya(0L);
            }
            headRencanaAnggaran.add(rencanaAnggaranDTO);
        }
        List<RencanaAnggaranDTOGroupByYear> result = new ArrayList<>();
        for (RencanaAnggaranDTO item :
                headRencanaAnggaran) {
            RencanaAnggaranDTOGroupByYear rencanaAnggaranDTOGroupByYear = new RencanaAnggaranDTOGroupByYear();
            rencanaAnggaranDTOGroupByYear.setTahun(item.getTahun().toString());
            rencanaAnggaranDTOGroupByYear.setTotalBiaya(commonUtil.toIdrFormat(new Double(item.getTotalBiaya())));
            rencanaAnggaranDTOGroupByYear.setTotalBiayaNum(item.getTotalBiaya());
            List<RencanaAnggaranDTO> detailRencanaAnggaran = getDetailFromPenelitianTahun(
                    idPenelitian, item.getTahun().intValue());
            rencanaAnggaranDTOGroupByYear.setRencanaAnggaranListPerYear(detailRencanaAnggaran);
            result.add(rencanaAnggaranDTOGroupByYear);
        }
        return result;
    }

    public List<KomponenRab> getKomponenRab() {
         return komponenRabMapper.selectByExample(new KomponenRabExample());
    }

    public void saveRencanaAnggaran(RencanaAnggaranDTO rencanaAnggaranDTO) {
        rencanaAnggaranMapper.insert(toRencanaAnggaran(rencanaAnggaranDTO));
    }

    private RencanaAnggaran toRencanaAnggaran(RencanaAnggaranDTO rencanaAnggaranDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(rencanaAnggaranDTO, RencanaAnggaran.class);
    }

    public void deleteRencanaAnggaran(Integer id) {
        rencanaAnggaranMapper.deleteByPrimaryKey(id);
    }

    public void updateRencanaAnggaran(RencanaAnggaranDTO rencanaAnggaranDTO) {
        rencanaAnggaranMapper.updateByPrimaryKeySelective(toRencanaAnggaran(rencanaAnggaranDTO));
    }

    public SkemaPenelitian getDanaAnggaran(Integer idPenelitian) {
        Penelitian penelitian = penelitianMapper.selectByPrimaryKey(idPenelitian);
        SkemaPenelitian skemaPenelitian = skemaPenelitianMapper.selectByPrimaryKey(penelitian.getIdSkemaPenelitian());
        return skemaPenelitian;
    }
    
    public String getTotalFromPenelitian(Integer idPenelitian){
        Long total = 0L;
        List<RencanaAnggaranDTO> listRencanaAnggaranDTO = rencanaAnggaranMapper.getTotalFromPenelitian(idPenelitian);
        for(RencanaAnggaranDTO rencanaAnggaranDTO : listRencanaAnggaranDTO){
            total += rencanaAnggaranDTO.getTotalBiaya();
        }
        String totalInIdr = commonUtil.toIdrFormat(new Double(total));
        return totalInIdr;
    }
}
