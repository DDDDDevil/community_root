package com.wangh.community_root.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdurmont.emoji.EmojiParser;
import com.wangh.community_root.mapper.BmsTagMapper;
import com.wangh.community_root.mapper.BmsTopicMapper;
import com.wangh.community_root.mapper.UmsUserMapper;
import com.wangh.community_root.model.dto.CreateTopicDTO;
import com.wangh.community_root.model.entity.BmsPost;
import com.wangh.community_root.model.entity.BmsTag;
import com.wangh.community_root.model.entity.BmsTopicTag;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.model.vo.PostVO;
import com.wangh.community_root.service.BmsPostService;
import com.wangh.community_root.service.BmsTagService;
import com.wangh.community_root.service.BmsTopicTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BmsPostServiceImpl extends ServiceImpl<BmsTopicMapper, BmsPost> implements BmsPostService {

    @Autowired
    private BmsTopicTagService bmsTopicTagService;

    @Autowired
    private UmsUserMapper umsUserMapper;

    @Autowired
    private BmsTagService bmsTagService;

    @Autowired
    private BmsTagMapper bmsTagMapper;

    @Override
    public Page<PostVO> getList(Page<PostVO> page, String tab) {
        // 查询话题
        Page<PostVO> iPage = this.baseMapper.selectListAndPage(page, tab);
        // 查询话题的标签
        setTopicTags(iPage);
        return iPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BmsPost create(CreateTopicDTO dto, UmsUser user) {
        BmsPost topic1 = this.baseMapper.selectOne(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getTitle, dto.getTitle()));
        Assert.isNull(topic1, "话题已存在，请修改");

        // 封装
        BmsPost topic = BmsPost.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .createTime(new Date())
                .build();
        this.baseMapper.insert(topic);

        // 用户积分增加
        int newScore = user.getScore() + 1;
        umsUserMapper.updateById(user.setScore(newScore));

        // 标签
        if (!ObjectUtils.isEmpty(dto.getTags())) {
            // 保存标签
            List<BmsTag> tags = bmsTagService.insertTags(dto.getTags());
            // 处理标签与话题的关联
            bmsTopicTagService.createTopicTag(topic.getId(), tags);
        }

        return topic;
    }

    private void setTopicTags(Page<PostVO> iPage) {
        iPage.getRecords().forEach(topic -> {
            List<BmsTopicTag> topicTags = bmsTopicTagService.selectByTopicId(topic.getId());
            if (!topicTags.isEmpty()) {
                List<String> tagIds = topicTags.stream().map(BmsTopicTag::getTagId).collect(Collectors.toList());
                List<BmsTag> tags = bmsTagMapper.selectBatchIds(tagIds);
                topic.setTags(tags);
            }
        });
    }
}
