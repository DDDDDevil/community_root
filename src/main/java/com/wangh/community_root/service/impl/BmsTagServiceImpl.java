package com.wangh.community_root.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangh.community_root.mapper.BmsPostMapper;
import com.wangh.community_root.mapper.BmsTagMapper;
import com.wangh.community_root.model.entity.BmsPost;
import com.wangh.community_root.model.entity.BmsTag;
import com.wangh.community_root.service.BmsPostService;
import com.wangh.community_root.service.BmsTagService;
import com.wangh.community_root.service.BmsTopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.*;


@Service
public class BmsTagServiceImpl implements BmsTagService {

    @Autowired
    private BmsTopicTagService bmsTopicTagService;

    @Autowired
    private BmsPostService bmsPostService;

    @Autowired
    private BmsTagMapper bmsTagMapper;

    @Autowired
    private BmsPostMapper bmsPostMapper;


    @Override
    public List<BmsTag> insertTags(List<String> tagNames) {
        List<BmsTag> tagList = new ArrayList<>();
        for (String tagName : tagNames) {
            Example example = new Example(BmsTag.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("name", tagName);
            BmsTag tag = bmsTagMapper.selectOneByExample(example);
            if (tag == null) {
                tag = BmsTag.builder().name(tagName).build();
                bmsTagMapper.insert(tag);
            } else {
                tag.setTopicCount(tag.getTopicCount() + 1);
                bmsTagMapper.updateByPrimaryKey(tag);
            }
            tagList.add(tag);
        }
        return tagList;
    }

    @Override
    public PageInfo<BmsPost> selectTopicsByTagId(Integer pageNum, Integer pageSize, String id) {
        // 获取关联的话题ID
        Set<String> ids = bmsTopicTagService.selectTopicIdsByTagId(id);
        Example example = new Example(BmsPost.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",ids);
        PageInfo<BmsPost> pageInfo = PageHelper.startPage(pageNum, pageNum).doSelectPageInfo(() -> bmsPostMapper.selectByExample(example));
        return pageInfo;
    }

    @Override
    public Map<String, Object> getTopicsByTag(Integer pageNum, Integer pageSize, String tagName) {
        Map<String, Object> map = new HashMap<>(16);

        Example example = new Example(BmsTag.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", tagName);
        BmsTag tag = bmsTagMapper.selectOneByExample(example);
        Assert.notNull(tag, "话题不存在，或已被管理员删除");

        PageInfo<BmsPost> topics = selectTopicsByTagId(pageNum, pageSize, tag.getId());
        // 其他热门标签
        Example exampleHot = new Example(BmsTag.class);
        exampleHot.setOrderByClause("topicCount");
        Example.Criteria criteriaHot = exampleHot.createCriteria();
        criteriaHot.andEqualTo("name", tagName);
        PageInfo<BmsTag> hotTags = PageHelper.startPage(1,10).doSelectPageInfo(() -> bmsTagMapper.selectByExample(exampleHot));

        map.put("topics", topics);
        map.put("hotTags", hotTags);
        return map;
    }

}
