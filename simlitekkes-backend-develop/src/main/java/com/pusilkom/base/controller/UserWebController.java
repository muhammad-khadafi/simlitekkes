package com.pusilkom.base.controller;

import com.pusilkom.base.dto.ChangePasswordDTO;
import com.pusilkom.base.dto.ForgotPasswordDTO;
import com.pusilkom.base.dto.UserwebDTO;
import com.pusilkom.base.helper.FileUploadHelper;
import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.model.Userweb;
import com.pusilkom.base.service.UserWebService;
import com.pusilkom.base.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhammad.khadafi on 19/02/2020.
 */
@RestController
public class UserWebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserWebController.class);
    @Autowired
    UploadUtil uploadUtil;
    @Value("${dir.upload.foto.userweb}")
    String DIR_UPLOAD_FILE;
    @Autowired
    private UserWebService userWebService;
    Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/userweb", method = RequestMethod.GET)
    public ResponseEntity getUserweb(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<UserwebDTO> userwebList = userWebService.getUserweb();
            responseEntity = ResponseEntity.ok(userwebList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/userweb/{organizationId}", method = RequestMethod.GET)
    public ResponseEntity getUserwebByOrganization(@PathVariable Integer organizationId) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<Userweb> userwebList = userWebService.getUserwebByOrganization(organizationId);
            responseEntity = ResponseEntity.ok(userwebList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/userweb-all/{organizationId}/{roleCode}", method = RequestMethod.GET)
    public ResponseEntity getUserwebDTOByOrganization(@PathVariable Integer organizationId, @PathVariable String roleCode) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<UserwebDTO> userwebList = userWebService.getUserwebList(organizationId, roleCode);
            responseEntity = ResponseEntity.ok(userwebList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/userweb-all-for-user-role/{organizationId}/{roleCode}", method = RequestMethod.GET)
    public ResponseEntity getUserwebAllForUserRole(@PathVariable Integer organizationId, @PathVariable String roleCode) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            List<UserwebDTO> userwebList = userWebService.getUserwebList2(organizationId, roleCode);
            responseEntity = ResponseEntity.ok(userwebList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseEntity;
    }

    @RequestMapping(value = "/userweb", method = RequestMethod.POST)
    public ResponseEntity postUserweb(@RequestBody UserwebDTO userwebDTO) {
        ResponseEntity responseEntity = null;

        try {
            Userweb result = userWebService.saveUserweb(userwebDTO);
            responseEntity = ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/userweb-foto/{id}", method = RequestMethod.POST)
    public ResponseEntity userwebFoto(@PathVariable Integer id, @RequestParam("file") MultipartFile fileUpload) {
        ResponseEntity responseEntity = null;
        try {
            FileUploadHelper fileUploadHelper = uploadUtil.uploadFileCommonService(DIR_UPLOAD_FILE, fileUpload);
            userWebService.updateUserweb(id, fileUploadHelper);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/userweb/{id}/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserweb(@PathVariable Long id, @PathVariable String username) {
        ResponseEntity responseEntity = null;

        try {
            ResponseEntityHelper responesResponseEntityHelper = userWebService.deleteUserweb(id, username);
            if (responesResponseEntityHelper.getStatus() == 409) {
                responseEntity = new ResponseEntity<>(responesResponseEntityHelper.getMessage(), HttpStatus.CONFLICT);
            } else
                responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/userweb-foto/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserwebFoto(@PathVariable Integer id) {
        ResponseEntity responseEntity = null;

        try {
            userWebService.deleteUserwebFoto(id);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/userweb", method = RequestMethod.PUT)
    public ResponseEntity updateUserweb(@RequestBody UserwebDTO userwebDTO) {
        ResponseEntity responseEntity = null;

        try {
            userWebService.updateUserweb(userwebDTO);
            responseEntity = ResponseEntity.ok().body(userwebDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/userweb/change-password", method = RequestMethod.PUT)
    public ResponseEntity userwebChangePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        ResponseEntity responseEntity = null;

        try {
            if (changePasswordDTO.getPasswordNew().equalsIgnoreCase(changePasswordDTO.getPassword())) {
                Boolean isOldPasswordCorrect = userWebService.checkOldPassword(changePasswordDTO);
                if (isOldPasswordCorrect) {
                    userWebService.userwebChangePassword(changePasswordDTO);
                    responseEntity = ResponseEntity.ok().build();
                } else {
                    responseEntity = new ResponseEntity<>("Password lama tidak sesuai", HttpStatus.CONFLICT);
                }
            } else {
                responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/userweb/forgot-password/check-email", method = RequestMethod.PUT)
    public ResponseEntity userwebCheckEmail(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        ResponseEntity responseEntity = null;

        try {
            forgotPasswordDTO.setIsValid(userWebService.checkValidEmail(forgotPasswordDTO.getEmail()));
            responseEntity = ResponseEntity.ok(forgotPasswordDTO);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @PutMapping(value = "/userweb/forgot-password")
    public ResponseEntity userwebForgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        ResponseEntity responseEntity = null;

        try {
            if (userWebService.checkValidEmail(forgotPasswordDTO.getEmail())) {
                userWebService.userWebForgotPassword(forgotPasswordDTO.getEmail());
                forgotPasswordDTO.setIsValid(true);
                LOGGER.info("Forgot password email sent to {}", forgotPasswordDTO.getEmail());
            } else {
                forgotPasswordDTO.setIsValid(false);
            }
            responseEntity = ResponseEntity.ok(forgotPasswordDTO);
        } catch (MailException | MessagingException e) {
            LOGGER.error("Unable to send email", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @PutMapping(value = "/userweb/reset-password")
    public ResponseEntity userwebForgotPassword(@RequestBody UserwebDTO userwebDTO) {
        ResponseEntity responseEntity = null;

        try {
            userWebService.userWebResetPassword(userwebDTO);
            responseEntity = ResponseEntity.ok().build();
            LOGGER.info("Reset password email sent to {}", userwebDTO.getEmail());
        } catch (MailException | MessagingException e) {
            LOGGER.error("Unable to send email", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/userweb-foto/{id}", method = RequestMethod.GET)
    public ResponseEntity getUserwebFoto(@PathVariable Integer id) throws Exception {
        ResponseEntity responseEntity = null;

        try {
            String fotoEncoded = userWebService.getUserwebFoto(id);
            responseEntity = ResponseEntity.ok(fotoEncoded);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/userweb-profil/{idUser}", method = RequestMethod.GET)
    public ResponseEntity getProfilUser(@PathVariable("idUser") Integer idUser) {
        ResponseEntity responseEntity = null;
        try {
            Userweb user = userWebService.getProfilUser(idUser);
            responseEntity = ResponseEntity.ok(user);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/userweb-profil", method = RequestMethod.PUT)
    public ResponseEntity updateProfilUser(@RequestBody UserwebDTO userwebDTO) {
        ResponseEntity responseEntity = null;
        try {
            userWebService.updateProfilUser(userwebDTO);
            responseEntity = ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An exception occurred!", e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/userweb/isNotPeneliti/{idOrganisasi}", method = RequestMethod.GET)
    public ResponseEntity getUserwebisNotPeneliti(@PathVariable("idOrganisasi") Integer idOrganisasi) {
        ResponseEntity responseEntity = null;
        try {
            List<Userweb> userwebList = userWebService.getUserwebisNotPeneliti(idOrganisasi);
            responseEntity = ResponseEntity.ok(userwebList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/userweb/checkIsDosen/{idUser}", method = RequestMethod.GET)
    public ResponseEntity checkUserwebIsDosen(@PathVariable("idUser") Long idUser) {
        ResponseEntity responseEntity = null;
        try {
            Boolean isDosen = userWebService.checkUserwebIsDosen(idUser);
            responseEntity = ResponseEntity.ok(isDosen);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/userweb/id/{userid}", method = RequestMethod.GET)
    public ResponseEntity getUserById(@PathVariable Integer userid) {
        ResponseEntity responseEntity = null;

        try {
            Userweb userwebList = userWebService.getUserwebByPk(userid);
            responseEntity = ResponseEntity.ok(userwebList);
        } catch (Exception e) {
            log.error("An exception occurred!", e);
        }

        return responseEntity;
    }
}
