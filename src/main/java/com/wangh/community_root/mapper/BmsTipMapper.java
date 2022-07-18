package com.wangh.community_root.mapper;

import com.wangh.community_root.model.entity.BmsTip;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BmsTipMapper extends Mapper<BmsTip> {
    BmsTip getTodayTip();
}