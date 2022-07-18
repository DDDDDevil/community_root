package com.wangh.community_root.service;

import com.wangh.community_root.model.entity.BmsFollow;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

import static com.wangh.community_root.jwt.JwtUtil.USER_NAME;

public interface BmsFollowService {

    void handleFollow(String userName ,String parentId);

    void handleUnFollow(String userName ,String parentId);

    Map<String, Object> isFollow(String userName , String parentId);
}