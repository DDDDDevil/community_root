package com.wangh.community_root.model.entity;
import com.wangh.community_root.utils.UUIdGenId;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "bms_follow")
public class BmsFollow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @KeySql(genId = UUIdGenId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "jdbc")
    private Integer id;

    /**
     * 被关注人id
     */
    private String parentId;

    /**
     * 关注人id
     */
    private String followerId;

    public BmsFollow() {
    }

}