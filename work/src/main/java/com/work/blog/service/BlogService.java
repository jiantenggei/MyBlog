package com.work.blog.service;

import com.work.blog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.blog.vo.PageVo;
import com.work.common.results.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jian
 * @since 2021-05-28
 */
public interface BlogService extends IService<Blog> {

    CommonResult getOneById(Long blogId);

    CommonResult getBlogs(PageVo pageVo);

    CommonResult edit(Blog blog);

    CommonResult getBlogsByUser(String userName);
}
