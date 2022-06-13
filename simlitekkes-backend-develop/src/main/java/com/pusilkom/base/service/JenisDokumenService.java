package com.pusilkom.base.service;

import com.pusilkom.base.model.JenisDokumen;
import com.pusilkom.base.model.JenisDokumenExample;
import com.pusilkom.base.model.mapper.JenisDokumenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by muhammad.khadafi on 2020-03-11 08:50:51.
 */

@Service
@Transactional
public class JenisDokumenService {

    @Autowired
    JenisDokumenMapper jenisDokumenMapper;

    public List<JenisDokumen> getJenisDokumenOrg() {
        JenisDokumenExample jenisDokumenExample = new JenisDokumenExample();
        jenisDokumenExample.createCriteria().andKelompokDokumenEqualTo("o");
        return jenisDokumenMapper.selectByExample(jenisDokumenExample);
    }
}
