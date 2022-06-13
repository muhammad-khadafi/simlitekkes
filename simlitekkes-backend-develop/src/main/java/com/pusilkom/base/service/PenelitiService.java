package com.pusilkom.base.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pusilkom.base.dto.*;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.PenelitiMapper;
import com.pusilkom.base.model.mapper.RumpunIlmuMapper;
import com.pusilkom.base.util.DebugUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ars on 11/03/2020.
 */
@Service
@Transactional
public class PenelitiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PenelitiService.class);
    private static final String LOG_RESULT_FMT = ">>> Result: {}";
    private static final String PARAMETERS = "parameters";

    @Value("${external.proxy.host}")
    private String proxyHost;

    @Value("${external.proxy.port:8080}")
    private String proxyPort;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private PenelitiMapper penelitiMapper;
    @Autowired
    private RumpunIlmuMapper rumpunIlmuMapper;
    @Autowired
    private LogAktivitasService logAktivitasService;
    @Autowired
    private DebugUtil d;

    public List<PenelitiDTO> getListPenelitiDTO(PenelitiDTO searchForm) {
        return penelitiMapper.getListPenelitiDTO(searchForm);
    }

    public Peneliti toPeneliti(PenelitiDTO penelitiDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(penelitiDTO, Peneliti.class);
    }

    public Integer save(PenelitiDTO penelitiDTO) {
        logAktivitasService.addLog(penelitiDTO.getUsername(), "POST", penelitiDTO.toString(), "/dosen");
        Peneliti peneliti = toPeneliti(penelitiDTO);
        penelitiMapper.insert(peneliti);
        return peneliti.getId();
    }

    public void update(PenelitiDTO penelitiDTO) {
        logAktivitasService.addLog(penelitiDTO.getUsername(), "PUT", penelitiDTO.toString(), "/dosen/" + penelitiDTO.getId());
        penelitiMapper.updateByPrimaryKeySelective(toPeneliti(penelitiDTO));
    }

    public void delete(Integer id, String username) {
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/dosen");
        penelitiMapper.deleteByPrimaryKey(id);
    }

    public List<RumpunIlmu> getAllBidangKeahlian(){
        short level = 3;
        RumpunIlmuExample ex = new RumpunIlmuExample();
        ex.createCriteria().andLevelEqualTo(level);
        return rumpunIlmuMapper.selectByExample(ex);
    }

    public List<PenelitiDTO> getDaftarPenelitiReviewerInternal(Integer idOrganisasi, String kataKunci) {
        return penelitiMapper.getDaftarPenelitiReviewerInternal(idOrganisasi, "%"+kataKunci.trim()+"%");
    }

    public List<PenelitiDTO> getDaftarPenelitiReviewerEksternal(String kataKunci) {
        return penelitiMapper.getDaftarPenelitiReviewerEksternal("%"+kataKunci.trim()+"%");
    }

    public PenelitiParentDiktiDTO getPenelitiDiktiByNidn(String nidn, String token) throws IOException {
        String uri = "http://sisdmk.kemkes.go.id:8094/rest/dosen2?token=" + token + "&nidn=" + nidn;
        uri = uri.trim();
        RestTemplate restTemplate = createRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(PARAMETERS, headers);

        ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        LOGGER.info(">>> getresult: {}", d.toString(res.getBody()));

        ObjectMapper mapper = new ObjectMapper();
        PenelitiParentDiktiDTO participantJsonList = mapper.readValue(res.getBody(), new TypeReference<PenelitiParentDiktiDTO>(){});
        return participantJsonList;
    }

    public String loginPenelitiDikti() throws IOException {
        String uri = "http://sisdmk.kemkes.go.id:8094/rest/login_ws?username=simlitekkes&password=simlitekkes2020";
        RestTemplate restTemplate = createRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(PARAMETERS, headers);

        ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        LOGGER.info(LOG_RESULT_FMT, d.toString(res.getBody()));

        ObjectMapper mapper = new ObjectMapper();
        LoginResultDiktiDTO participantJsonList = mapper.readValue(res.getBody(), new TypeReference<LoginResultDiktiDTO>(){});

        return participantJsonList.getToken();
    }

    public PerguruanTinggiParentDiktiDTO getPerguruanTinggiDiktiByIdPt(String token, String idPt) throws IOException {
        String uri = "http://sisdmk.kemkes.go.id:8094/rest/perguruanTinggi?token=" + token + "&id=" + idPt;
        uri = uri.trim();
        RestTemplate restTemplate = createRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(PARAMETERS, headers);

        ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        LOGGER.info(LOG_RESULT_FMT, d.toString(res.getBody()));

        ObjectMapper mapper = new ObjectMapper();
        PerguruanTinggiParentDiktiDTO participantJsonList = mapper.readValue(res.getBody(), new TypeReference<PerguruanTinggiParentDiktiDTO>(){});
        return participantJsonList;
    }

    public ProdiParentDiktiDTO getProdiDiktiByIdPtDanIdProdi(String token, String idPt, String idProdi) throws IOException {
        String uri = "http://sisdmk.kemkes.go.id:8094/rest/prodiPerguruanTinggi?token=" + token + "&id-pt=" + idPt + "&id-prodi=" + idProdi;
        uri = uri.trim();
        RestTemplate restTemplate = createRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(PARAMETERS, headers);

        ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        LOGGER.info(LOG_RESULT_FMT, d.toString(res.getBody()));

        ObjectMapper mapper = new ObjectMapper();
        ProdiParentDiktiDTO participantJsonList = mapper.readValue(res.getBody(), new TypeReference<ProdiParentDiktiDTO>(){});
        return participantJsonList;
    }

    private RestTemplate createRestTemplate() {
        if (isProxy()) {
            LOGGER.debug("Create RestTemplate with proxy '{}:{}' enabled",
                proxyHost, proxyPort);
            return restTemplateBuilder
                .requestFactory(this::httpRequestFactoryWithProxy)
                .build();
        } else {
            LOGGER.debug("Create RestTemplate with proxy disabled");
            return restTemplateBuilder.build();
        }
    }

    private boolean isProxy() {
        return proxyHost.length() > 0 && proxyPort.length() > 0;
    }

    private SimpleClientHttpRequestFactory httpRequestFactoryWithProxy() {
        SimpleClientHttpRequestFactory requestFactory =
            new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP,
            new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort)));
        requestFactory.setProxy(proxy);
        return requestFactory;
    }
}
