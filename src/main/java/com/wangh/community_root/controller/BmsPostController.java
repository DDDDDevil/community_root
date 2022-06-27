package com.wangh.community_root.controller;


import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;
import com.wangh.community_root.common.api.ApiResult;
import com.wangh.community_root.model.dto.CreateTopicDTO;
import com.wangh.community_root.model.entity.BmsPost;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.model.vo.PostVO;
import com.wangh.community_root.service.BmsPostService;
import com.wangh.community_root.service.UmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.wangh.community_root.jwt.JwtUtil.USER_NAME;

@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController {

    @Autowired
    private BmsPostService bmsPostService;

    @Autowired
    private UmsUserService umsUserService;

    @GetMapping("/list")
    public ApiResult<PageInfo<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        PageInfo<PostVO> list = bmsPostService.getList(pageNo, pageSize, tab);
        return ApiResult.success(list);
    }

    /**
     * 发布帖子
     * @param userName
     * @param dto
     * @return
     */
    @PostMapping("/create")
    public ApiResult<BmsPost> create(@RequestHeader(value = USER_NAME) String userName
            , @RequestBody CreateTopicDTO dto) {
        UmsUser user = umsUserService.getUserByUsername(userName);
        BmsPost topic = bmsPostService.create(dto, user);
        return ApiResult.success(topic);
    }

    @GetMapping()
    public ApiResult<Map<String, Object>> view(@RequestParam("id") String id) {
        Map<String, Object> map = bmsPostService.viewTopic(id);
        return ApiResult.success(map);
    }
    @GetMapping("/recommend")
    public ApiResult<List<BmsPost>> getRecommend(@RequestParam("topicId") String id) {
        List<BmsPost> topics = bmsPostService.getRecommend(id);
        return ApiResult.success(topics);
    }

    @PostMapping("/update")
    public ApiResult<BmsPost> update(@RequestHeader(value = USER_NAME) String userName, @Valid @RequestBody BmsPost post) {
        bmsPostService.updatePost(userName,post);
        return ApiResult.success(post);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@RequestHeader(value = USER_NAME) String userName, @PathVariable("id") String id) {
        bmsPostService.deletePost(userName,id);
        return ApiResult.success(null,"删除成功");
    }

}
