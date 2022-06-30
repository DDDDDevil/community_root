package com.wangh.community_root.service.impl;

import com.wangh.community_root.common.exception.ApiAsserts;
import com.wangh.community_root.jwt.JwtUtil;
import com.wangh.community_root.mapper.UmsUserMapper;
import com.wangh.community_root.model.dto.LoginDTO;
import com.wangh.community_root.model.dto.RegisterDTO;
import com.wangh.community_root.model.entity.BmsAvatars;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.model.vo.ProfileVO;
import com.wangh.community_root.service.BmsAvatarsService;
import com.wangh.community_root.service.UmsUserService;
import com.wangh.community_root.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Slf4j
@Service
public class UmsUserServiceImpl implements UmsUserService {

    @Autowired
    private UmsUserMapper umsUserMapper;

    @Autowired
    private BmsAvatarsService bmsAvatarsService;

    @Override
    public UmsUser userRegister(RegisterDTO registerDTO) {
        Example example = new Example(UmsUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", registerDTO.getName());
        criteria.orEqualTo("mail", registerDTO.getEmail());
        UmsUser umsUser = umsUserMapper.selectOneByExample(criteria);
        if(!ObjectUtils.isEmpty(umsUser)){
            ApiAsserts.fail("账号或邮箱已存在！");
        }
        BmsAvatars bmsAvatars = bmsAvatarsService.getavatarbyrandom();
        UmsUser addUser = UmsUser.builder()
                .username(registerDTO.getName())
                .alias(registerDTO.getName())
                .password(MD5Utils.getPwd(registerDTO.getPass()))
                .email(registerDTO.getEmail())
                .avatar(bmsAvatars.getAvatar())
                .createTime(new Date())
                .status(true)
                .build();
        umsUserMapper.insert(addUser);
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
            log.error("用户不存在or密码验证失败=======>{}", loginDTO.getUsername());
        }
        return token;
    }

    @Override
    public UmsUser getUserByUsername(String username) {
        Example example = new Example(UmsUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        UmsUser user = umsUserMapper.selectOneByExample(example);
        return user;
    }

    @Override
    public ProfileVO getUserProfile(String id) {
        ProfileVO profile = new ProfileVO();
        UmsUser user = umsUserMapper.selectByPrimaryKey(id);
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
