package com.pusilkom.base.controller;

import com.pusilkom.base.dto.Response;
import com.pusilkom.base.service.ValidasiService;
import com.pusilkom.base.util.DebugUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by code-generator on 2020-05-22.
 */

@RestController
public class ValidasiController {
    @Autowired
    ValidasiService validasiService;
    @Autowired
    DebugUtil d;
    @Value("${tanggal.mulai.pengusulan}") String tanggalMulai;
    @Value("${tanggal.selesai.pengusulan}") String tanggalSelesai;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //Validasi data H16W, get data output penelitian dosen sebagai ketua, return true if validation passed
    @RequestMapping(value = "/validasi/h16w-output/{idUser}/{idOutput}", method = RequestMethod.GET)
    public ResponseEntity geValidasiH16WIdOutput(@PathVariable Integer idUser, @PathVariable Integer idOutput) {
        ResponseEntity responseEntity = null;
        try {
            Boolean validateResult = validasiService.getDataValidasiOutputPenelitianPeranKetuaH16W(idUser, idOutput);
            responseEntity = ResponseEntity.ok(validateResult);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    //Validasi role, user berasal dari poltekkes
    @RequestMapping(value = "/validasi/isPoltekkes/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getDataValidasiUserFromPoltekkes(@PathVariable Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        try {
            Boolean validateResult = validasiService.getDataValidasiUserFromPoltekkes(idOrganisasi);
            responseEntity = ResponseEntity.ok(validateResult);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    //Validasi role, user berasal dari poltekkes
    @RequestMapping(value = "/validasi/isDosenAktif/{idUser}", method = RequestMethod.GET)
    public ResponseEntity checkDosenisAktif(@PathVariable Integer idUser) {
        ResponseEntity responseEntity = null;
        try {
            Boolean validateResult = validasiService.checkDosenisAktif(idUser);
            responseEntity = ResponseEntity.ok(validateResult);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    //Validasi role user standar
    @RequestMapping(value = "validasi/{roleCode}/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getRoleValidation(@PathVariable String roleCode, @PathVariable Integer idOrganisasi){
        ResponseEntity responseEntity = null;
        try {
            Boolean validateResult = false;
            if(roleCode.equalsIgnoreCase("kapus_ppm")){
                validateResult = validasiService.getDataValidasiUserFromPoltekkes(idOrganisasi);
            } else if(roleCode.equalsIgnoreCase("pengelola_pusat")){
                validateResult = validasiService.getDataValidasiUserFromPpsdm(idOrganisasi);
            } else if(roleCode.equalsIgnoreCase("dosen")){
                validateResult = validasiService.getDataValidasiUserFromPoltekkes(idOrganisasi);
            }
            responseEntity = ResponseEntity.ok(validateResult);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/validasi/checkUserIsKapusPpm/{userId}/{roleCode}", method = RequestMethod.GET)
    public ResponseEntity checkUserIsKapusPpm(@PathVariable("userId") Integer userId, @PathVariable("roleCode") String roleCode) {
        ResponseEntity responseEntity = null;
        try {
            Boolean result;
            result = validasiService.checkUserIsKapusPPM(userId, roleCode);
            responseEntity = ResponseEntity.ok(result);
        } catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  responseEntity;
    }

    @RequestMapping(value = "/validasi/checkUserIsPengelolaPusat/{userId}/{roleCode}", method = RequestMethod.GET)
    public ResponseEntity checkUserIsPengelolaPusat(@PathVariable("userId") Integer userId, @PathVariable("roleCode") String roleCode) {
        ResponseEntity responseEntity = null;
        try {
            Boolean result;
            result = validasiService.checkUserIsPengelolaPusat(userId, roleCode);
            responseEntity = ResponseEntity.ok(result);
        } catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  responseEntity;
    }

    @RequestMapping(value = "validasi/h14w/{idPenelitian}/{idUser}", method = RequestMethod.GET)
    public ResponseEntity checkPenelitianNotSubmitted(@PathVariable("idPenelitian") Integer idPenelitian, @PathVariable("idUser") Integer idUser){
        ResponseEntity responseEntity = null;
        try{
            Boolean result;
            result = validasiService.getDataValidasiIsPenelitianNotSubmittedH14W(idPenelitian, idUser);
            responseEntity = ResponseEntity.ok(result);
        } catch(Exception e){
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/validasi/checkUserIsOperatorPpm/{userId}/{roleCode}", method = RequestMethod.GET)
    public ResponseEntity checkUserIsOperatorPpm(@PathVariable("userId") Integer userId, @PathVariable("roleCode") String roleCode) {
        ResponseEntity responseEntity = null;
        try {
            Boolean result;
            result = validasiService.checkUserIsOperatorPpm(userId, roleCode);
            responseEntity = ResponseEntity.ok(result);
        } catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/validasi/checkUserIsFromPpsdm/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity checkUserIsFromPpsdm(@PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        try {
            Boolean result;
            result = validasiService.getDataValidasiUserFromPpsdm(idOrganisasi);
            responseEntity = ResponseEntity.ok(result);
        } catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "validasi/h10w/{idUser}", method = RequestMethod.GET)
    public ResponseEntity checkPenelitiWithNidnExist(@PathVariable("idUser") Integer idUser){
        ResponseEntity responseEntity = null;
        try{
            Boolean result;
            result = validasiService.checkPenelitiWithNidnExistH10W(idUser);
            responseEntity = ResponseEntity.ok(result);
        } catch(Exception e){
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/validasi/checkUserIsReviewer/{userId}/{roleCode}", method = RequestMethod.GET)
    public ResponseEntity checkUserIsReviewer(@PathVariable("userId") Integer userId, @PathVariable("roleCode") String roleCode) {
        ResponseEntity responseEntity = null;
        try {
            Boolean result;
            result = validasiService.checkUserIsReviewer(userId, roleCode);
            responseEntity = ResponseEntity.ok(result);
        } catch(Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "validasi/I14R/{idPenelitian}/{idUser}", method = RequestMethod.GET)
    public ResponseEntity checkValidasiI14W(@PathVariable("idPenelitian") Integer idPenelitian, @PathVariable("idUser") Integer idUser){
        ResponseEntity responseEntity = null;
        try{
            Boolean result;
            result = validasiService.getDataValidasiIsPenelitianI14W(idPenelitian, idUser);
            responseEntity = ResponseEntity.ok(result);
        } catch(Exception e){
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/validasi/checkPenelitiIsSameOrganization/{idPeneliti}/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity checkPenelitiIsSameOrganization(@PathVariable("idPeneliti") Integer idPeneliti, @PathVariable("idOrganisasi") Integer idOrganisasi){
        ResponseEntity responseEntity = null;
        try{
            Boolean result;
            result = validasiService.checkPenelitiIsSameOrganization(idPeneliti, idOrganisasi);
            responseEntity = ResponseEntity.ok(result);
        } catch(Exception e){
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Validasi tgl mulai pengusulan < current date < tgl selesai pengusulan
    @RequestMapping(value = "/validasi/date/isAllowed", method = RequestMethod.GET)
    public ResponseEntity checkCurrentDateAllowed() {
        ResponseEntity responseEntity = null;
        try {
            String stringValidate = "Allowed";
            Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
            String tglMulaiStr = currentYear + tanggalMulai;
            String tglSelesaiStr = currentYear + tanggalSelesai;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            LocalDate tglMulai = LocalDate.parse(tglMulaiStr, formatter);
            LocalDate tglSelesai = LocalDate.parse(tglSelesaiStr, formatter);
            LocalDate currentDate = (Calendar.getInstance().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (tglMulai.isAfter(currentDate) ) {
                stringValidate = "Pengusulan belum dibuka";
            } else if (currentDate.isAfter(tglSelesai)) {
                stringValidate = "Pengusulan sudah ditutup";
            }
            responseEntity = ResponseEntity.ok(stringValidate);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/validasi/checkUsernameIsUnique/{username}", method = RequestMethod.GET)
    public ResponseEntity checkUsernameIsUnique(@PathVariable("username") String username){
        ResponseEntity responseEntity = null;
        try{
            Boolean result;
            result = validasiService.checkUsernameIsUnique(username);
            responseEntity = ResponseEntity.ok(result);
        } catch(Exception e){
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/validasi/checkNidnIsUsed/{nidn}", method = RequestMethod.GET)
    public ResponseEntity checkNidnIsUsed(@PathVariable("nidn") String nidn){
        ResponseEntity responseEntity = null;
        try{
            Boolean result;
            result = validasiService.checkNidnIsUsed(nidn);
            responseEntity = ResponseEntity.ok(result);
        } catch(Exception e){
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
