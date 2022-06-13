package com.pusilkom.base.service;

import com.pusilkom.base.dto.ChangePasswordDTO;
import com.pusilkom.base.dto.UserwebDTO;
import com.pusilkom.base.helper.FileUploadHelper;
import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.*;
import com.pusilkom.base.util.PasswordGeneratorUtil;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by muhammad.khadafi on 19/02/2020.
 */
@Service
@Transactional
public class UserWebService {

    @Autowired
    UserwebMapper userwebMapper;
    @Autowired
    OrganisasiMapper organisasiMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    CustomEmailSenderService customEmailSenderService;
    @Autowired
    PasswordGeneratorUtil passwordGeneratorUtil;
    @Autowired
    ReviewerSkemaMapper reviewerSkemaMapper;
    @Autowired
    UserwebHasRoleMapper userwebHasRoleMapper;
    @Autowired
    KomentarMapper komentarMapper;

    public List<UserwebDTO> getUserweb() {
        UserwebExample userwebExample = new UserwebExample();
        userwebExample.setOrderByClause("username");
        List<Userweb> userwebList = userwebMapper.selectByExample(userwebExample);
        List<UserwebDTO> userwebDTOList = new ArrayList<>();
        for (Userweb userweb :
                userwebList) {
            UserwebDTO userwebDTO = new UserwebDTO();
            userwebDTO = toUserwebDTO(userweb);

            if (userwebDTO.getIdOrganisasi() != null) {
                Organisasi organisasi = organisasiMapper.selectByPrimaryKey(userweb.getIdOrganisasi());
                userwebDTO.setOrganisasi(organisasi.getNama());
            }

            if (userwebDTO.getLastLogin() != null) {
                String pattern = "dd-MM-yyyy HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String lastLoginStr = simpleDateFormat.format(userweb.getLastLogin());
                userwebDTO.setLastLoginStr(lastLoginStr);
            }
            userwebDTOList.add(userwebDTO);
        }
        return userwebDTOList;
    }

    public List<UserwebDTO> getUserwebDTOByOrganizationId(Integer organizationId) {
        List<Userweb> userwebList = getUserwebByOrganization(organizationId);
        List<UserwebDTO> userwebDTOList = new ArrayList<>();
        for (Userweb userweb :
            userwebList) {
            UserwebDTO userwebDTO = new UserwebDTO();
            userwebDTO = toUserwebDTO(userweb);

            if (userwebDTO.getIdOrganisasi() != null) {
                Organisasi organisasi = organisasiMapper.selectByPrimaryKey(userweb.getIdOrganisasi());
                userwebDTO.setOrganisasi(organisasi.getNama());
            }

            if (userwebDTO.getLastLogin() != null) {
                String pattern = "dd-MM-yyyy HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String lastLoginStr = simpleDateFormat.format(userweb.getLastLogin());
                userwebDTO.setLastLoginStr(lastLoginStr);
            }
            userwebDTOList.add(userwebDTO);
        }
        return userwebDTOList;
    }

    public Userweb saveUserweb(UserwebDTO userwebDTO) {
        String newSalt = BCrypt.gensalt();
        String newPass = createNewPassword(10);

        userwebDTO.setPassword(BCrypt.hashpw(newPass, newSalt));
        Userweb userweb = toUserweb(userwebDTO);
        userwebMapper.insert(userweb);
        logAktivitasService.addLog(userwebDTO.getUsernameSubmitter(), "POST",
            userwebDTO.toString(), "/userweb");

//        try {
//            sendPasswordToNewUser(userweb, newPass);
//        } catch (MessagingException e) {
//            System.out.println("send password faied..");
//            e.printStackTrace();
//        }
        return userweb;
    }


    public void sendPasswordToNewUser(Userweb userweb, String password)
        throws MailException, MessagingException {

        String bodyEmail = "<h3>Hi " + userweb.getNama() + ",</h3>" +
            "<p>Anda baru saja terdaftar sebagai user SIMLITEKKES, silakan melakukan login dengan username dan password di bawah ini:</p>" +
            "<p>Username : " + userweb.getUsername() + "</p>" +
            "<p>Password : " + password + "</p>" +
            "<p>nb : jika login bermasalah pastikan kepada admin tempat mendaftar bahwa anda telah mendapat peran pengguna</p>" +
            "<br/><p>Salam,</p>" +
            "<p>Admin SIMLITEKKES</p>";

        customEmailSenderService.sendEmailHtml(userweb.getEmail(),
            "SIMLITEKKES - LOGIN INFO", bodyEmail);
    }

    public Userweb toUserweb(UserwebDTO userwebDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userwebDTO, Userweb.class);
    }

    public UserwebDTO toUserwebDTO(Userweb userweb) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userweb, UserwebDTO.class);
    }

    public ResponseEntityHelper deleteUserweb(Long id, String username) {
        ResponseEntityHelper responseEntityHelper = new ResponseEntityHelper();
        responseEntityHelper.setStatus(200);
        responseEntityHelper.setMessage("OK");

        responseEntityHelper = checkingConstraints(id, responseEntityHelper);

        if (responseEntityHelper.getStatus() == 200) {
            userwebMapper.deleteByPrimaryKey(Integer.parseInt(id.toString()));
            logAktivitasService.addLog(username, "DELETE",
                id.toString(), "/userweb/" + id.toString() + "/" + username);
        }

        return responseEntityHelper;
    }

    private ResponseEntityHelper checkingConstraints(Long id, ResponseEntityHelper responseEntityHelper) {
        // checking reviewer skema
        ReviewerSkemaExample reviewerSkemaExample = new ReviewerSkemaExample();
        reviewerSkemaExample.createCriteria().andIdPemberiTugasEqualTo(id.intValue());
        List<ReviewerSkema> reviewerSkema = reviewerSkemaMapper.selectByExample(reviewerSkemaExample);

        // checking komentar
//        KomentarExample komentarExample = new KomentarExample();
//        komentarExample.createCriteria().andIdUserEqualTo(id.intValue());
//        List<Komentar> komentars = komentarMapper.selectByExample(komentarExample);

        // checking user role
        UserwebHasRoleExample userwebHasRoleExample = new UserwebHasRoleExample();
        userwebHasRoleExample.createCriteria().andIdUserwebEqualTo(id);
        List<UserwebHasRole> userwebHasRoles = userwebHasRoleMapper.selectByExample(userwebHasRoleExample);

        if (!reviewerSkema.isEmpty() || !userwebHasRoles.isEmpty()
//            || !komentars.isEmpty()
        ) {
            responseEntityHelper.setMessage("Data digunakan sebagai referensi oleh data lain");
            responseEntityHelper.setStatus(409);
        }

        return responseEntityHelper;

    }

    public void updateUserweb(UserwebDTO userwebDTO) {
        logAktivitasService.addLog(userwebDTO.getUsernameSubmitter(), "PUT",
            userwebDTO.toString(), "/userweb");
        Userweb userweb = userwebMapper.selectByPrimaryKey(userwebDTO.getId());
//        userwebDTO.setFoto(null);
//        userwebDTO.setFotoName(null);
        userwebDTO.setPassword(userweb.getPassword());
        userwebMapper.updateByPrimaryKey(toUserweb(userwebDTO));
    }

    public boolean checkValidEmail(String email) {
        UserwebExample ex = new UserwebExample();
        ex.createCriteria().andEmailEqualTo(email.toLowerCase());

        List<Userweb> listUserWeb = userwebMapper.selectByExample(ex);
        if (!listUserWeb.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void userwebChangePassword(ChangePasswordDTO changaPasswordDTO) {
        Userweb userweb = new Userweb();
        userweb.setId(changaPasswordDTO.getId());
        String newSalt = BCrypt.gensalt();
        userweb.setPassword(BCrypt.hashpw(changaPasswordDTO.getPasswordNew(), newSalt));
        userwebMapper.updateByPrimaryKeySelective(userweb);
    }

    public void updateUserweb(Integer id, FileUploadHelper fileUploadHelper) {
        UserwebDTO userwebDTO = new UserwebDTO();
        userwebDTO.setId(id);
        userwebDTO.setFoto(fileUploadHelper.getFilePath());
        userwebDTO.setFotoName(fileUploadHelper.getFileName());
        userwebMapper.updateByPrimaryKeySelective(toUserweb(userwebDTO));
    }

    public String getUserwebFoto(Integer id) {
        String encodedFoto = null;
        Userweb userweb = userwebMapper.selectByPrimaryKey(id);
        if (userweb.getFoto() != null) {
            byte[] fileContent = new byte[0];
            try {
                fileContent = FileUtils.readFileToByteArray(new File(userweb.getFoto()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            encodedFoto = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(fileContent);
        }
        return encodedFoto;
    }

    public void deleteUserwebFoto(Integer id) {
        Userweb userweb = userwebMapper.selectByPrimaryKey(id);
        if (userweb.getFoto() != null) {
            File file = new File(userweb.getFoto());
            file.delete();
        }
        userweb.setFotoName(null);
        userweb.setFoto(null);
        userwebMapper.updateByPrimaryKey(userweb);
    }

    public void userWebForgotPassword(String email)
            throws MailException, MessagingException {
        Userweb userweb = new Userweb();
        UserwebExample ex = new UserwebExample();
        ex.createCriteria().andEmailEqualTo(email);

        List<Userweb> listUserWeb = userwebMapper.selectByExample(ex);
        if (!listUserWeb.isEmpty()) {
            userweb = listUserWeb.get(0);
        }

        String newPass = createNewPassword(10);
        String newSalt = BCrypt.gensalt();
        userweb.setPassword(BCrypt.hashpw(newPass, newSalt));
        userwebMapper.updateByPrimaryKeySelective(userweb);

        String bodyEmail = "<h3>Hi " + userweb.getNama() + ",</h3>" +
                "<p>Anda baru saja meminta untuk reset password, silakan melakukan login dengan username dan password di bawah ini:</p>" +
                "<p>Username : " + userweb.getUsername() + "</p>" +
                "<p>Password : " + newPass + "</p>" +
                "<br/><p>Salam,</p>" +
                "<p>Admin SIMLITEKKES</p>";

        customEmailSenderService.sendEmailHtml(userweb.getEmail(),
                "SIMLITEKKES - RESET PASSWORD", bodyEmail);
    }

    public void userWebResetPassword(UserwebDTO userwebDTO)
            throws MailException, MessagingException {
        Userweb userweb = toUserweb(userwebDTO);

        String newPass = createNewPassword(10);
        String newSalt = BCrypt.gensalt();
        userweb.setPassword(BCrypt.hashpw(newPass, newSalt));
        userwebMapper.updateByPrimaryKeySelective(userweb);

        String bodyEmail = "<h3>Hi " + userweb.getNama() + ",</h3>" +
                "<p>Anda baru saja meminta untuk reset password, silakan melakukan login dengan username dan password di bawah ini:</p>" +
                "<p>Username : " + userweb.getUsername() + "</p>" +
                "<p>Password : " + newPass + "</p>" +
                "<br/><p>Salam,</p>" +
                "<p>Admin SIMLITEKKES</p>";

        customEmailSenderService.sendEmailHtml(userweb.getEmail(),
                "SIMLITEKKES - RESET PASSWORD", bodyEmail);
    }

    private String createNewPassword(int length) {
        return passwordGeneratorUtil.generateRandomPassword(length);
    }

    public List<Userweb> getUserwebByOrganization(Integer organizationId) {
        UserwebExample userwebExample = new UserwebExample();
        userwebExample.createCriteria().andIdOrganisasiEqualTo(organizationId);
        userwebExample.setOrderByClause("username");
        return userwebMapper.selectByExample(userwebExample);
    }

    public Userweb getProfilUser(Integer idUser) {
        return userwebMapper.selectByPrimaryKey(idUser);
    }

    public void updateProfilUser(UserwebDTO userwebDTO) {
        userwebMapper.updateByPrimaryKey(toUserweb(userwebDTO));
    }

    public Boolean checkOldPassword(ChangePasswordDTO changePasswordDTO) {
        Userweb userweb = userwebMapper.selectByPrimaryKey(changePasswordDTO.getId());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(changePasswordDTO.getPasswordOld(), userweb.getPassword());
    }

    public List<Userweb> getUserwebisNotPeneliti(Integer idOrganisasi) {
        List<Userweb> listUser = userwebMapper.getUserwebisNotPeneliti(idOrganisasi);
        return listUser;
    }

    public Boolean checkUserwebIsDosen(Long idUser) {
        return userwebMapper.checkUserwebIsDosen(idUser);
    }

    public Userweb getUserwebByPk(Integer id){
        return userwebMapper.selectByPrimaryKey(id);
    }

    public List<Userweb> getSuperAdmin() {
        return userwebMapper.selectSuperAdmin();
    }

    public List<UserwebDTO> getUserwebList(Integer organizationId, String roleCode) {
        return userwebMapper.getUserwebList(organizationId, roleCode);
    }

    public List<UserwebDTO> getUserwebList2(Integer organizationId, String roleCode) {
        return userwebMapper.getUserwebList2(organizationId, roleCode);
    }
}
