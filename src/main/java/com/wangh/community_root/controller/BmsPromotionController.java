package com.wangh.community_root.controller;

import com.wangh.community_root.common.api.ApiResult;
import com.wangh.community_root.model.entity.BmsPromotion;
import com.wangh.community_root.service.BmsPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class BmsPromotionController {

    @Autowired
    private BmsPromotionService bmsPromotionService;

    @GetMapping("/showPromotion")
    public ApiResult<List<BmsPromotion>> getPromotion(){
        List<BmsPromotion> bmsPromotionList = bmsPromotionService.listPromotion();
        return ApiResult.success(bmsPromotionList);
    }
}
