package com.wangh.community_root.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangh.community_root.mapper.BmsAvatarsMapper;
import com.wangh.community_root.model.entity.BmsAvatars;
import com.wangh.community_root.service.BmsAvatarsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BmsAvatarsServiceImpl extends ServiceImpl<BmsAvatarsMapper, BmsAvatars>
        implements BmsAvatarsService {

    @Override
    public BmsAvatars getavatarbyrandom() {
        BmsAvatars bmsAvatars = null;
        try{
            bmsAvatars = this.baseMapper.getavatarbyrandom();
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.toString());
        }
        return bmsAvatars;
    }
}
