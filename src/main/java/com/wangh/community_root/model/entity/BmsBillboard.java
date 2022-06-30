package com.wangh.community_root.model.entity;


import com.wangh.community_root.utils.UUIdGenId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bms_billboard")
public class BmsBillboard implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @KeySql(genId = UUIdGenId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "jdbc")
    private Integer id;

    /**
     * 公告牌
     */
    private String content;

    /**
     * 公告时间
     */
    private Date createTime;

    /**
     * 1：展示中，0：过期
     */
    @Builder.Default
    private Boolean flag = false;
}
