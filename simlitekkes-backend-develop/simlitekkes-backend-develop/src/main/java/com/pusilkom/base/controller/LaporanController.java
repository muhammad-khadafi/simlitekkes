package com.pusilkom.base.controller;

import com.pusilkom.base.dto.BeritaDTO;
import com.pusilkom.base.dto.DownloadResponse;
import com.pusilkom.base.service.LaporanService;
import com.pusilkom.base.util.DownloadUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class LaporanController {

    final static String TICKET_NOTVALID_MSG = "Ticket is not valid";
    final static String FAILED = "Failed to generate";

    @Autowired
    LaporanService laporanService;

    @Autowired
    DownloadUtil downloadUtil;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/laporan-anggaran/tahun-usulan/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getTahunUsulanForLaporanAnggaran(@PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        try {
            List<String> tahunUsulanList = laporanService.getTahunUsulanForLaporanAnggaran(idOrganisasi);
            responseEntity = ResponseEntity.ok(tahunUsulanList);

        }catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/laporan-anggaran/download/{tahunUsulan}/{idOrganisasi}", method = RequestMethod.POST)
    public ResponseEntity generateTicketDownload(@PathVariable("tahunUsulan") String tahunUsulan, @PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;

        try {
            String ticket = downloadUtil.generateTicket("/download/laporan-anggaran/" + tahunUsulan + "/" + idOrganisasi) ;
            responseEntity = ResponseEntity.ok(new DownloadResponse(ticket));
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/download/laporan-anggaran/{tahunUsulan}/{idOrganisasi}", method = RequestMethod.GET)
    public void exportLaporanAnggaran(@PathVariable("tahunUsulan") String tahunUsulan, @PathVariable("idOrganisasi") Integer idOrganisasi, @RequestParam("ticket") String ticket, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            boolean isVerified = downloadUtil.verifyTicket(request, ticket);

            if (!isVerified) {
                response.getOutputStream().write(TICKET_NOTVALID_MSG.getBytes());
                return;
            }

            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String filename = "LaporanAnggaran-"+tahunUsulan+".xlsx";
            String headerValue = "attachment; filename="+filename;

            response.setHeader(headerKey, headerValue);

            XSSFWorkbook workbook = laporanService.generateLaporanAnggaran(tahunUsulan, idOrganisasi);
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.flush();
            outputStream.close();
        }catch(Exception e) {
            log.error("An exception occurred!", e);
            response.getOutputStream().write(FAILED.getBytes());
            return;
        }

    }

}
