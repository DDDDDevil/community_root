package com.wangh.community_root.service.impl;

import com.wangh.community_root.common.api.ApiResult;
import com.wangh.community_root.common.exception.ApiAsserts;
import com.wangh.community_root.mapper.BmsFollowMapper;
import com.wangh.community_root.mapper.UmsUserMapper;
import com.wangh.community_root.model.entity.BmsFollow;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.service.BmsFollowService;
import com.wangh.community_root.service.UmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.Map;


@Service
public class BmsFollowServiceImpl implements BmsFollowService {

    @Autowired
    private UmsUserService umsUserService;

    @Autowired
    private BmsFollowMapper bmsFollowMapper;

    @Autowired
    private UmsUserMapper umsUserMapper;

    @Override
    public void handleFollow(String userName, String parentId, UmsUser umsUser) {
        Example example = new Example(BmsFollow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId",parentId);
        criteria.andEqualTo("followerId",umsUser.getId());
        BmsFollow one = bmsFollowMapper.selectOneByExample(example);

        if (!ObjectUtils.isEmpty(one)) {
            ApiAsserts.fail("已关注");
        }

        BmsFollow follow = new BmsFollow();
        follow.setParentId(parentId);
        follow.setFollowerId(umsUser.getId());
        bmsFollowMapper.insert(follow);

        // 粉丝数+1
        umsUser.setFollowerCount(umsUser.getFollowerCount()+1);
        umsUserMapper.updateByPrimaryKey(umsUser);
    }

    @Override
    public void handleUnFollow(String userName, String parentId) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);

        Example example = new Example(BmsFollow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId",parentId);
        criteria.andEqualTo("followerId",umsUser.getId());
        BmsFollow one = bmsFollowMapper.selectOneByExample(example);
        if (ObjectUtils.isEmpty(one)) {
            ApiAsserts.fail("未关注！");
        }
        bmsFollowMapper.deleteByExample(example);

        // 粉丝数-1
        umsUser.setFollowerCount(umsUser.getFollowerCount()-1);
        umsUserMapper.updateByPrimaryKey(umsUser);
    }

    @Override
    public Map<String, Object> isFollow(String userName, String parentId) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        Map<String, Object> map = new HashMap<>(16);
        map.put("hasFollow", false);
        if (!ObjectUtils.isEmpty(umsUser)) {
            Example example = new Example(BmsFollow.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("parentId",parentId);
            criteria.andEqualTo("followerId",umsUser.getId());
            BmsFollow one = bmsFollowMapper.selectOneByExample(example);
            if (!ObjectUtils.isEmpty(one)) {
                map.put("hasFollow", true);
            }
        }
        return map;
    }
}