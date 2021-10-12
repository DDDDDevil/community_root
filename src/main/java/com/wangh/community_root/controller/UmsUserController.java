package com.wangh.community_root.controller;


import com.wangh.community_root.common.api.ApiResult;
import com.wangh.community_root.common.exception.ApiAsserts;
import com.wangh.community_root.model.dto.LoginDTO;
import com.wangh.community_root.model.dto.RegisterDTO;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.service.UmsUserService;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.wangh.community_root.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/user")
public class UmsUserController {

    @Autowired
    private UmsUserService umsUserService;

    @PostMapping("/register")
    public ApiResult<Map<String, Object>> userRegister(@Valid @RequestBody RegisterDTO registerDTO){
        UmsUser user = umsUserService.userRegister(registerDTO);
        if(ObjectUtils.isEmpty(user)){
            ApiAsserts.fail("账号注册失败！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        return ApiResult.success(map);
    }

    @PostMapping("/login")
    public ApiResult<Map<String, Object>> userLogin(@Valid @RequestBody LoginDTO loginDTO){
        String token = umsUserService.userLogin(loginDTO);
        if(ObjectUtils.isEmpty(token)){
            return ApiResult.failed("账号密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return ApiResult.success(map);
    }

    @GetMapping("/info")
    public ApiResult<UmsUser> getUser(@RequestHeader(value = USER_NAME) String userName) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        return ApiResult.success(user);
    }

    @GetMapping("/logout")
    public ApiResult<Object> logOut(){
        return ApiResult.success(null,"注销成功");
    }
}
