package com.wangh.community_root.model.entity;


import com.wangh.community_root.utils.UUIdGenId;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "bms_avatars")
public class BmsAvatars {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @KeySql(genId = UUIdGenId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "jdbc")
    private String id;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Date createTime;

}
