package com.pusilkom.base.service;

import com.pusilkom.base.dto.*;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhammad.khadafi on 2020-03-11 08:50:51.
 */

@Service
@Transactional
public class IdentitasPengusulService {

    @Autowired
    PenelitiMapper penelitiMapper;
    @Autowired
    PenelitianMapper penelitianMapper;
    @Autowired
    SkemaPenelitianMapper skemaPenelitianMapper;
    @Autowired
    TingkatKesiapanTeknologiMapper tingkatKesiapanTeknologiMapper;
    @Autowired
    IndikatorTktMapper indikatorTktMapper;
    @Autowired
    RumpunIlmuMapper rumpunIlmuMapper;
    @Autowired
    PenelitianService penelitianService;
    @Autowired
    TopikMapper topikMapper;
    @Autowired
    TemaMapper temaMapper;
    @Autowired
    TimPenelitiMapper timPenelitiMapper;
    @Autowired
    OrganisasiMapper organisasiMapper;
    @Autowired
    PenelitiService penelitiService;

    public SkemaPenelitian getMinMaxTkt(Integer idPenelitian) {
        Penelitian penelitian = penelitianMapper.selectByPrimaryKey(idPenelitian);
        SkemaPenelitian skemaPenelitian = skemaPenelitianMapper.selectByPrimaryKey(penelitian.getIdSkemaPenelitian());
        return skemaPenelitian;
    }

    public PengukuranTktDTO getTktData(Integer idJenisTkt, Integer activeLvl) {
        PengukuranTktDTO pengukuranTktDTO = new PengukuranTktDTO();

        TingkatKesiapanTeknologiExample tingkatKesiapanTeknologiExample = new TingkatKesiapanTeknologiExample();
        tingkatKesiapanTeknologiExample.createCriteria().andIdKelompokTktEqualTo(idJenisTkt).andLevelEqualTo(activeLvl.shortValue());
        List<TingkatKesiapanTeknologi> tingkatKesiapanTeknologi = tingkatKesiapanTeknologiMapper.selectByExample(tingkatKesiapanTeknologiExample);
        pengukuranTktDTO.setDefinisi(tingkatKesiapanTeknologi.get(0).getDefinisi());

        IndikatorTktExample indikatorTktExample = new IndikatorTktExample();
        indikatorTktExample.createCriteria().andIdTingkatKesiapanTeknologiEqualTo(tingkatKesiapanTeknologi.get(0).getId());
        List<IndikatorTkt> indikatorTktList = indikatorTktMapper.selectByExample(indikatorTktExample);
        List<IndikatorTktDTO> indikatorTktDTOList = new ArrayList<>();

        for (IndikatorTkt indikatorTkt :
                indikatorTktList) {
            indikatorTktDTOList.add(toIndikatorTktDTO(indikatorTkt));
        }

        pengukuranTktDTO.setIndikatorTktList(indikatorTktDTOList);

        return pengukuranTktDTO;
    }

    public IndikatorTktDTO toIndikatorTktDTO(IndikatorTkt indikatorTkt) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(indikatorTkt, IndikatorTktDTO.class);
    }

    public Penelitian toPenelitian(PenelitianDTO penelitianDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(penelitianDTO, Penelitian.class);
    }

    public PenelitianDTO toPenelitianDTO(Penelitian penelitian) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(penelitian, PenelitianDTO.class);
    }

    public List<RumpunIlmu> getRumpunIlmu(Integer idParent) {
        List<RumpunIlmu> listRumpunIlmu;
        RumpunIlmuExample rumpunIlmuExample = new RumpunIlmuExample();
        if (idParent.equals(0)) {
            rumpunIlmuExample.createCriteria().andIdParentRumpunIlmuIsNull();
        } else {
            rumpunIlmuExample.createCriteria().andIdParentRumpunIlmuEqualTo(idParent);
        }
        listRumpunIlmu = rumpunIlmuMapper.selectByExample(rumpunIlmuExample);
        for (RumpunIlmu rumpunIlmu:
             listRumpunIlmu) {
            rumpunIlmu.setNama(rumpunIlmu.getNama() + " - " + rumpunIlmu.getKode());
        }
        return listRumpunIlmu;
    }

    public List<LamaKegiatanDto> getLamaKegiatan(Integer idPenelitian) {
        Penelitian penelitian = penelitianMapper.selectByPrimaryKey(idPenelitian);
        SkemaPenelitian skemaPenelitian = skemaPenelitianMapper.selectByPrimaryKey(penelitian.getIdSkemaPenelitian());
        Integer tahunMax = skemaPenelitian.getJangkaWaktuMax().intValue();
        Integer tahunMin = skemaPenelitian.getJangkaWaktuMin().intValue();
        List<LamaKegiatanDto> lamaKegiatan = new ArrayList<>();
        for (Integer i = tahunMin; i <= tahunMax; i++) {
            LamaKegiatanDto lamaKegiatanDto = new LamaKegiatanDto();
            lamaKegiatanDto.setLamaKegiatan(i);
            lamaKegiatan.add(lamaKegiatanDto);
        }
        return lamaKegiatan;
    }

    public void putIdentitasUsulan(Integer idPenelitian, PenelitianDTO penelitianDTO) {
        TingkatKesiapanTeknologiExample tingkatKesiapanTeknologiExample = new TingkatKesiapanTeknologiExample();
        tingkatKesiapanTeknologiExample.createCriteria()
                .andIdKelompokTktEqualTo(penelitianDTO.getIdKelompokTkt())
                .andLevelEqualTo(penelitianDTO.getTktSaatIni());
        List<TingkatKesiapanTeknologi> tktSaatIni = tingkatKesiapanTeknologiMapper.selectByExample(tingkatKesiapanTeknologiExample);

        TingkatKesiapanTeknologiExample tingkatKesiapanTeknologiExample2 = new TingkatKesiapanTeknologiExample();
        tingkatKesiapanTeknologiExample2.createCriteria()
                .andIdKelompokTktEqualTo(penelitianDTO.getIdKelompokTkt())
                .andLevelEqualTo(penelitianDTO.getTktTarget());
        List<TingkatKesiapanTeknologi> tktTarget = tingkatKesiapanTeknologiMapper.selectByExample(tingkatKesiapanTeknologiExample2);

        penelitianDTO.setIdTingkatKesiapanTeknologi(tktSaatIni.get(0).getId());
        penelitianDTO.setIdTingkatKesiapanTeknologiTarget(tktTarget.get(0).getId());
        penelitianDTO.setId(idPenelitian);

        penelitianMapper.updateByPrimaryKeySelective(toPenelitian(penelitianDTO));
    }

    public PenelitianDTO getIdentitasUsulan(Integer idPenelitian) {
        PenelitianDTO penelitianDTO = toPenelitianDTO(penelitianService.getPenelitianById(idPenelitian));

        if (penelitianDTO.getIdTingkatKesiapanTeknologi() != null) {
            TingkatKesiapanTeknologiExample tingkatKesiapanTeknologiExample = new TingkatKesiapanTeknologiExample();
            tingkatKesiapanTeknologiExample.createCriteria()
                    .andIdEqualTo(penelitianDTO.getIdTingkatKesiapanTeknologi())
                    .andIdKelompokTktEqualTo(penelitianDTO.getIdKelompokTkt());
            penelitianDTO.setTktSaatIni(tingkatKesiapanTeknologiMapper.selectByExample(
                    tingkatKesiapanTeknologiExample).get(0).getLevel());
        }

        if (penelitianDTO.getIdTingkatKesiapanTeknologiTarget() != null) {
            TingkatKesiapanTeknologiExample tingkatKesiapanTeknologiExample2 = new TingkatKesiapanTeknologiExample();
            tingkatKesiapanTeknologiExample2.createCriteria()
                    .andIdEqualTo(penelitianDTO.getIdTingkatKesiapanTeknologiTarget())
                    .andIdKelompokTktEqualTo(penelitianDTO.getIdKelompokTkt());

            penelitianDTO.setTktTarget(tingkatKesiapanTeknologiMapper.selectByExample(
                    tingkatKesiapanTeknologiExample2).get(0).getLevel());
        }

        RumpunIlmu rumpunIlmu = null;
        RumpunIlmu rumpunIlmuLv2 = null;
        RumpunIlmu rumpunIlmuLv1 = null;

        if (penelitianDTO.getIdRumpunIlmu() != null)
            rumpunIlmu = rumpunIlmuMapper.selectByPrimaryKey(penelitianDTO.getIdRumpunIlmu());
        if (rumpunIlmu != null)
            rumpunIlmuLv2 = rumpunIlmuMapper.selectByPrimaryKey(rumpunIlmu.getIdParentRumpunIlmu());
        if (rumpunIlmuLv2 != null)
            rumpunIlmuLv1 = rumpunIlmuMapper.selectByPrimaryKey(rumpunIlmuLv2.getIdParentRumpunIlmu());

        penelitianDTO.setIdIlmuLv3(rumpunIlmu != null ? rumpunIlmu.getId() : null);
        penelitianDTO.setIdIlmuLv2(rumpunIlmuLv2 != null ? rumpunIlmuLv2.getId() : null);
        penelitianDTO.setIdIlmuLv1(rumpunIlmuLv1 != null ? rumpunIlmuLv1.getId() : null);


        Topik topik = null;
        Tema tema = null;

        if (penelitianDTO.getIdTopik() != null)
            topik = topikMapper.selectByPrimaryKey(penelitianDTO.getIdTopik());

        if (topik != null)
            tema = temaMapper.selectByPrimaryKey(topik.getIdTema());

        penelitianDTO.setIdTema(tema != null ? tema.getId() : null);

        if (penelitianDTO.getTahunSelesaiPelaksanaan() != null && penelitianDTO.getTahunMulaiPelaksanaan() != null) {
            Integer lamaKegiatan = Integer.parseInt(penelitianDTO.getTahunSelesaiPelaksanaan())
                    - Integer.parseInt(penelitianDTO.getTahunMulaiPelaksanaan()) + 1;
            penelitianDTO.setLamaKegiatan(lamaKegiatan);
        }

        return penelitianDTO;

    }

    public IdentitasUsulanPreviewDTO getIdentitasUsulanPreview(Integer idPenelitian) {
        return penelitianMapper.getIdentitasUsulanPreview(idPenelitian);
    }

    public SkemaPenelitian getSkemaPenelitianByIdPenelitian(Integer idPenelitian) {
        Penelitian penelitian = penelitianMapper.selectByPrimaryKey(idPenelitian);
        SkemaPenelitian skemaPenelitian = skemaPenelitianMapper.selectByPrimaryKey(penelitian.getIdSkemaPenelitian());
        return skemaPenelitian;
    }

    public List<PenelitiDTO> getAnggotaPeneliti(Boolean isPoltekkes, Integer idPenelitian) {
        return penelitiMapper.getAnggotaPeneliti(isPoltekkes, idPenelitian);
    }

    public void addAnggotaPeneliti(Integer idPenelitian, String tugasPenelitian, BigDecimal alokasiJam, List<PenelitiDTO> penelitiDTOList) {
        for (PenelitiDTO penelitiDTO:
             penelitiDTOList) {
            TimPeneliti timPeneliti = new TimPeneliti();
            timPeneliti.setIdPeneliti(penelitiDTO.getId());
            timPeneliti.setIdPenelitian(idPenelitian);
            timPeneliti.setIdPeranPeneliti(2);
            timPeneliti.setIdOrganisasi(penelitiDTO.getIdOrganisasi());
            timPeneliti.setIdBidangKeahlianSaatIni(penelitiDTO.getIdBidangKeahlian());
            timPeneliti.setTugasPenelitian(tugasPenelitian);
            timPeneliti.setAlokasiJamPerMinggu(alokasiJam);
            timPenelitiMapper.insert(timPeneliti);
        }
    }

    public List<PenelitiDTO> getTimPeneliti(Integer idPenelitian, Boolean isPoltekkes) {
        return penelitiMapper.selectTimPeneliti(idPenelitian, isPoltekkes);
    }

    public void deleteTimPeneliti(Integer id) {
        timPenelitiMapper.deleteByPrimaryKey(id);
    }

    public List<Organisasi> getOrganisasiNonPoltekkes() {
        OrganisasiExample organisasiExample = new OrganisasiExample();
        organisasiExample.createCriteria().andIsPoltekkesEqualTo(false);
        return organisasiMapper.selectByExample(organisasiExample);
    }

    public List<RumpunIlmu> getBidangKeahlian() {
        RumpunIlmuExample rumpunIlmuExample = new RumpunIlmuExample();
        rumpunIlmuExample.createCriteria().andLevelEqualTo(Short.valueOf("3"));
        rumpunIlmuExample.setOrderByClause("nama");
        return rumpunIlmuMapper.selectByExample(rumpunIlmuExample);
    }

    public void addAnggotaPeneliti(Integer idPenelitian, PenelitiDTO penelitiDTO) {
        // insert peneliti
        Peneliti peneliti = penelitiService.toPeneliti(penelitiDTO);
        penelitiMapper.insert(peneliti);

        // insert tim peneliti
        TimPeneliti timPeneliti = new TimPeneliti();
        timPeneliti.setIdPeneliti(peneliti.getId());
        timPeneliti.setIdPenelitian(idPenelitian);
        timPeneliti.setIdPeranPeneliti(2);
        timPeneliti.setIdOrganisasi(penelitiDTO.getIdOrganisasi());
        timPeneliti.setIdBidangKeahlianSaatIni(penelitiDTO.getIdBidangKeahlian());
        timPeneliti.setTugasPenelitian(penelitiDTO.getTugasPenelitian());
        timPeneliti.setAlokasiJamPerMinggu(penelitiDTO.getAlokasiJam());
        timPenelitiMapper.insert(timPeneliti);
    }

    public List<TimPeneliti> getAnggotaTimPeneliti(Integer idPenelitian) {
        TimPenelitiExample ex = new TimPenelitiExample();
        ex.createCriteria().andIdPenelitianEqualTo(idPenelitian).andIdPeranPenelitiEqualTo(2);
        return timPenelitiMapper.selectByExample(ex);
    }
}
