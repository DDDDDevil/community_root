package com.wangh.community_root.service;

import com.wangh.community_root.model.dto.CommentDTO;
import com.wangh.community_root.model.entity.BmsComment;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.model.vo.CommentVO;

import java.util.List;


public interface  BmsCommentService {
    /**
     *
     *
     * @param topicid
     * @return {@link BmsComment}
     */
    List<CommentVO> getCommentsByTopicID(String topicid);

    BmsComment create(CommentDTO dto, UmsUser principal);
}
