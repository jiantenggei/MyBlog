package com.work.blog.controller;


import com.work.blog.entity.Blog;
import com.work.blog.service.BlogService;
import com.work.blog.vo.PageVo;
import com.work.common.results.CommonResult;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jian
 * @since 2021-05-28
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    //根据id查博客
    @GetMapping("/getOneById/{id}")
    public CommonResult getOneById(@PathVariable("id") Long blogId){
        return blogService.getOneById(blogId);
    }
    //分页查询
    @PostMapping("/getBlogs")
    public CommonResult getBlogs(@RequestBody PageVo pageVo){
        return blogService.getBlogs(pageVo);
    }

    //编辑博客
    @RequiresAuthentication //需要登录注解
    @PostMapping("/edit")
    public CommonResult edit(@Validated @RequestBody Blog blog){
        return blogService.edit(blog);
    }
    //编辑博客
    @RequiresAuthentication //需要登录
    @DeleteMapping("deleteById/{id}")
    public CommonResult deleteById(@PathVariable("id") Long blogId){
        boolean isRemoved = blogService.removeById(blogId);

        Assert.isTrue(isRemoved,"删除失败！该博客可能已经被删除");
        return CommonResult.successResponse(null,"删除成功！");
    }
    //更具用户查博客
    @RequiresAuthentication
    @GetMapping("/getBlogsByUser/{username}")
    public CommonResult getBlogsByUser(@PathVariable("username") String userName){
        return blogService.getBlogsByUser(userName);
    }

}
