package com.wangh.community_root.model.entity;

import com.wangh.community_root.utils.UUIdGenId;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@Accessors(chain = true)
@Table(name = "bms_tag")
public class BmsTag implements Serializable {
    private static final long serialVersionUID = 3257790983905872243L;

    @Id
    @KeySql(genId = UUIdGenId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "jdbc")
    private String id;

    private String name;
    /**
     * 当前标签下的话题个数
     */
    @Builder.Default
    private Integer topicCount = 1;
}
