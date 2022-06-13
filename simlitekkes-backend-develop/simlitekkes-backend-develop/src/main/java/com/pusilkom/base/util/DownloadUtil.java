package com.pusilkom.base.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pusilkom.base.dto.TicketData;
import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by pusilkom on 3/24/2017.
 */
@Component
public class DownloadUtil {
    Logger log = LoggerFactory.getLogger(this.getClass());

    public String generateTicket(String targetDownloadURL) throws Exception {
        TicketData ticketData = new TicketData(targetDownloadURL, new Date());
        String jsonString = new ObjectMapper().writeValueAsString(ticketData);
        String encryptedJsonString = SecurityUtil.encryptWithGlobalKey(jsonString);
        byte[] encodedJsonString = Base64.encodeBase64(encryptedJsonString.getBytes());
        return new String(encodedJsonString);
    }

    public boolean verifyTicket(HttpServletRequest request, String encodedTicket) {
        try {
            String decodedTicket = new String(Base64.decodeBase64(encodedTicket));
            String jsonTicket = SecurityUtil.decryptWithGlobalKey(decodedTicket);
            TicketData ticketData = new ObjectMapper().readValue(jsonTicket, TicketData.class);

            if (!ticketData.getRequestUri().equals(request.getRequestURI())) {
                log.error("[verifyTicket not valid] targetDownloadURL : {} should be same with current requestUri {} . Check targetDownloadURL when generateTicket.", ticketData.getRequestUri(), request.getRequestURI().toString());
                return false;
            }

            DateTime expireDateTime = new DateTime(ticketData.getDateRequest()).plusMinutes(5);
            if (expireDateTime.isBeforeNow()) {
                log.error("[verifyTicket date has expired] expired date time : {}", expireDateTime);
                return false;
            }

            return true;
        } catch (Exception e) {
            log.error("verifyTicket : {}", e);
            return false;
        }
    }

}
