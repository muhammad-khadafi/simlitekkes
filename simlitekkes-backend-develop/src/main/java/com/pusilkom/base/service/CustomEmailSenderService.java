package com.pusilkom.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class CustomEmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Sends an HTML-based email.
     *
     * @param emailTo   address of the recipient
     * @param subject   subject of the email
     * @param textEmail content of the email
     * @throws MailException        failed to send the email
     * @throws MessagingException   problems when preparing the email
     */
    public void sendEmailHtml(String emailTo, String subject, String textEmail)
            throws MailException, MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(emailTo);
        helper.setSubject(subject);
        helper.setText(textEmail, true);

        javaMailSender.send(mimeMessage);
    }
}