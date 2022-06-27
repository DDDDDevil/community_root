package com.wangh.community_root.mapper;

import com.wangh.community_root.model.dto.RegisterDTO;
import com.wangh.community_root.model.entity.UmsUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.common.Mapper;


public interface UmsUserMapper extends Mapper<UmsUser> {
}
