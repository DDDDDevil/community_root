package com.wangh.community_root.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangh.community_root.model.entity.BmsAvatars;

public interface BmsAvatarsService extends IService<BmsAvatars> {
    BmsAvatars getavatarbyrandom();
}
