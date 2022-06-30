package com.wangh.community_root.service.impl;

import com.wangh.community_root.mapper.BmsPromotionMapper;
import com.wangh.community_root.model.entity.BmsPromotion;
import com.wangh.community_root.service.BmsPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BmsPromotionServiceImpl implements BmsPromotionService {

    @Autowired
    private BmsPromotionMapper bmsPromotionMapper;

    @Override
    public List<BmsPromotion> listPromotion() {
        return bmsPromotionMapper.selectAll();
    }
}



