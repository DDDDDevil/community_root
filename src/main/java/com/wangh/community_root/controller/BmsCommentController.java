package com.wangh.community_root.controller;

import com.wangh.community_root.common.api.ApiResult;
import com.wangh.community_root.model.dto.CommentDTO;
import com.wangh.community_root.model.entity.BmsComment;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.model.vo.CommentVO;
import com.wangh.community_root.service.BmsCommentService;
import com.wangh.community_root.service.UmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wangh.community_root.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/comment")
public class BmsCommentController extends BaseController {

    @Autowired
    private BmsCommentService bmsCommentService;
    @Autowired
    private UmsUserService umsUserService;

    @GetMapping("/get_comments")
    public ApiResult<List<CommentVO>> getCommentsByTopicID(@RequestParam(value = "topicid", defaultValue = "1") String topicid) {
        List<CommentVO> lstBmsComment = bmsCommentService.getCommentsByTopicID(topicid);
        return ApiResult.success(lstBmsComment);
    }

    @PostMapping("/add_comment")
    public ApiResult<BmsComment> add_comment(@RequestHeader(value = USER_NAME) String userName,
                                             @RequestBody CommentDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsComment comment = bmsCommentService.create(dto, user);
        return ApiResult.success(comment);
    }
}
