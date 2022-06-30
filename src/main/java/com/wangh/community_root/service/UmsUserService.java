package com.wangh.community_root.service;

import com.wangh.community_root.model.dto.LoginDTO;
import com.wangh.community_root.model.dto.RegisterDTO;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.model.vo.ProfileVO;

public interface UmsUserService {

    /**
     * 注册用户
     * @param registerDTO
     * @return
     */
    UmsUser userRegister(RegisterDTO registerDTO);

    /**
     * 用户登录，返回token
     * @param loginDTO
     * @return 生成的JWT的token
     */
    String userLogin(LoginDTO loginDTO);

    /**
     * 获取用户信息
     *
     * @param username
     * @return dbUser
     */
    UmsUser getUserByUsername(String username);

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return
     */
    ProfileVO getUserProfile(String id);
}
