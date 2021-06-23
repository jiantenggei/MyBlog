package com.work.blog.mapper;

import com.work.blog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jian
 * @since 2021-05-28
 */
@Mapper
@Component("BlogMapper")
public interface BlogMapper extends BaseMapper<Blog> {

}
