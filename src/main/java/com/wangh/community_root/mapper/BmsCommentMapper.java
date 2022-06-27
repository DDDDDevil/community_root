package com.wangh.community_root.mapper;

import com.wangh.community_root.model.entity.BmsComment;
import com.wangh.community_root.model.vo.CommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;


public interface BmsCommentMapper extends Mapper<BmsComment> {

    /**
     * getCommentsByTopicID
     *
     * @param topicid
     * @return
     */
    List<CommentVO> getCommentsByTopicID(@Param("topicid") String topicid);
}
