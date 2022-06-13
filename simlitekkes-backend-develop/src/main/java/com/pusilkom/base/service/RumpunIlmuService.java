/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pusilkom.base.service;

import com.pusilkom.base.model.RumpunIlmu;
import com.pusilkom.base.model.RumpunIlmuExample;
import com.pusilkom.base.model.mapper.RumpunIlmuMapper;
import com.pusilkom.base.util.DebugUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Helmi Fakhriandi
 */
@Service
@Transactional
public class RumpunIlmuService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LogAktivitasService logAktivitasService;
    @Autowired
    DebugUtil d;

    @Autowired
    RumpunIlmuMapper rumpunIlmuMapper;

    public List<RumpunIlmu> getRumpunIlmubyLevel(Short level, Integer idParent) {
        RumpunIlmuExample rumpunIlmuExample = new RumpunIlmuExample();
        if (level != 1) {
            rumpunIlmuExample.createCriteria().andLevelEqualTo(level).andIdParentRumpunIlmuEqualTo(idParent);
        } else {
            rumpunIlmuExample.createCriteria().andLevelEqualTo(level);
        }
        List<RumpunIlmu> rumpunIlmus = rumpunIlmuMapper.selectByExample(rumpunIlmuExample);
        return rumpunIlmus;
    }

}
