package com.pusilkom.base.service;

import com.pusilkom.base.dto.TopikDTO;
import com.pusilkom.base.model.Topik;
import com.pusilkom.base.model.TopikExample;
import com.pusilkom.base.model.mapper.TopikMapper;
import com.pusilkom.base.util.DebugUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ars on 11/03/2020.
 */
@Service
@Transactional
public class TopikService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TopikMapper topikMapper;
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    DebugUtil d;

    public List<TopikDTO> getListTopikDTO(TopikDTO searchForm) {
        List<TopikDTO> listTopikDTO = topikMapper.getListTopikDTO(searchForm);
        return listTopikDTO;
    }

    public Topik toTopik(TopikDTO topikDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(topikDTO, Topik.class);
    }

    public void save(TopikDTO topikDTO) {
        logAktivitasService.addLog(topikDTO.getUsername(), "POST", topikDTO.toString(), "/topik/" + topikDTO.getIdTema());
        topikMapper.insert(toTopik(topikDTO));
    }

    public void update(TopikDTO topikDTO) {
        logAktivitasService.addLog(topikDTO.getUsername(), "PUT", topikDTO.toString(), "/topik/" + topikDTO.getIdTema());
        topikMapper.updateByPrimaryKeySelective(toTopik(topikDTO));
    }

    public void delete(Long id, String username) {
        TopikExample ex = new TopikExample();
        ex.createCriteria().andIdEqualTo(id.intValue());
        List<Topik> topik = topikMapper.selectByExample(ex);
        logAktivitasService.addLog(username, "DELETE", id.toString(), "/topik/" + topik.get(0).getIdTema());
        topikMapper.deleteByExample(ex);
    }

    //true = duplicate, false != duplicate
    public Boolean checkDuplicateNama(TopikDTO topikDTO) {
        TopikExample ex = new TopikExample();
        ex.createCriteria().andNamaLikeInsensitive(topikDTO.getNama());
        List<Topik> listTopik = topikMapper.selectByExample(ex);
        if ((topikDTO.getId() != null && listTopik.size() > 0 && listTopik.get(0).getId() != topikDTO.getId())
                || (topikDTO.getId() == null && listTopik.size() > 0)) {
            return true;
        } else {
            return false;
        }
    }
}
