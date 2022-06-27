package com.wangh.community_root.controller;

import com.wangh.community_root.common.api.ApiResult;
import com.wangh.community_root.model.entity.BmsBillboard;
import com.wangh.community_root.service.BmsBillboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/billboard")
public class BmsBillboardController extends BaseController {

    @Autowired
    private BmsBillboardService bmsBillboardService;

    @GetMapping("/showBillboard")
    public ApiResult<BmsBillboard> getBillboard(){
        List<BmsBillboard> billboards = bmsBillboardService.listBillboard();
        return ApiResult.success(billboards.get(billboards.size()- 1));
    }
}
