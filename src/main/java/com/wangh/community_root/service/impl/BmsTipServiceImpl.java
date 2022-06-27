package com.wangh.community_root.service.impl;

import com.wangh.community_root.mapper.BmsTipMapper;
import com.wangh.community_root.model.entity.BmsTip;
import com.wangh.community_root.service.BmsTipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BmsTipServiceImpl implements BmsTipService {

    @Autowired
    private BmsTipMapper bmsTipMapper;

    @Override
    public BmsTip getTodayTip() {
        BmsTip bmsTip = null;
        try{
            bmsTip = bmsTipMapper.getTodayTip();
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.toString());
        }
        return bmsTip;
    }
}