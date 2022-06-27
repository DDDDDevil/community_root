package com.wangh.community_root.service.impl;

import com.wangh.community_root.common.exception.ApiAsserts;
import com.wangh.community_root.mapper.BmsFollowMapper;
import com.wangh.community_root.model.entity.BmsFollow;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.service.BmsFollowService;
import com.wangh.community_root.service.UmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;


@Service
public class BmsFollowServiceImpl implements BmsFollowService {

    @Autowired
    private UmsUserService umsUserService;

    @Autowired
    private BmsFollowMapper bmsFollowMapper;

    @Override
    public void handleFollow(String userName, String parentId) {
        UmsUser umsUser = umsUserService.getUserByUsername(userName);
        if (parentId.equals(umsUser.getId())) {
            ApiAsserts.fail("您脸皮太厚了，怎么可以关注自己呢");
        }

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
    }

    @Override
    public void handleUnFollow(String userName, String parentId) {

    }
}