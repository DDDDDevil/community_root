package com.wangh.community_root.service.impl;

import com.wangh.community_root.mapper.BmsCommentMapper;
import com.wangh.community_root.model.dto.CommentDTO;
import com.wangh.community_root.model.entity.BmsComment;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.model.vo.CommentVO;
import com.wangh.community_root.service.BmsCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class BmsCommentServiceImpl implements BmsCommentService {

    @Autowired
    private BmsCommentMapper bmsCommentMapper;

    @Override
    public List<CommentVO> getCommentsByTopicID(String topicid) {
        List<CommentVO> lstBmsComment = new ArrayList<CommentVO>();
        try {
            lstBmsComment = bmsCommentMapper.getCommentsByTopicID(topicid);
        } catch (Exception e) {
            log.info("lstBmsComment失败");
        }
        return lstBmsComment;
    }

    @Override
    public BmsComment create(CommentDTO dto, UmsUser user) {
        BmsComment comment = BmsComment.builder()
                .userId(user.getId())
                .content(dto.getContent())
                .topicId(dto.getTopic_id())
                .createTime(new Date())
                .build();
        bmsCommentMapper.insertSelective(comment);
        return comment;
    }
}

