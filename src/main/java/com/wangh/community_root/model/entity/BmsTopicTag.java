package com.wangh.community_root.model.entity;


import com.wangh.community_root.utils.UUIdGenId;
import lombok.Data;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Accessors(chain = true)
@Table(name = "bms_post_tag")
public class BmsTopicTag implements Serializable {
    private static final long serialVersionUID = -5028599844989220715L;

    @Id
    @KeySql(genId = UUIdGenId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "jdbc")
    private String id;

    private String tagId;

    private String topicId;
}