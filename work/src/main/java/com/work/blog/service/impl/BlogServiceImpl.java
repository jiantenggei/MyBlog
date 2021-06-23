package com.work.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.blog.entity.Blog;
import com.work.blog.mapper.BlogMapper;
import com.work.blog.service.BlogService;
import com.work.blog.vo.PageVo;
import com.work.common.results.CommonResult;
import com.work.user.entity.User;
import com.work.user.service.UserService;
import com.work.utils.GetDateUtil;
import com.work.utils.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jian
 * @since 2021-05-28
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    private UserService userService;
    @Override
    public CommonResult getOneById(Long blogId) {
        Blog blog = this.getById(blogId);
        Assert.notNull(blog, "该博文已被删除");
        return CommonResult.successResponse(blog, "查询成功!");
    }

    @Override
    public CommonResult getBlogs(PageVo pageVo) {

        Integer pageSize = pageVo.getPageSize();
        Integer pageNo = pageVo.getPageNo();
        String  blogName = pageVo.getBlogName();

        Page page = new Page(pageNo,pageSize);
        IPage pageData = this.page(page,new LambdaQueryWrapper<Blog>()
                .like(!StringUtils.isEmpty(blogName),Blog::getTitle,blogName).orderByDesc(Blog::getGmtCreate));
        return CommonResult.successResponse(pageData,"查询成功！");
    }

    @Override
    public CommonResult edit(Blog blog) {
        Blog blogTemp=null;
        if (blog.getId()!=null){ //提交编辑好的博客
            blogTemp = this.getById(blog.getId());
            //比对用户id和博客的id是否一样，如果不一样则不能编辑
            Assert.isTrue(blogTemp.getUserId().longValue()
                    == ShiroUtil.getProfile().getId().longValue(),"没有编辑权限");
        }else{ //添加新博客
            blogTemp = new Blog();
            //这里我还需要去写入一个用户
            User user =userService.getById(ShiroUtil.getProfile().getId());
            blogTemp.setUserId(ShiroUtil.getProfile().getId());
            blogTemp.setGmtCreate(GetDateUtil.getNetworkTime());
            blogTemp.setStatus(0);
            blogTemp.setAuthor(user.getUsername());
        }
        BeanUtil.copyProperties(blog,blogTemp,"id","userId","gmtCreate","status","author");
        this.saveOrUpdate(blogTemp);
        return CommonResult.successResponse(null,"操作成功！");
    }

    @Override
    public CommonResult getBlogsByUser(String userName) {
        List<Blog>  blogs = this.list(new LambdaQueryWrapper<Blog>().eq(Blog::getAuthor,userName));
        return CommonResult.successResponse(blogs,"查询成功~");
    }
}
