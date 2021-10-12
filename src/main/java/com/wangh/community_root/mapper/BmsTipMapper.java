package com.wangh.community_root.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangh.community_root.model.entity.BmsTip;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BmsTipMapper extends BaseMapper<BmsTip> {
    BmsTip getTodayTip();
}