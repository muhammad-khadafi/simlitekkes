package com.pusilkom.base.controller;

import com.pusilkom.base.dto.*;
import com.pusilkom.base.service.ReviewerSkemaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ReviewerSkemaController {

    @Autowired
    ReviewerSkemaService reviewerSkemaService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/tahun-usulan/{tipe}/{id}", method = RequestMethod.GET)
    public ResponseEntity getTahunUsulan(@PathVariable("tipe") String tipe, @PathVariable("id") Long id) {
        ResponseEntity responseEntity = null;

        try {
            List<String> tahunUsulanList = reviewerSkemaService.getTahunUsulan(tipe, id);
            responseEntity = ResponseEntity.ok(tahunUsulanList);

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/reviewer-internal/{idOrganisasi}/{tahunUsulan}", method = RequestMethod.GET)
    public ResponseEntity getReviewerInternalByOrganization(@PathVariable("idOrganisasi")Long idOrganisasi, @PathVariable("tahunUsulan") String tahunUsulan) {
        ResponseEntity responseEntity = null;
        try {
            List<ReviewerDTO> reviewerList = reviewerSkemaService.getReviewerInternalByOrganization(idOrganisasi, tahunUsulan);
            responseEntity = ResponseEntity.ok(reviewerList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/reviewer-eksternal/{tahunUsulan}", method = RequestMethod.GET)
    public ResponseEntity getReviewerEksternal(@PathVariable("tahunUsulan") String tahunUsulan) {
        ResponseEntity responseEntity = null;
        try {
            List<ReviewerDTO> reviewerList = reviewerSkemaService.getReviewerEksternal(tahunUsulan);
            responseEntity = ResponseEntity.ok(reviewerList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/reviewer-internal", method = RequestMethod.POST)
    public ResponseEntity addReviewerInternal(@RequestBody ReviewerDTO reviewerDTO) {
        ResponseEntity responseEntity = null;

        try {
            boolean failed = reviewerSkemaService.addReviewerInternal(reviewerDTO);
            if (!failed)
                responseEntity = ResponseEntity.ok().build();
            else
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/reviewer-eksternal", method = RequestMethod.POST)
    public ResponseEntity addReviewerEksternal(@RequestBody ReviewerDTO reviewerDTO) {
        ResponseEntity responseEntity = null;
        try {
            boolean failed = reviewerSkemaService.addReviewerEksternal(reviewerDTO);
            if (!failed)
                responseEntity = ResponseEntity.ok().build();
            else
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/reviewer-internal", method = RequestMethod.PUT)
    public ResponseEntity updateReviewerInternal(@RequestBody ReviewerDTO reviewerDTO) {
        ResponseEntity responseEntity = null;
        try {
            reviewerSkemaService.updateReviewerInternal(reviewerDTO);
            responseEntity = ResponseEntity.ok().build();
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/reviewer-eksternal", method = RequestMethod.PUT)
    public ResponseEntity updateReviewerEksternal(@RequestBody ReviewerDTO reviewerDTO) {
        ResponseEntity responseEntity = null;
        try {
            reviewerSkemaService.updateReviewerEksternal(reviewerDTO);
            responseEntity = ResponseEntity.ok().build();
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value="/reviewer/{idReviewer}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteReviewer(@PathVariable("idReviewer") Integer idReviewer, @PathVariable("username") String username) {
        ResponseEntity responseEntity = null;
        try {
            int referred = reviewerSkemaService.deleteReviewer(idReviewer, username);

            if (referred == 0)
                responseEntity = ResponseEntity.ok().build();
            else
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/reviewer/{idOrganisasi}/{tahunUsulan}", method = RequestMethod.GET)
    public ResponseEntity getAllReviewerForPlotting(@PathVariable("idOrganisasi") Integer idOrganisasi, @PathVariable("tahunUsulan") String tahunUsulan) {
        ResponseEntity responseEntity = null;
        try {
            List<ReviewerDTO> reviewerList = reviewerSkemaService.getAllReviewer(idOrganisasi, tahunUsulan);
            responseEntity = ResponseEntity.ok(reviewerList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/plotting-reviewer/{idOrganisasi}/{tahunUsulan}", method = RequestMethod.GET)
    public ResponseEntity getAllProposalForPlotting(@PathVariable("idOrganisasi") Integer idOrganisasi, @PathVariable("tahunUsulan") String tahunUsulan) {
        ResponseEntity responseEntity = null;
        try {
            List<PlottingReviewerDTO> proposalForPlotting = reviewerSkemaService.getAllProposalForPlotting(idOrganisasi, tahunUsulan);
            responseEntity = ResponseEntity.ok(proposalForPlotting);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/plotting-reviewer", method = RequestMethod.PUT)
    public ResponseEntity plottingReviewer(@RequestBody PlottingReviewerDTO plottingReviewerDTO) {
        ResponseEntity responseEntity = null;
        try {
            boolean isFailed = reviewerSkemaService.plotReviewer(plottingReviewerDTO);
            if (!isFailed)
                responseEntity = ResponseEntity.ok().build();
            else
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value="/remove-plotting-reviewer/{idPenelitian}/{username}", method = RequestMethod.PUT)
    public ResponseEntity removePlottingReviewer(@PathVariable("idPenelitian") Integer idPenelitian, @PathVariable("username") String username) {
        ResponseEntity responseEntity = null;
        try {
            boolean statusNotPlotted = reviewerSkemaService.removePlottingReviewer(idPenelitian, username);

            if (!statusNotPlotted)
                responseEntity = ResponseEntity.ok().build();
            else
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).build();

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/riwayat-review/{idUser}", method = RequestMethod.GET)
    public ResponseEntity getRiwayatReview(@PathVariable("idUser") Integer idUser) {
        ResponseEntity responseEntity = null;
        try {
            List<PlottingReviewerDTO> riwayatList = reviewerSkemaService.getRiwayatReview(idUser);
            responseEntity = ResponseEntity.ok(riwayatList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/riwayat-review/{tahunUsulan}/{idPeneliti}", method = RequestMethod.GET)
    public ResponseEntity getRiwayatReview(@PathVariable("tahunUsulan") String tahunUsulan, @PathVariable("idPeneliti") Integer idPeneliti) {
        ResponseEntity responseEntity = null;
        try {
            List<PlottingReviewerDTO> riwayatList = reviewerSkemaService.getRiwayatReview(tahunUsulan, idPeneliti);
            responseEntity = ResponseEntity.ok(riwayatList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/hasil-review/{idSkemaPenelitian}/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity getHasilReviewUsulan(@PathVariable("idSkemaPenelitian") Integer idSkemaPenelitian, @PathVariable("idPenelitian") Integer idPenelitian) {
        ResponseEntity responseEntity = null;
        try {
            List<HasilReviewDTO> listHasilReviewDTO = reviewerSkemaService.getHasilReviewUsulan(idSkemaPenelitian, idPenelitian);
            responseEntity = ResponseEntity.ok(listHasilReviewDTO);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/review/{idUser}/{tahunUsulan}", method = RequestMethod.GET)
    public ResponseEntity getDaftarReview(@PathVariable("idUser") Integer idUser, @PathVariable("tahunUsulan") String tahunUsulan) {
        ResponseEntity responseEntity = null;
        try {
            List<DaftarReviewDTO> listDaftarReviewDTO = reviewerSkemaService.getDaftarReview(idUser, tahunUsulan);
            responseEntity = ResponseEntity.ok(listDaftarReviewDTO);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/review/checkIsExist/{idPenelitian}", method = RequestMethod.GET)
    public ResponseEntity checkPenilaianReviewIsExist(@PathVariable("idPenelitian") Integer idPenelitian) {
        ResponseEntity responseEntity = null;
        try {
            Boolean result = reviewerSkemaService.checkPenilaianReviewIsExist(idPenelitian);
            responseEntity = ResponseEntity.ok(result);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/hasil-review", method = RequestMethod.PUT)
    public ResponseEntity updateBulkKriteriaPenilaianPenelitian(@RequestBody List<KriteriaPenilaianPenelitianDTO> kriteriaPenilaianPenelitianDTOList) {
        ResponseEntity responseEntity = null;
        try {
            reviewerSkemaService.updateBulkKriteriaPenilaianPenelitian(kriteriaPenilaianPenelitianDTOList);
            responseEntity = ResponseEntity.ok().build();
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/hasil-review", method = RequestMethod.POST)
    public ResponseEntity addBulkKriteriaPenilaianPenelitian(@RequestBody List<KriteriaPenilaianPenelitianDTO> kriteriaPenilaianPenelitianDTOList) {
        ResponseEntity responseEntity = null;
        try {
            reviewerSkemaService.addBulkKriteriaPenilaianPenelitian(kriteriaPenilaianPenelitianDTOList);
            responseEntity = ResponseEntity.ok().build();
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
