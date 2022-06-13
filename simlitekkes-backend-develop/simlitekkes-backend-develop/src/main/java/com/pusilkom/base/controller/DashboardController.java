package com.pusilkom.base.controller;

import com.pusilkom.base.dto.DashboardColumnChartDTO;
import com.pusilkom.base.dto.DashboardPieChartDTO;
import com.pusilkom.base.dto.DashboardTableDTO;
import com.pusilkom.base.service.DashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/dashboard/peneliti-per-pendidikan-terakhir/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getPenelitiPerPendidikanTerakhir(@PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        try {
            List<DashboardPieChartDTO> pieChartDTOList = dashboardService.getPenelitiPerPendidikanTerakhir(idOrganisasi);
            responseEntity = ResponseEntity.ok(pieChartDTOList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dashboard/peneliti-reviewer-per-bidang-ilmu/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getPenelitiReviewerPerBidangIlmu(@PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        try {
            List<DashboardTableDTO> tableDTOList = dashboardService.getPenelitiReviewerPerBidangIlmu(idOrganisasi);
            responseEntity = ResponseEntity.ok(tableDTOList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dashboard/proposal-per-skema/{tipe}/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getProposalPerSkema(@PathVariable("tipe") String tipe, @PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        try {
            if (tipe.equalsIgnoreCase("tabel")) {
                List<DashboardTableDTO> tableDTOList = dashboardService.getProposalPerSkemaForTable(idOrganisasi);
                responseEntity = ResponseEntity.ok(tableDTOList);
            } else if (tipe.equalsIgnoreCase("chart")) {
                DashboardColumnChartDTO columnChartDTO = dashboardService.getProposalPerSkemaForChart(idOrganisasi);
                responseEntity = ResponseEntity.ok(columnChartDTO);
            }

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dashboard/proposal-per-kelompok-tkt/{tipe}/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getProposalPerKelompokTkt(@PathVariable("tipe") String tipe, @PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        try {
            if (tipe.equalsIgnoreCase("tabel")) {
                List<DashboardTableDTO> tableDTOList = dashboardService.getProposalPerKelompokTktForTable(idOrganisasi);
                responseEntity = ResponseEntity.ok(tableDTOList);
            } else if (tipe.equalsIgnoreCase("chart")) {
                DashboardColumnChartDTO columnChartDTO = dashboardService.getProposalPerKelompokTktForChart(idOrganisasi);
                responseEntity = ResponseEntity.ok(columnChartDTO);
            }

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dashboard/proposal-per-topik/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getProposalPerTopik(@PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        try {
            List<DashboardTableDTO> tableDTOList = dashboardService.getProposalPerTopik(idOrganisasi);
            responseEntity = ResponseEntity.ok(tableDTOList);
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/dashboard/proposal-per-tema/{tipe}/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getProposalPerTema(@PathVariable("tipe") String tipe, @PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        try {
            if (tipe.equalsIgnoreCase("tabel")) {
                List<DashboardTableDTO> tableDTOList = dashboardService.getProposalPerTemaForTable(idOrganisasi);
                responseEntity = ResponseEntity.ok(tableDTOList);
            } else if (tipe.equalsIgnoreCase("chart")) {
                DashboardColumnChartDTO columnChartDTO = dashboardService.getProposalPerTemaForChart(idOrganisasi);
                responseEntity = ResponseEntity.ok(columnChartDTO);
            }

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
