package com.wangh.community_root.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangh.community_root.mapper.BmsBillboardMapper;
import com.wangh.community_root.mapper.BmsPromotionMapper;
import com.wangh.community_root.model.entity.BmsBillboard;
import com.wangh.community_root.model.entity.BmsPromotion;
import com.wangh.community_root.service.BmsBillboardService;
import com.wangh.community_root.service.BmsPromotionService;
import org.springframework.stereotype.Service;

@Service
public class BmsPromotionServiceImpl extends ServiceImpl<BmsPromotionMapper, BmsPromotion>
        implements BmsPromotionService {
}



