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


@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bms_promotion")
public class BmsPromotion implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @KeySql(genId = UUIdGenId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "jdbc")
    private Integer id;

    /**
     * 广告标题
     */
    private String title;

    /**
     * 广告链接
     */
    private String link;

    /**
     * 说明
     */
    private String description;
}
