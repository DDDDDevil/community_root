package com.wangh.community_root.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wangh.community_root.mapper.BmsFollowMapper;
import com.wangh.community_root.model.entity.BmsFollow;
import com.wangh.community_root.service.BmsFollowService;
import org.springframework.stereotype.Service;


@Service
public class BmsFollowServiceImpl extends ServiceImpl<BmsFollowMapper, BmsFollow> implements BmsFollowService {
}