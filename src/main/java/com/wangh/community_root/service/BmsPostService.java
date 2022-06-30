package com.wangh.community_root.service;

import com.github.pagehelper.PageInfo;
import com.wangh.community_root.model.dto.CreateTopicDTO;
import com.wangh.community_root.model.entity.BmsPost;
import com.wangh.community_root.model.entity.UmsUser;
import com.wangh.community_root.model.vo.PostVO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public interface BmsPostService {

    void deletePost(String userName, String id);

    void updatePost(String userName, BmsPost post);

    /**
     * 获取首页话题列表
     * @param pageNo
     * @param pageSize
     * @param tab
     * @return
     */
    PageInfo<PostVO> getList(Integer pageNo, Integer pageSize, String tab);

    /**
     * 发布
     *
     * @param dto
     * @param principal
     * @return
     */
    BmsPost create(CreateTopicDTO dto, UmsUser principal);

    /**
     * 查看梯子详情
     *
     * @param id
     * @return
     */
    Map<String, Object> viewTopic(String id);

    /**
     * 获取随机推荐10篇
     *
     * @param id
     * @return
     */
    List<BmsPost> getRecommend(String id);

    /**
     * 关键字检索
     *
     * @param keyword
     * @param page
     * @return
     */
    PageInfo<PostVO> searchByKey(String keyword, Integer pageNum, Integer pageSize);
}
