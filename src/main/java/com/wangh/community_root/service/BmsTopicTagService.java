package com.wangh.community_root.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangh.community_root.model.entity.BmsTag;
import com.wangh.community_root.model.entity.BmsTopicTag;

import java.util.List;
import java.util.Set;

public interface BmsTopicTagService extends IService<BmsTopicTag> {

    /**
     * 获取Topic Tag 关联记录
     *
     * @param topicId TopicId
     * @return
     */
    List<BmsTopicTag> selectByTopicId(String topicId);
}