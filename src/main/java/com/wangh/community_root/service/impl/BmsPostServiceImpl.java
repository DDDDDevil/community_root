package com.wangh.community_root.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;
import com.wangh.community_root.mapper.*;
import com.wangh.community_root.model.dto.CreateTopicDTO;
import com.wangh.community_root.model.entity.BmsPost;
import com.wangh.community_root.model.entity.BmsTag;
import com.wangh.community_root.model.entity.BmsTopicTag;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.model.vo.PostVO;
import com.wangh.community_root.model.vo.ProfileVO;
import com.wangh.community_root.service.BmsPostService;
import com.wangh.community_root.service.BmsTagService;
import com.wangh.community_root.service.BmsTopicTagService;
import com.wangh.community_root.service.UmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BmsPostServiceImpl implements BmsPostService {

    @Autowired
    private BmsTopicTagService bmsTopicTagService;

    @Autowired
    private BmsTopicTagMapper bmsTopicTagMapper;

    @Autowired
    private BmsTopicMapper bmsTopicMapper;

    @Autowired
    private UmsUserMapper umsUserMapper;

    @Autowired
    private UmsUserService umsUserService;

    @Autowired
    private BmsTagService bmsTagService;

    @Autowired
    private BmsTagMapper bmsTagMapper;

    @Autowired
    private BmsPostMapper bmsPostMapper;

    @Override
    public void deletePost(String userName, String id) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        BmsPost byId = bmsPostMapper.selectByPrimaryKey(id);
        Assert.notNull(byId, "来晚一步，话题已不存在");
        Assert.isTrue(byId.getUserId().equals(umsUser.getId()), "你为什么可以删除别人的话题？？？");
        bmsPostMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updatePost(String userName, BmsPost post) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        Assert.isTrue(umsUser.getId().equals(post.getUserId()), "非本人无权修改");
        post.setModifyTime(new Date());
        post.setContent(EmojiParser.parseToAliases(post.getContent()));
    }

    @Override
    public PageInfo<PostVO> getList(Integer pageNo, Integer pageSize, String tab) {
        // 查询话题
        PageInfo<PostVO> iPage = PageHelper.startPage(pageNo,pageSize).doSelectPageInfo(()->bmsTopicMapper.selectListAndPage(tab));
        // 查询话题的标签
        setTopicTags(iPage);
        return iPage;
    }

    /**
     * 创建话题
     * @param dto
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BmsPost create(CreateTopicDTO dto, UmsUser user) {
        Example example = new Example(BmsPost.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("title",dto.getTitle());
        BmsPost topic1 = bmsTopicMapper.selectOneByExample(example);
        Assert.isNull(topic1, "话题已存在，请修改");

        // 封装
        BmsPost topic = BmsPost.builder()
                .userId(user.getId())
                .title(dto.getTitle())
                .content(EmojiParser.parseToAliases(dto.getContent()))
                .createTime(new Date())
                .build();
        bmsTopicMapper.insert(topic);

        // 用户积分增加
        int newScore = user.getScore() + 1;
        umsUserMapper.updateByPrimaryKey(user.setScore(newScore));

        // 标签
        if (!ObjectUtils.isEmpty(dto.getTags())) {
            // 保存标签
            List<BmsTag> tags = bmsTagService.insertTags(dto.getTags());
            // 处理标签与话题的关联
            bmsTopicTagService.createTopicTag(topic.getId(), tags);
        }

        return topic;
    }

    @Override
    public Map<String, Object> viewTopic(String id) {
        Map<String, Object> map = new HashMap<>(16);
        BmsPost topic = bmsTopicMapper.selectByPrimaryKey(id);
        Assert.notNull(topic, "当前话题不存在,或已被作者删除");
        // 查询话题详情
        topic.setView(topic.getView() + 1);
        bmsTopicMapper.updateByPrimaryKey(topic);
        // emoji转码
        topic.setContent(EmojiParser.parseToUnicode(topic.getContent()));
        map.put("topic", topic);
        // 标签
        Example example = new Example(BmsTopicTag.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("topicId",topic.getId());
        Set<String> set = new HashSet<>();
        for (BmsTopicTag articleTag : bmsTopicTagMapper.selectByExample(example)) {
            set.add(articleTag.getTagId());
        }
        Example example1 = new Example(BmsTag.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andIn("id", set);
        List<BmsTag> tags = bmsTagMapper.selectByExample(example1);
        map.put("tags", tags);

        // 作者

        ProfileVO user = umsUserService.getUserProfile(topic.getUserId());
        map.put("user", user);

        return map;
    }

    private void setTopicTags(PageInfo<PostVO> iPage) {
        iPage.getList().forEach(topic -> {
            List<BmsTopicTag> topicTags = bmsTopicTagService.selectByTopicId(topic.getId());
            if (!topicTags.isEmpty()) {
                List<String> tagIds = topicTags.stream().map(BmsTopicTag::getTagId).collect(Collectors.toList());
                Example example = new Example(BmsTag.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andIn("id",tagIds);
                List<BmsTag> tags = bmsTagMapper.selectByExample(example);
                topic.setTags(tags);
            }
        });
    }

    @Override
    public List<BmsPost> getRecommend(String id) {
        return bmsTopicMapper.selectRecommend(id);
    }

    @Override
    public PageInfo<PostVO> searchByKey(String keyword, Integer pageNum, Integer pageSize) {
        // 查询话题
        PageInfo<PostVO> iPage = PageHelper.startPage(pageNum,pageSize).doSelectPageInfo(() -> bmsTopicMapper.searchByKey(keyword));
        // 查询话题的标签
        setTopicTags(iPage);
        return iPage;
    }
}
