package com.wangh.community_root.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangh.community_root.common.api.ApiResult;
import com.wangh.community_root.model.vo.PostVO;
import com.wangh.community_root.service.BmsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class BmsPostController extends BaseController {

    @Autowired
    private BmsPostService bmsPostService;

    @GetMapping("/list")
    public ApiResult<Page<PostVO>> list(@RequestParam(value = "tab", defaultValue = "latest") String tab,
                                        @RequestParam(value = "pageNo", defaultValue = "1")  Integer pageNo,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        Page<PostVO> list = bmsPostService.getList(new Page<>(pageNo, pageSize), tab);
        return ApiResult.success(list);
    }

}
