package com.wangh.community_root.service;

import com.github.pagehelper.PageInfo;
import com.wangh.community_root.model.entity.BmsPost;
import com.wangh.community_root.model.entity.BmsTag;
import java.util.List;
import java.util.Map;

public interface BmsTagService {
    /**
     * 插入标签
     *
     * @param tags
     * @return
     */
    List<BmsTag> insertTags(List<String> tags);

    /**
     * 获取标签关联话题
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    PageInfo<BmsPost> selectTopicsByTagId(Integer pageNum, Integer pageSize, String id);

    /**
     * 获取指定tag关联的所有话题
     * @param pageNum
     * @param pageSize
     * @param tagName
     * @return
     */
    Map<String, Object> getTopicsByTag(Integer pageNum, Integer pageSize, String tagName);

}
