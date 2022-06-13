package com.pusilkom.base.service;

import com.pusilkom.base.dto.BukuDTO;
import com.pusilkom.base.model.Buku;
import com.pusilkom.base.model.BukuExample;
import com.pusilkom.base.model.mapper.BukuMapper;
import com.pusilkom.base.util.DebugUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ars on 14/04/2020.
 */

@Service
@Transactional
public class BukuService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BukuMapper bukuMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    PenulisOutputService penulisOutputService;
    @Autowired
    OutputService outputService;
    @Autowired
    DebugUtil d;

    public List<BukuDTO> getListBukuDTO(BukuDTO searchform) {
        List<BukuDTO> listBuku = bukuMapper.getListBukuDTO(searchform);
        for(BukuDTO detail: listBuku){
            if(detail.getPathFile() != null){
                detail.setFileName(outputService.pathToFileName(detail.getPathFile()));
            }
            if(detail.getIdOutput() != null){
                detail.setOutput(outputService.getOutputById(detail.getIdOutput()));
            }
            if(detail.getIdPenulisOutput() != null){
                detail.setPenulisOutput(penulisOutputService.getPenulisOutputById(detail.getIdPenulisOutput()));
            }
        }
        return listBuku;
    }

    public Buku getBukuByIdOutput(Integer id){
        BukuExample ex = new BukuExample();
        ex.createCriteria().andIdOutputEqualTo(id);
        List<Buku> listBuku = bukuMapper.selectByExample(ex);
        return listBuku.get(0);
    }

    public Buku toBuku(BukuDTO BukuDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(BukuDTO, Buku.class);
    }

    public void save(BukuDTO BukuDTO) {
        logAktivitasService.addLog(BukuDTO.getUsername(), "POST", BukuDTO.toString(), "/buku-dosen");
        bukuMapper.insert(toBuku(BukuDTO));
    }

    public void update(BukuDTO BukuDTO) {
        logAktivitasService.addLog(BukuDTO.getUsername(), "PUT", BukuDTO.toString(), "/buku-dosen/" + BukuDTO.getIdOutput());
        BukuExample ex = new BukuExample();
        ex.createCriteria().andIdOutputEqualTo(BukuDTO.getIdOutput());
        bukuMapper.updateByExampleSelective(toBuku(BukuDTO), ex);
    }

    public void delete(Integer id, String username) {
        BukuExample ex = new BukuExample();
        ex.createCriteria().andIdOutputEqualTo(id);
        bukuMapper.deleteByExample(ex);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/buku-dosen/" + id);
    }
}

