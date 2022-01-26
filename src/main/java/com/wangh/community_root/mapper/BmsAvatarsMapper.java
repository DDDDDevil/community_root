package com.wangh.community_root.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangh.community_root.model.entity.BmsAvatars;

public interface BmsAvatarsMapper extends BaseMapper<BmsAvatars> {
    BmsAvatars getavatarbyrandom();
}
