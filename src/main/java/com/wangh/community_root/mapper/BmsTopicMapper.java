package com.wangh.community_root.mapper;


import com.github.pagehelper.PageInfo;
import com.wangh.community_root.model.entity.BmsPost;
import com.wangh.community_root.model.vo.PostVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

@Repository
public interface BmsTopicMapper extends Mapper<BmsPost> {
    /**
     * 分页查询首页话题列表
     * <p>
     *
     * @param tab
     * @param tab
     * @return
     */
    List<PostVO> selectListAndPage(@Param("tab") String tab);

    /**
     * 获取详情页推荐
     *
     * @param id
     * @return
     */
    List<BmsPost> selectRecommend(@Param("id") String id);

    /**
     * 全文检索
     *
     * @param keyword
     * @return
     */
    PageInfo<PostVO> searchByKey(@Param("keyword") String keyword);
}