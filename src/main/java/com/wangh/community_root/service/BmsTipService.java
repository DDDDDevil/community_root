package com.wangh.community_root.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangh.community_root.model.entity.BmsTip;

public interface BmsTipService extends IService<BmsTip> {
    BmsTip getTodayTip();
}
