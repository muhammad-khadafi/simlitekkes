package com.pusilkom.base.service;

import com.pusilkom.base.dto.KelompokTktDTO;
import com.pusilkom.base.helper.ResponseEntityHelper;
import com.pusilkom.base.model.*;
import com.pusilkom.base.model.mapper.KelompokTktMapper;
import com.pusilkom.base.model.mapper.PenelitianMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by muhammad.khadafi on 2020-03-11 08:50:51.
 */

@Service
@Transactional
public class KelompokTktService {

    @Autowired
    KelompokTktMapper kelompokTktMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    PenelitianMapper penelitianMapper;

    public List<KelompokTkt> getKelompokTktList() {
        return kelompokTktMapper.selectByExample(new KelompokTktExample());
    }

    public void saveKelompokTkt(KelompokTktDTO kelompokTktDTO) {
        kelompokTktDTO.setIsActive(true);
        logAktivitasService.addLog(kelompokTktDTO.getUsername(), "POST", kelompokTktDTO.toString(), "/jenis-tkt");
        kelompokTktMapper.insert(toKelompokTkt(kelompokTktDTO));
    }

    private KelompokTkt toKelompokTkt(KelompokTktDTO kelompokTktDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(kelompokTktDTO, KelompokTkt.class);
    }

    public ResponseEntityHelper deleteKelompokTkt(Integer id, String username) {
        ResponseEntityHelper responseEntityHelper = new ResponseEntityHelper();
        responseEntityHelper.setStatus(200);
        responseEntityHelper.setMessage("OK");

        responseEntityHelper = checkingConstraints(id, responseEntityHelper);

        if (responseEntityHelper.getStatus() == 200) {
            logAktivitasService.addLog(username, "DELETE", id.toString(), "/jenis-tkt");
            kelompokTktMapper.deleteByPrimaryKey(id);
        }
        return responseEntityHelper;
    }

    private ResponseEntityHelper checkingConstraints(Integer id, ResponseEntityHelper responseEntityHelper) {

        // checking penelitian
        PenelitianExample penelitianExample = new PenelitianExample();
        penelitianExample.createCriteria().andIdKelompokTktEqualTo(id);
        List<Penelitian> penelitians = penelitianMapper.selectByExample(penelitianExample);

        if (!penelitians.isEmpty()) {
            responseEntityHelper.setMessage("Data digunakan sebagai referensi oleh data lain");
            responseEntityHelper.setStatus(409);
        }

        return responseEntityHelper;

    }

    public void updateKelompokTkt(KelompokTktDTO kelompokTktDTO) {
        logAktivitasService.addLog(kelompokTktDTO.getUsername(), "PUT", kelompokTktDTO.toString(), "/jenis-tkt");
        kelompokTktMapper.updateByPrimaryKey(toKelompokTkt(kelompokTktDTO));
    }
}
