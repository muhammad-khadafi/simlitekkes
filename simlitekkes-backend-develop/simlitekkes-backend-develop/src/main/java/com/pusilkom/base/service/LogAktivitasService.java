package com.pusilkom.base.service;

import com.pusilkom.base.dto.LogAktifitasDTO;
import com.pusilkom.base.model.LogAktivitas;
import com.pusilkom.base.model.mapper.LogAktivitasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by muhammad.khadafi on 18/03/2020.
 */

@Service
@Transactional
public class LogAktivitasService {

    @Autowired
    LogAktivitasMapper logAktivitasMapper;

    public void addLog(String username, String httpMethod, String payLoad, String url) {
        LogAktivitas logAktivitas = new LogAktivitas();
        logAktivitas.setUsername(username);
        logAktivitas.setTimestampAktivitas(new Date());
        logAktivitas.setMethod(httpMethod);
        logAktivitas.setRequest(payLoad);
        logAktivitas.setUrl(url);
        logAktivitasMapper.insert(logAktivitas);
    }

    public List<LogAktifitasDTO> getLogAktifitasPerBulan(String periode) {
        String bulan = periode.substring(5,7);
        String tahun = periode.substring(0,4);
        return logAktivitasMapper.getLogAktifitasPerBulan(Integer.parseInt(bulan), Integer.parseInt(tahun));
    }
}
