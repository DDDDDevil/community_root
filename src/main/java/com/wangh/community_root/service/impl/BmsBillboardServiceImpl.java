package com.wangh.community_root.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangh.community_root.mapper.BmsBillboardMapper;
import com.wangh.community_root.model.entity.BmsBillboard;
import com.wangh.community_root.service.BmsBillboardService;
import org.springframework.stereotype.Service;

@Service
public class BmsBillboardServiceImpl extends ServiceImpl<BmsBillboardMapper, BmsBillboard>
        implements BmsBillboardService {
}
