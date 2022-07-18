package com.wangh.community_root.mapper;

import com.wangh.community_root.model.entity.BmsAvatars;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BmsAvatarsMapper extends Mapper<BmsAvatars> {
    BmsAvatars gestational();
}
