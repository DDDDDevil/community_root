package com.wangh.community_root.service.impl;

import com.wangh.community_root.mapper.BmsTopicTagMapper;
import com.wangh.community_root.model.entity.BmsTag;
import com.wangh.community_root.model.entity.BmsTopicTag;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.service.BmsTopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;


@Service
@Transactional(rollbackFor = Exception.class)
public class BmsTopicTagServiceImpl implements BmsTopicTagService {

    @Autowired
    private BmsTopicTagMapper bmsTopicTagMapper;

    @Override
    public List<BmsTopicTag> selectByTopicId(String topicId) {
        Example example = new Example(BmsTopicTag.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("topicId", topicId);
        return bmsTopicTagMapper.selectByExample(example);
    }

    @Override
    public void createTopicTag(String id, List<BmsTag> tags) {
        // 先删除topicId对应的所有记录
        Example example = new Example(BmsTopicTag.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("topicId", id);
        bmsTopicTagMapper.deleteByExample(example);
        // 循环保存对应关联
        tags.forEach(tag -> {
            BmsTopicTag topicTag = new BmsTopicTag();
            topicTag.setTopicId(id);
            topicTag.setTagId(tag.getId());
            bmsTopicTagMapper.insert(topicTag);
        });
    }

    @Override
    public Set<String> selectTopicIdsByTagId(String id) {
        return bmsTopicTagMapper.getTopicIdsByTagId(id);
    }
}