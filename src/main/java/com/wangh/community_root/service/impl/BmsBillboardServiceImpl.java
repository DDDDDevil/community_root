package com.wangh.community_root.service.impl;

import com.wangh.community_root.mapper.BmsBillboardMapper;
import com.wangh.community_root.model.entity.BmsBillboard;
import com.wangh.community_root.service.BmsBillboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BmsBillboardServiceImpl implements BmsBillboardService {

    @Autowired
    private BmsBillboardMapper bmsBillboardMapper;

    @Override
    public List<BmsBillboard> listBillboard() {
        Example example = new Example(BmsBillboard.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("flag",1);
        return bmsBillboardMapper.selectByExample(example);
    }
}
