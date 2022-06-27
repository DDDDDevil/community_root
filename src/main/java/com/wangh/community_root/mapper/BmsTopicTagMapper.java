package com.wangh.community_root.mapper;

import com.wangh.community_root.model.entity.BmsTopicTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import java.util.Set;


public interface BmsTopicTagMapper extends Mapper<BmsTopicTag> {
    /**
     * 根据标签获取话题ID集合
     *
     * @param id
     * @return
     */
    Set<String> getTopicIdsByTagId(@Param("id") String id);
}
