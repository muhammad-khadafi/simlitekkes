package com.pusilkom.base.service;

import com.pusilkom.base.dto.OrganisasiDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrganisasiService {

    @Autowired
    OrganisasiMapper organisasiMapper;

    @Autowired
    UserwebMapper userwebMapper;

    @Autowired
    PenelitiMapper penelitiMapper;

    @Autowired
    BeritaMapper beritaMapper;

    @Autowired
    DokumenOrganisasiMapper dokumenOrganisasiMapper;

    @Autowired
    ReviewerSkemaMapper reviewerSkemaMapper;

    @Autowired
    LogAktivitasService logAktivitasService;

    public Organisasi getProfilPoltekkesByUserId(Integer userId) {
        Userweb userweb = userwebMapper.selectByPrimaryKey(userId);
        return organisasiMapper.selectByPrimaryKey(userweb.getIdOrganisasi());
    }

    public boolean updateProfilPoltekkes(OrganisasiDTO organisasiDTO) {
        boolean failed = false;
        List<Organisasi> organisasiList = checkUniqueForUpdate(organisasiDTO);
        if (organisasiList.size() > 0)
            failed = true;
        else {
            organisasiMapper.updateByPrimaryKeySelective(toOrganisasi(organisasiDTO));
            logAktivitasService.addLog(organisasiDTO.getUsername(), "PUT", organisasiDTO.toString(),"/profil-poltekkes");
        }

        return failed;
    }

    public Organisasi toOrganisasi(OrganisasiDTO organisasiDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(organisasiDTO, Organisasi.class);
    }

    public List<Organisasi> getAllOrganisasi() {
        OrganisasiExample organisasiExample = new OrganisasiExample();
        organisasiExample.setOrderByClause("nama");
        List<Organisasi> organisasiList = organisasiMapper.selectByExample(organisasiExample);
        return  organisasiList;
    }

    public Organisasi getOrganisasiById(Integer idOrganisasi) {
        return organisasiMapper.selectByPrimaryKey(idOrganisasi);
    }

    public boolean addOrganisasi(OrganisasiDTO organisasiDTO) {
        boolean failed = false;
        //check unik utk nama organisasi
        OrganisasiExample organisasiExample = new OrganisasiExample();
        organisasiExample.createCriteria().andNamaLikeInsensitive(organisasiDTO.getNama());
        List<Organisasi> organisasiList = organisasiMapper.selectByExample(organisasiExample);
        if (organisasiList.size() > 0)
            failed = true;
        else {
            organisasiMapper.insert(toOrganisasi(organisasiDTO));
            logAktivitasService.addLog(organisasiDTO.getUsername(),"POST", organisasiDTO.toString(),"/organisasi/0");
        }

        return failed;
    }

    public boolean updateOrganisasi(OrganisasiDTO organisasiDTO) {
        boolean failed = false;
        //check unik untuk nama organisasi
        List<Organisasi> organisasiList = checkUniqueForUpdate(organisasiDTO);
        if (organisasiList.size() > 0)
            failed = true;
        else {
            organisasiMapper.updateByPrimaryKeySelective(toOrganisasi(organisasiDTO));
            logAktivitasService.addLog(organisasiDTO.getUsername(), "PUT", organisasiDTO.toString(),"/organisasi/"+organisasiDTO.getId());
        }

        return failed;
    }

    public List<Organisasi> checkUniqueForUpdate(OrganisasiDTO organisasiDTO) {
        OrganisasiExample organisasiExample = new OrganisasiExample();
        organisasiExample.createCriteria().andIdNotEqualTo(organisasiDTO.getId()).andNamaLikeInsensitive(organisasiDTO.getNama());
        return organisasiMapper.selectByExample(organisasiExample);
    }

    public int deleteOrganisasi(Long idOrganisasi, String username) {
        int referred = 0;

        //check tabel userweb
        UserwebExample userwebExample = new UserwebExample();
        userwebExample.createCriteria().andIdOrganisasiEqualTo(idOrganisasi.intValue());
        List<Userweb> userwebList = userwebMapper.selectByExample(userwebExample);
        if (userwebList.size() > 0)
            return referred+1;

        //check tabel peneliti
        PenelitiExample penelitiExample = new PenelitiExample();
        penelitiExample.createCriteria().andIdOrganisasiEqualTo(idOrganisasi.intValue());
        List<Peneliti> penelitiList = penelitiMapper.selectByExample(penelitiExample);
        if (penelitiList.size() > 0)
            return referred+1;

        organisasiMapper.deleteByPrimaryKey(idOrganisasi.intValue());
        logAktivitasService.addLog(username,"DELETE", idOrganisasi.toString(), "/organisasi");

        return referred;
    }

    public OrganisasiDTO getTemaTopikUnggulanByUserId(Integer userId) {
        Userweb userweb = userwebMapper.selectByPrimaryKey(userId);
        return organisasiMapper.getTemaTopikUnggulan(userweb.getIdOrganisasi());
    }

    public void updateTemaTopikUnggulan(OrganisasiDTO organisasiDTO) {
        organisasiMapper.updateByPrimaryKeySelective(toOrganisasi(organisasiDTO));
        logAktivitasService.addLog(organisasiDTO.getUsername(), "PUT", organisasiDTO.toString(),"/topik-unggulan");
    }

    public Organisasi getOrganisasiByKodePddikti(String kodePddikti) {
        OrganisasiExample ex = new OrganisasiExample();
        ex.createCriteria().andKodePddiktiEqualTo(kodePddikti);
        List<Organisasi> list = organisasiMapper.selectByExample(ex);
        Organisasi org = new Organisasi();
        if (list.size() > 0) org = list.get(0);
        return org;
    }

    public List<Organisasi> getAllOrganisasiNonPoltekkes() {
        OrganisasiExample organisasiExample = new OrganisasiExample();
        organisasiExample.createCriteria().andIsPoltekkesEqualTo(false);
        organisasiExample.setOrderByClause("nama");
        List<Organisasi> organisasiList = organisasiMapper.selectByExample(organisasiExample);
        return  organisasiList;
    }

    public List<Organisasi> getOrganisasiList(Integer organisationId) {
        List<Organisasi> organisasiList = new ArrayList<>();
        if (organisationId == 1) {
            organisasiList =  getAllOrganisasi();
        } else {
            organisasiList.add(getOrganisasiById(organisationId));
        }
        return organisasiList;
    }
}
