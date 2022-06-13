package com.pusilkom.base.service;

import com.pusilkom.base.dto.BeritaDTO;
import com.pusilkom.base.dto.DokumenOrganisasiDTO;
import com.pusilkom.base.model.DokumenOrganisasi;
import com.pusilkom.base.model.mapper.BeritaMapper;
import com.pusilkom.base.model.mapper.DokumenOrganisasiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HomeService {

    @Autowired
    BeritaMapper beritaMapper;

    @Autowired
    DokumenOrganisasiMapper dokumenOrganisasiMapper;

    public List<BeritaDTO> getBeritaTerkini(Integer idOrganisasi) {
        return beritaMapper.getBeritaTerkini(idOrganisasi);
    }

    public List<DokumenOrganisasiDTO> getDokumenTerkini(Integer idOrganisasi) {
        return dokumenOrganisasiMapper.getDokumenTerkini(idOrganisasi);
    }
}
