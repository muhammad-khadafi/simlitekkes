package com.pusilkom.base.service;

import com.pusilkom.base.dto.PenelitianDTO;
import com.pusilkom.base.dto.ReviewerSkemaDTO;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.*;
import com.pusilkom.base.util.DebugUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pusilz on 12/05/2020.
 */
@Service
@Transactional
public class ValidasiService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DebugUtil d;
    @Autowired
    PenelitianMapper penelitianMapper;
    @Autowired
    OrganisasiMapper organisasiMapper;
    @Autowired
    ReviewerSkemaMapper reviewerSkemaMapper;
    @Autowired
    PenelitiMapper penelitiMapper;

    @Autowired
    UserwebMapper userwebMapper;

    @Autowired
    UserwebHasRoleMapper userwebHasRoleMapper;

    //Validasi D14R kapus ppm, get data penelitian yg sudah di submit dan seinstitusi, return true if validation passed
    public Boolean getDataValidasiIsPenelitianSubmittedD14R(Integer idPenelitian, Integer idOrganisasi, String tahun) {
        List<PenelitianDTO> listPenelitian = penelitianMapper.getDataValidasiIsPenelitianSubmittedD14R(idPenelitian, idOrganisasi, tahun);
        Boolean result = (listPenelitian.size() > 0) ? true : false;
        return result;
    }

    //Validasi F14R pengelola pusat, get data penelitian yg sudah di submit, return true if validation passed
    public Boolean getDataValidasiIsPenelitianSubmittedF14R(Integer idPenelitian, String tahun) {
        List<PenelitianDTO> listPenelitian = penelitianMapper.getDataValidasiIsPenelitianSubmittedF14R(idPenelitian, tahun);
        Boolean result = (listPenelitian.size() > 0) ? true : false;
        return result;
    }

    //Validasi H14W dosen, get data penelitian dia yg belum di submit, return true if validation passed
    public Boolean getDataValidasiIsPenelitianNotSubmittedH14W(Integer idPenelitian, Integer userid) {
        List<PenelitianDTO> listPenelitian = penelitianMapper.getDataValidasiIsPenelitianNotSubmittedH14W(idPenelitian, userid);
        Boolean result = (listPenelitian.size() > 0) ? true : false;
        return result;
    }

    //Validasi role, user berasal dari poltekkes
    public Boolean getDataValidasiUserFromPoltekkes(Integer idOrganisasi) {
        OrganisasiExample organisasiExample = new OrganisasiExample();
        organisasiExample.createCriteria().andIdEqualTo(idOrganisasi).andIsPoltekkesEqualTo(true);
        List<Organisasi> listOrganisasi = organisasiMapper.selectByExample(organisasiExample);
        Boolean result = !listOrganisasi.isEmpty();
        return result;
    }

    //Validasi role, user berasal dari ppsdm
    public Boolean getDataValidasiUserFromPpsdm(Integer idOrganisasi) {
        Boolean isPpsdm = false;
        Organisasi userOrg = organisasiMapper.selectByPrimaryKey(idOrganisasi);
        if (userOrg.getId() == 1)
            isPpsdm = true;

        return isPpsdm;
    }

    //Validasi role, user reviewer memiliki penugasan
    public Boolean getDataValidasiReviewerHasPenugasan(Integer idUser) {
        List<ReviewerSkemaDTO> listReviewerSkemaDTO = reviewerSkemaMapper.getReviewerHasPenugasan(idUser);
        Boolean result = !listReviewerSkemaDTO.isEmpty();
        return result;
    }

    //Validasi I14R reviewer, get all data penelitian yang ditugaskan ke reviewer, return true if validation passed
    public Boolean getDataValidasiIsPenelitianAssignedI14R(Integer idUser, String tahunUsulan) {
        List<PenelitianDTO> listPenelitianDTO = penelitianMapper.getDataValidasiIsPenelitianAssignedI14R(idUser, tahunUsulan);
        Boolean result = !listPenelitianDTO.isEmpty();
        return result;
    }

    //Validasi H14R dosen, get data penelitian yang dia punyai, return true if validation passed
    public Boolean getDataValidasiIsOwnPenelitianH14R(Integer idUser, Integer idPenelitian) {
        List<PenelitianDTO> listPenelitian = penelitianMapper.getDataValidasiIsOwnPenelitianH14R(idUser, idPenelitian);
        Boolean result = listPenelitian.isEmpty();
        return result;
    }

    //Validasi data H16W, get data output penelitian dosen sebagai ketua, return true if validation passed
    public Boolean getDataValidasiOutputPenelitianPeranKetuaH16W(Integer idUser, Integer idOutput) {
        List<PenelitianDTO> listPenelitian = penelitianMapper.getDataValidasiOutputPenelitianPeranKetuaH16W(idUser, idOutput);
        Boolean result = !listPenelitian.isEmpty();
        return result;
    }

    //Validasi role dosen aktif
    public Boolean checkDosenisAktif(Integer idUser) {
        PenelitiExample penelitiExample = new PenelitiExample();
        penelitiExample.createCriteria().andNidnIsNotNull().andIsActiveEqualTo(true).andIdUserEqualTo(idUser);
        List<Peneliti> listPeneliti = penelitiMapper.selectByExample(penelitiExample);
        Boolean result = !listPeneliti.isEmpty();
        return result;
    }

    public Boolean checkUserIsKapusPPM(Integer userId, String roleCode) {
        Boolean isKapus, isPoltekkes;
        isKapus = isPoltekkes = false;

        //check roleCode = KAPUS_PPM
        if (roleCode.equalsIgnoreCase("kapus_ppm"))
            isKapus = true;

        //check user berasal dari poltekkes
        Userweb user = userwebMapper.selectByPrimaryKey(userId);
        Organisasi userOrg = organisasiMapper.selectByPrimaryKey(user.getIdOrganisasi());
        if (userOrg.getIsPoltekkes())
            isPoltekkes = true;

        return (isKapus && isPoltekkes);
    }

    public Boolean checkUserIsPengelolaPusat(Integer userId, String roleCode) {
        Boolean isPengelolaPusat, isPpsdm;
        isPengelolaPusat = isPpsdm = false;

        //check roleCode = PENGELOLA_PUSAT
        if (roleCode.equalsIgnoreCase("pengelola_pusat"))
            isPengelolaPusat = true;

        //check user berasal dari PPSDM
        Userweb user = userwebMapper.selectByPrimaryKey(userId);
        Organisasi userOrg = organisasiMapper.selectByPrimaryKey(user.getIdOrganisasi());
        if (userOrg.getId() == 1)
            isPpsdm = true;

        return (isPengelolaPusat && isPpsdm);
    }

    public Boolean checkUserIsOperatorPpm(Integer userId, String roleCode) {
        Boolean isOperatorPpm, isPoltekkes;
        isOperatorPpm = isPoltekkes = false;

        //check roleCode = OPERATOR_PPM
        if (roleCode.equalsIgnoreCase("operator_ppm"))
            isOperatorPpm = true;

        //check user berasal dari poltekkes
        Userweb user = userwebMapper.selectByPrimaryKey(userId);
        Organisasi userOrg = organisasiMapper.selectByPrimaryKey(user.getIdOrganisasi());
        if (userOrg.getIsPoltekkes())
            isPoltekkes = true;

        return (isOperatorPpm && isPoltekkes);
    }

    //Validasi H10W dosen, get peneliti with nidn, return true if validation passed
    public Boolean checkPenelitiWithNidnExistH10W(Integer idUser) {
        PenelitiExample ex = new PenelitiExample();
        ex.createCriteria().andIdUserEqualTo(idUser).andNidnIsNotNull();
        List<Peneliti> listPeneliti = penelitiMapper.selectByExample(ex);
        Boolean result = (listPeneliti.size() > 0) ? true : false;
        return result;
    }

    public Boolean checkUserIsReviewer(Integer userId, String roleCode) {
        Boolean isReviewer, hasPenugasan;
        isReviewer = hasPenugasan = false;

        //check roleCode = REVIEWER
        if (roleCode.equalsIgnoreCase("reviewer"))
            isReviewer = true;

        //check user mempunyai penugasan untuk review penelitian
        List<ReviewerSkemaDTO> reviewerSkemaList = reviewerSkemaMapper.getReviewerHasPenugasan(userId);
        if (reviewerSkemaList.size() > 0)
            hasPenugasan = true;

        return (isReviewer && hasPenugasan);
    }

    //Validasi I14W reviewer, check data penelitian yg ditugaskan kepadanya ada
    public Boolean getDataValidasiIsPenelitianI14W(Integer idPenelitian, Integer idUser) {
        List<PenelitianDTO> listPenelitian = penelitianMapper.getDataValidasiI14W(idPenelitian, idUser);
        Boolean result = (listPenelitian.size() > 0) ? true : false;
        return result;
    }

    public Boolean checkPenelitiIsSameOrganization(Integer idPeneliti, Integer idOrganisasi) {
        Peneliti peneliti = penelitiMapper.selectByPrimaryKey(idPeneliti);
        Boolean result = false;
        if (peneliti != null && peneliti.getIdOrganisasi() == idOrganisasi) {
            result = true;
        }
        return result;
    }

    //Validasi role, user berasal dari ppsdm
    public Boolean checkUsernameIsUnique(String username) {
        Boolean isUnique = true;
        UserwebExample ex = new UserwebExample();
        ex.createCriteria().andUsernameLikeInsensitive(username);
        List<Userweb> userweb = userwebMapper.selectByExample(ex);
        if (userweb.size() > 0)
            isUnique = false;

        return isUnique;
    }

    //Cek apakah nidn sudah digunakan
    public Boolean checkNidnIsUsed(String nidn) {
        PenelitiExample ex = new PenelitiExample();
        ex.createCriteria().andNidnEqualTo(nidn);
        List<Peneliti> listPeneliti = penelitiMapper.selectByExample(ex);
        Boolean result = (listPeneliti.size() > 0) ? true : false;
        return result;
    }
}
