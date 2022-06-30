package com.wangh.community_root.service.impl;

import com.wangh.community_root.mapper.BmsAvatarsMapper;
import com.wangh.community_root.model.entity.BmsAvatars;
import com.wangh.community_root.service.BmsAvatarsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BmsAvatarsServiceImpl implements BmsAvatarsService {

    @Autowired
    private BmsAvatarsMapper bmsAvatarsMapper;

    @Override
    public BmsAvatars getavatarbyrandom() {
        BmsAvatars bmsAvatars = null;
        try{
            bmsAvatars = this.bmsAvatarsMapper.getavatarbyrandom();
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.toString());
        }
        return bmsAvatars;
    }
}
