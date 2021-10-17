package com.wangh.community_root.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangh.community_root.model.entity.BmsTopicTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface BmsTopicTagMapper extends BaseMapper<BmsTopicTag> {
    /**
     * 根据标签获取话题ID集合
     *
     * @param id
     * @return
     */
    Set<String> getTopicIdsByTagId(@Param("id") String id);
}
