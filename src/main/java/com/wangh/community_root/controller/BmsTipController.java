package com.wangh.community_root.controller;


import com.wangh.community_root.common.api.ApiResult;
import com.wangh.community_root.model.entity.BmsTip;
import com.wangh.community_root.service.BmsTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tip")
public class BmsTipController {

    @Autowired
    private BmsTipService bmsTipService;

    @RequestMapping("/today")
    public ApiResult<BmsTip> getTodayTip(){
        BmsTip bmsTip = null;
        try{
            bmsTip = bmsTipService.getTodayTip();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ApiResult.success(bmsTip);
    }
}
