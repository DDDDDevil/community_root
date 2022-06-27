package com.wangh.community_root.model.entity;

import com.wangh.community_root.utils.UUIdGenId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bms_post")
public class BmsPost implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @KeySql(genId = UUIdGenId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "jdbc")
    private String id;
    /**
     * 标题
     */
    @NotBlank(message = "标题不可以为空")
    private String title;
    /**
     * markdown
     */
    @NotBlank(message = "内容不可以为空")
    private String content;

    /**
     * 作者ID
     */
    private String userId;

    /**
     * 评论数
     */
    @Builder.Default
    private Integer comments = 0;

    /**
     * 收藏数
     */
    @Builder.Default
    private Integer collects = 0;

    /**
     * 浏览数
     */
    @Builder.Default
    private Integer view = 0;

    /**
     * 专栏ID，默认不分栏
     */
    @Builder.Default
    private Integer sectionId = 0;

    /**
     * 置顶
     */
    @Builder.Default
    private Boolean top = false;

    /**
     * 加精
     */
    @Builder.Default
    private Boolean essence = false;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;
}
