package com.wangh.community_root.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangh.community_root.model.dto.LoginDTO;
import com.wangh.community_root.model.dto.RegisterDTO;
import com.wangh.community_root.model.entity.UmsUser;

public interface UmsUserService extends IService<UmsUser> {

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
}
