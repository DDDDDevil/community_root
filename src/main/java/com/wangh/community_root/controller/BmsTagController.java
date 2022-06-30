package com.wangh.community_root.controller;


import com.wangh.community_root.common.api.ApiResult;
import com.wangh.community_root.model.entity.BmsPost;
import com.wangh.community_root.model.entity.BmsTag;
import com.wangh.community_root.service.BmsTagService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tag")
public class BmsTagController extends BaseController {

    @Resource
    private BmsTagService bmsTagService;

    @GetMapping("/{name}")
    public ApiResult<Map<String, Object>> getTopicsByTag(
            @PathVariable("name") String tagName,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        return ApiResult.success(bmsTagService.getTopicsByTag(page,size,tagName));
    }

}