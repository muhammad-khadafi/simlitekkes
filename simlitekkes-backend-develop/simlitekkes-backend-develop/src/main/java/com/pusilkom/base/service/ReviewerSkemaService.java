package com.pusilkom.base.service;

import com.pusilkom.base.dto.*;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class ReviewerSkemaService {

    @Autowired
    ReviewerSkemaMapper reviewerSkemaMapper;

    @Autowired
    PenelitianMapper penelitianMapper;

    @Autowired
    LogAktivitasService logAktivitasService;

    @Autowired
    WorkflowPenelitianMapper workflowPenelitianMapper;

    @Autowired
    StatusTahapPenelitianMapper statusTahapPenelitianMapper;

    @Autowired
    TimPenelitiMapper timPenelitiMapper;

    @Autowired
    KriteriaPenilaianPenelitianMapper kriteriaPenilaianPenelitianMapper;

    @Autowired
    CustomEmailSenderService customEmailSenderService;

    @Autowired
    UserwebMapper userwebMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserwebHasRoleMapper userwebHasRoleMapper;

    @Autowired
    PenelitiMapper penelitiMapper;

    public List<String> getTahunUsulan(String tipe, Long idOrganisasi) {
        List<String> tahunUsulanList = new ArrayList<String>();

        ReviewerSkemaExample reviewerSkemaExample = new ReviewerSkemaExample();
        if (tipe.equalsIgnoreCase("in")) {
            if (idOrganisasi.intValue() != 1) {
                reviewerSkemaExample.createCriteria().andIdOrganisasiEqualTo(idOrganisasi.intValue());
            }
        } else { //only PPSDM
            reviewerSkemaExample.createCriteria().andIdOrganisasiEqualTo(1);
        }

        reviewerSkemaExample.setOrderByClause("tahun_usulan desc");
        reviewerSkemaExample.setDistinct(true);

        List<ReviewerSkema> reviewerSkemaList = reviewerSkemaMapper.selectByExample(reviewerSkemaExample);

        for(ReviewerSkema a: reviewerSkemaList) {
            tahunUsulanList.add(a.getTahunUsulan());
        }
        return tahunUsulanList;
    }

    //============================AFTER REDEFINED REQS============================================

    public List<ReviewerDTO> getReviewerInternalByOrganization(Long idOrganisasi, String tahunUsulan) {
        return reviewerSkemaMapper.getReviewerInternalByOrganization(idOrganisasi.intValue(), tahunUsulan);
    }

    public List<ReviewerDTO> getReviewerEksternal(String tahunUsulan) {
        return reviewerSkemaMapper.getReviewerEksternal(tahunUsulan);
    }

    public boolean addReviewerInternal(ReviewerDTO reviewerDTO) {
        boolean isFailed = false;
        //check unik ke tabel reviewer_skema
        List<ReviewerSkema> reviewerSkemaList = checkForUnique(reviewerDTO,"internal");
        if (reviewerSkemaList.size() > 0) {
            isFailed = true;
        } else {
            reviewerDTO.setIsActive(true);
            reviewerDTO.setIsReviewerEksternal(false);
            reviewerDTO.setIsNew(true);
            reviewerDTO.setIsSeen(false);

            reviewerSkemaMapper.insert(toReviewerSkema(reviewerDTO));
            logAktivitasService.addLog(reviewerDTO.getUsername(),"POST", reviewerDTO.toString(),"/reviewer-internal");

            //tambahkan peran 'REVIEWER' untuk si peneliti, apabila belum ada sebelumnya
            addRoleReviewerToPeneliti(reviewerDTO);

        }
        return isFailed;
    }

    private void addRoleReviewerToPeneliti(ReviewerDTO reviewerDTO) {
        //tambahkan peran 'REVIEWER' untuk si peneliti, apabila belum ada sebelumnya
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRoleCodeEqualTo("REVIEWER");
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        Long idRoleReviewer = null;
        if (roleList.size() > 0)
            idRoleReviewer = roleList.get(0).getId();

        Integer idPenelitiReviewer = reviewerDTO.getIdPeneliti();
        Peneliti peneliti = penelitiMapper.selectByPrimaryKey(idPenelitiReviewer);
        Integer idUserReviewer = peneliti.getIdUser();

        UserwebHasRoleExample userwebHasRoleExample = new UserwebHasRoleExample();
        userwebHasRoleExample.createCriteria().andIdUserwebEqualTo(idUserReviewer.longValue()).andIdRoleEqualTo(idRoleReviewer);
        List<UserwebHasRole> userRoles = userwebHasRoleMapper.selectByExample(userwebHasRoleExample);
        if (userRoles.size() == 0) {
            //insert role REVIEWER
            UserwebHasRole userwebHasRole = new UserwebHasRole();
            userwebHasRole.setIdRole(idRoleReviewer);
            userwebHasRole.setIdUserweb(idUserReviewer.longValue());
            userwebHasRoleMapper.insert(userwebHasRole);

        }
    }

    public ReviewerSkema toReviewerSkema(ReviewerDTO reviewerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reviewerDTO, ReviewerSkema.class);
    }

    private List<ReviewerSkema> checkForUnique(ReviewerDTO reviewerDTO, String tipe) {
        ReviewerSkemaExample reviewerSkemaExample = new ReviewerSkemaExample();
        ReviewerSkemaExample.Criteria reviewerCriteria = reviewerSkemaExample.createCriteria();
        reviewerCriteria.andIdOrganisasiEqualTo(reviewerDTO.getIdOrganisasi());
        reviewerCriteria.andIdPenelitiEqualTo(reviewerDTO.getIdPeneliti());
        reviewerCriteria.andTahunUsulanEqualTo(reviewerDTO.getTahunUsulan());
        reviewerCriteria.andTahunPelaksanaanEqualTo(reviewerDTO.getTahunPelaksanaan());
        if (tipe.equalsIgnoreCase("internal"))
            reviewerCriteria.andIsReviewerEksternalEqualTo(false);
        else
            reviewerCriteria.andIsReviewerEksternalEqualTo(true);

        return reviewerSkemaMapper.selectByExample(reviewerSkemaExample);
    }

    public boolean addReviewerEksternal(ReviewerDTO reviewerDTO) {
        boolean isFailed = false;
        //check unik ke tabel reviewer_skema
        List<ReviewerSkema> reviewerSkemaList = checkForUnique(reviewerDTO,"eksternal");
        if (reviewerSkemaList.size() > 0) {
            isFailed = true;
        } else {
            reviewerDTO.setIsActive(true);
            reviewerDTO.setIsReviewerEksternal(true);
            reviewerDTO.setIsNew(true);
            reviewerDTO.setIsSeen(false);

            reviewerSkemaMapper.insert(toReviewerSkema(reviewerDTO));
            logAktivitasService.addLog(reviewerDTO.getUsername(),"POST", reviewerDTO.toString(),"/reviewer-eksternal");

            //tambahkan peran 'REVIEWER' untuk si peneliti, apabila belum ada sebelumnya
            addRoleReviewerToPeneliti(reviewerDTO);
        }
        return  isFailed;
    }

    public void updateReviewerInternal(ReviewerDTO reviewerDTO) {
        ReviewerSkema reviewerSkema = reviewerSkemaMapper.selectByPrimaryKey(reviewerDTO.getId());
        reviewerSkema.setIdDokumenOrganisasi(reviewerDTO.getIdDokumenOrganisasi());
        reviewerSkemaMapper.updateByPrimaryKey(reviewerSkema);
        logAktivitasService.addLog(reviewerDTO.getUsername(),"PUT", reviewerDTO.toString(),"/reviewer-internal");
    }

    public void updateReviewerEksternal(ReviewerDTO reviewerDTO) {
        ReviewerSkema reviewerSkema = reviewerSkemaMapper.selectByPrimaryKey(reviewerDTO.getId());
        reviewerSkema.setIdDokumenOrganisasi(reviewerDTO.getIdDokumenOrganisasi());
        reviewerSkemaMapper.updateByPrimaryKey(reviewerSkema);
        logAktivitasService.addLog(reviewerDTO.getUsername(),"PUT", reviewerDTO.toString(),"/reviewer-eksternal");
    }

    public int deleteReviewer(Integer idReviewer, String username) {
        int referred = 0;
        //check tabel penelitian
        PenelitianExample penelitianExample = new PenelitianExample();
        penelitianExample.createCriteria().andIdReviewerSkemaEqualTo(idReviewer);
        List<Penelitian> penelitianList = penelitianMapper.selectByExample(penelitianExample);
        if (penelitianList.size() > 0)
            referred = +1;
        else {
            reviewerSkemaMapper.deleteByPrimaryKey(idReviewer);
            logAktivitasService.addLog(username,"DELETE", null,"/reviewer/"+idReviewer+"/"+username);
        }
        return referred;
    }

    public List<ReviewerDTO> getAllReviewer(Integer idOrganisasi, String tahunUsulan) {
        return reviewerSkemaMapper.getAllReviewerForPlotting(idOrganisasi, tahunUsulan);
    }

    public List<PlottingReviewerDTO> getAllProposalForPlotting(Integer idOrganisasi, String tahunUsulan) {
        return reviewerSkemaMapper.getAllProposalForPlotting(idOrganisasi, tahunUsulan);
    }

    public boolean plotReviewer(PlottingReviewerDTO plottingReviewerDTO) throws MailException, MessagingException {
        boolean isFailed = false;

        //check apakah reviewer tidak sama dengan ketua tim penelitian
        ReviewerSkema reviewerSkema = reviewerSkemaMapper.selectByPrimaryKey(plottingReviewerDTO.getIdReviewerSkema());
        int idReviewer = reviewerSkema.getIdPeneliti().intValue();
        TimPenelitiExample timPenelitiExample = new TimPenelitiExample();
        timPenelitiExample.createCriteria().andIdPenelitianEqualTo(plottingReviewerDTO.getIdPenelitian()).andIdPeranPenelitiEqualTo(1);
        List<TimPeneliti> timPenelitiList = timPenelitiMapper.selectByExample(timPenelitiExample);
        int idKetuaTim = 0;
        if (timPenelitiList.size() > 0) {
            TimPeneliti ketuaTim = timPenelitiList.get(0);
            idKetuaTim = ketuaTim.getIdPeneliti();
        }

        if (idReviewer == idKetuaTim)
            return true;

        //update penelitian
        Penelitian penelitian = penelitianMapper.selectByPrimaryKey(plottingReviewerDTO.getIdPenelitian());
        penelitian.setIdReviewerSkema(plottingReviewerDTO.getIdReviewerSkema());
        penelitianMapper.updateByPrimaryKey(penelitian);

        //update workflow_penelitian
        WorkflowPenelitianExample workflowPenelitianExample = new WorkflowPenelitianExample();
        workflowPenelitianExample.createCriteria().andIdPenelitianEqualTo(plottingReviewerDTO.getIdPenelitian()).andIsLastEqualTo(true);
        List<WorkflowPenelitian> workflowPenelitianList = workflowPenelitianMapper.selectByExample(workflowPenelitianExample);
        if (workflowPenelitianList.size() > 0) {
            WorkflowPenelitian workflowPenelitian = workflowPenelitianList.get(0);
            workflowPenelitian.setIsLast(false);
            workflowPenelitianMapper.updateByExample(workflowPenelitian, workflowPenelitianExample);
        }

        //mendapatkan :id_status_tahap_penelitian_baru untuk PLOTTED
        Integer idStatusTahapPenelitian = getIdStatuTahapPenelitianByKodeStatus("PLOTTED");

        //insert into workflow_penelitian
        WorkflowPenelitian workflow = new WorkflowPenelitian();
        workflow.setIdPenelitian(plottingReviewerDTO.getIdPenelitian());
        workflow.setIdStatusTahapPenelitian(idStatusTahapPenelitian);
        workflow.setTimestampUpdate(Calendar.getInstance().getTime());
        workflow.setUsername(plottingReviewerDTO.getUsername());
        workflow.setIsLast(true);
        workflowPenelitianMapper.insert(workflow);

        logAktivitasService.addLog(plottingReviewerDTO.getUsername(),"PUT", plottingReviewerDTO.toString(),"/plotting-reviewer/");

        //kirim email ke reviewer untuk memberitahukan bahwa dia sudah diplotting
        Peneliti peneliti = penelitiMapper.selectByPrimaryKey(idReviewer);
        Userweb userReviewer = userwebMapper.selectByPrimaryKey(peneliti.getIdUser());
        String bodyEmail = "<h3>Hi " + userReviewer.getNama() + ",</h3>" +
                "<p>Anda baru saja mendapat tugas untuk melakukan review penelitian. Silakan akses aplikasi SIMLITEKKES dengan informasi berikut ini:</p>" +
                "<p>Peran : REVIEWER</p>" +
                "<p>Menu: Daftar Review dan Plotting ==> Daftar Review</p>" +
                "<br/><p>Salam,</p>" +
                "<p>Admin SIMLITEKKES</p>";

        customEmailSenderService.sendEmailHtml(userReviewer.getEmail(),
                "SIMLITEKKES - PLOTTING REVIEWER", bodyEmail);

        return isFailed;
    }

    private Integer getIdStatuTahapPenelitianByKodeStatus(String kodeStatus) {
        Integer idStatusTahapPenelitian = null;
        StatusTahapPenelitianExample statusTahapPenelitianExample = new StatusTahapPenelitianExample();
        statusTahapPenelitianExample.createCriteria().andKodeStatusEqualTo(kodeStatus);
        List<StatusTahapPenelitian> statusTahapPenelitianList = statusTahapPenelitianMapper.selectByExample(statusTahapPenelitianExample);
        if (statusTahapPenelitianList.size() > 0) {
            StatusTahapPenelitian statusTahapPenelitian = statusTahapPenelitianList.get(0);
            idStatusTahapPenelitian = statusTahapPenelitian.getId();
        }
        return idStatusTahapPenelitian;
    }

    public boolean removePlottingReviewer(Integer idPenelitian, String username) {
        boolean statusNotPlotted = false;

        //check apakah status terakhir adalah PLOTTED
        WorkflowPenelitianExample workflowPenelitianExample = new WorkflowPenelitianExample();
        workflowPenelitianExample.createCriteria().andIdPenelitianEqualTo(idPenelitian).andIsLastEqualTo(true);
        List<WorkflowPenelitian> workflowPenelitianList = workflowPenelitianMapper.selectByExample(workflowPenelitianExample);
        WorkflowPenelitian workflowPenelitian = null;
        if (workflowPenelitianList.size() > 0) {
            workflowPenelitian = workflowPenelitianList.get(0);
            StatusTahapPenelitian statusTahapPenelitian = statusTahapPenelitianMapper.selectByPrimaryKey(workflowPenelitian.getIdStatusTahapPenelitian());
            if (statusTahapPenelitian.getKodeStatus().compareToIgnoreCase("PLOTTED") != 0) //not equal to PLOTTED
                return true;
        }

        //remove reviewer_skema dari penelitian
        Penelitian penelitian = penelitianMapper.selectByPrimaryKey(idPenelitian);
        penelitian.setIdReviewerSkema(null);
        penelitianMapper.updateByPrimaryKey(penelitian);

        //update workflow_penelitian
        workflowPenelitian.setIsLast(false);
        workflowPenelitianMapper.updateByExample(workflowPenelitian, workflowPenelitianExample);

        //mendapatkan :id_status_tahap_penelitian_baru untuk SUBMITTED
        Integer idStatusTahapPenelitian = getIdStatuTahapPenelitianByKodeStatus("SUBMITTED");

        //insert into workflow_penelitian
        WorkflowPenelitian workflow = new WorkflowPenelitian();
        workflow.setIdPenelitian(idPenelitian);
        workflow.setIdStatusTahapPenelitian(idStatusTahapPenelitian);
        workflow.setTimestampUpdate(Calendar.getInstance().getTime());
        workflow.setUsername(username);
        workflow.setIsLast(true);
        workflowPenelitianMapper.insert(workflow);

        PlottingReviewerDTO plottingReviewerDTO = new PlottingReviewerDTO();
        plottingReviewerDTO.setIdPenelitian(idPenelitian);
        plottingReviewerDTO.setUsername(username);
        logAktivitasService.addLog(username,"PUT", plottingReviewerDTO.toString(),"/remove-plotting-reviewer/"+idPenelitian+"/"+username);

        return statusNotPlotted;
    }

    public List<PlottingReviewerDTO> getRiwayatReview(Integer idUser) {
        return reviewerSkemaMapper.getRiwayatReview(idUser);
    }

    public List<PlottingReviewerDTO> getRiwayatReview(String tahunUsulan, Integer idPeneliti) {
        return reviewerSkemaMapper.getRiwayatReviewByTahunUsulan(tahunUsulan, idPeneliti);
    }

    public List<HasilReviewDTO> getHasilReviewUsulan(Integer idSkemaPenelitian, Integer idPenelitian) {
        return reviewerSkemaMapper.getHasilReviewUsulan(idSkemaPenelitian, idPenelitian);
    }

    public List<DaftarReviewDTO> getDaftarReview(Integer idUser, String tahunUsulan)  {
        return reviewerSkemaMapper.getDaftarReview(idUser, tahunUsulan);
    }

    public Boolean checkPenilaianReviewIsExist(Integer idPenelitian)  {
        KriteriaPenilaianPenelitianExample ex = new KriteriaPenilaianPenelitianExample();
        ex.createCriteria().andIdPenelitianEqualTo(idPenelitian);
        List<KriteriaPenilaianPenelitian> listPenilaian =  kriteriaPenilaianPenelitianMapper.selectByExample(ex);
        Boolean result = !listPenilaian.isEmpty();
        return result;
    }

    public KriteriaPenilaianPenelitian toKriteriaPenilaianPenelitian(KriteriaPenilaianPenelitianDTO kriteriaPenilaianPenelitianDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(kriteriaPenilaianPenelitianDTO, KriteriaPenilaianPenelitian.class);
    }

    public void updateBulkKriteriaPenilaianPenelitian(List<KriteriaPenilaianPenelitianDTO> kriteriaPenilaianPenelitianDTOList) {
        for (KriteriaPenilaianPenelitianDTO krit: kriteriaPenilaianPenelitianDTOList) {
            kriteriaPenilaianPenelitianMapper.updateKriteriaPenilaian(krit);
            logAktivitasService.addLog(krit.getUsername(),"PUT", krit.toString(),"penelitian/" + krit.getIdPenelitian() + "/view-usulan");
        }
    }

    public void addBulkKriteriaPenilaianPenelitian(List<KriteriaPenilaianPenelitianDTO> kriteriaPenilaianPenelitianDTOList) {
        for (KriteriaPenilaianPenelitianDTO krit: kriteriaPenilaianPenelitianDTOList) {
            KriteriaPenilaianPenelitian newKrit = toKriteriaPenilaianPenelitian(krit);
            logAktivitasService.addLog(krit.getUsername(),"POST", krit.toString(),"penelitian/" + krit.getIdPenelitian() + "/view-usulan");
            kriteriaPenilaianPenelitianMapper.insert(newKrit);
        }
    }
}
