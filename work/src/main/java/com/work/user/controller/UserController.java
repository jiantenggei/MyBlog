package com.work.user.controller;


import com.work.common.results.CommonResult;
import com.work.user.entity.User;
import com.work.user.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jian
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @PostMapping("/login")
    public CommonResult login(@Validated @RequestBody User user){
       return   userService.login(user);
    }

    @PostMapping("/register")
    public CommonResult register(@Validated @RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/resetPwd")
    public CommonResult resetPwd(@Validated @RequestBody User user){
        return userService.resetPwd(user);
    }
    @RequiresAuthentication
    @GetMapping("/logout")
    public CommonResult logout(){
//        SecurityUtils.getSubject().logout();
        return CommonResult.successResponse(null,"退出成功");
    }
    //更具用户名获取用户详细信息
    @GetMapping("getUserByName/{username}")

    public CommonResult getUserByName(@PathVariable("username") String username){
        return userService.getUserByName(username);
    }

}
