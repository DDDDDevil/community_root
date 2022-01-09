package com.wangh.community_root.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangh.community_root.common.exception.ApiAsserts;
import com.wangh.community_root.jwt.JwtUtil;
import com.wangh.community_root.mapper.UmsUserMapper;
import com.wangh.community_root.model.dto.LoginDTO;
import com.wangh.community_root.model.dto.RegisterDTO;
import com.wangh.community_root.model.entity.BmsPost;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.model.vo.ProfileVO;
import com.wangh.community_root.service.UmsUserService;
import com.wangh.community_root.utils.MD5Utils;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Slf4j
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser>
        implements UmsUserService {

    @Override
    public UmsUser userRegister(RegisterDTO registerDTO) {
        LambdaQueryWrapper<UmsUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UmsUser::getUsername, registerDTO.getName()).or()
                .eq(UmsUser::getEmail, registerDTO.getEmail());
        UmsUser umsUser = this.baseMapper.selectOne(lambdaQueryWrapper);
        if(!ObjectUtils.isEmpty(umsUser)){
            ApiAsserts.fail("账号或邮箱已存在！");
        }
        UmsUser addUser = UmsUser.builder()
                .username(registerDTO.getName())
                .alias(registerDTO.getName())
                .password(MD5Utils.getPwd(registerDTO.getPass()))
                .email(registerDTO.getEmail())
                .createTime(new Date())
                .status(true)
                .build();
        baseMapper.insert(addUser);

        System.out.println(IdWorker.getId());
        return addUser;
    }

    @Override
    public String userLogin(LoginDTO loginDTO) {
        String token = null;
        try{
            UmsUser user = getUserByUsername(loginDTO.getUsername());
            if(!user.getPassword().equals(MD5Utils.getPwd(loginDTO.getPassword()))){
                throw new Exception("密码错误");
            }
            token = JwtUtil.generateToken(String.valueOf(user.getUsername()));
            log.info(token);
        }catch (Exception e){
            log.info("用户不存在or密码验证失败=======>{}", loginDTO.getUsername());
        }
        return token;
    }

    @Override
    public UmsUser getUserByUsername(String username) {
        UmsUser user = this.baseMapper.selectOne(
                new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername, username)
        );
        return user;
    }

    @Override
    public ProfileVO getUserProfile(String id) {
        ProfileVO profile = new ProfileVO();
        UmsUser user = baseMapper.selectById(id);
        BeanUtils.copyProperties(user, profile);
//        // 用户文章数
//        int count = bmsTopicMapper.selectCount(new LambdaQueryWrapper<BmsPost>().eq(BmsPost::getUserId, id));
//        profile.setTopicCount(count);
//
//        // 粉丝数
//        int followers = bmsFollowMapper.selectCount((new LambdaQueryWrapper<BmsFollow>().eq(BmsFollow::getParentId, id)));
//        profile.setFollowerCount(followers);

        return profile;
    }
}
