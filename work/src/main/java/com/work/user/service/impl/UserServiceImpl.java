package com.work.user.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.common.results.CommonResult;
import com.work.user.entity.User;
import com.work.user.mapper.UserMapper;
import com.work.user.service.UserService;
import com.work.utils.EmailUtil;
import com.work.utils.GetDateUtil;
import com.work.utils.JwtUtils;
import com.work.utils.RandomPwdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jian
 * @since 2021-05-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private JwtUtils jwtUtils;
    @Autowired
    private  EmailUtil emailUtil;

    @Override
    public CommonResult login(User user) {
        //查询数据库
        User userDb = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername()));

        if (userDb == null) {
            return CommonResult.errorResponse("该用户还未注册！");
        } else {
            if (userDb.getPassword().equals(SecureUtil.md5(user.getPassword()))) {
                //登陆成功 需要差生token
                String token = jwtUtils.generateToken(userDb.getId());
                LocalDateTime data = GetDateUtil.getNetworkTime();
                //1.具有转换功能的对象
                userDb.setLastLogin(data);
                this.updateById(userDb);
                userDb.setToken(token);
                return CommonResult.successResponse(userDb.setPassword(""), "登录成功");
            } else {
                return CommonResult.errorResponse("密码错误！");
            }
        }
    }

    public Boolean isRegister(String username) {
        User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user != null)
            return true;
        return false;
    }

    @Override
    public CommonResult register(User user) {
        if (isRegister(user.getUsername())) {
            return CommonResult.errorResponse("用户名已被注册！");
        }
        LocalDateTime dateTime = GetDateUtil.getNetworkTime();
        String encodePwd = SecureUtil.md5(user.getPassword());
        this.save(user.setPassword(encodePwd).setCreated(dateTime).setStatus(0));
        return CommonResult.successResponse("", "注册成功！");
    }


    @Override
    public CommonResult resetPwd(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        if (isRegister(username)){
            //产生随机密码
            String password =RandomPwdUtil.genRandomNum(8);
            User userDb = this.getOne(new LambdaQueryWrapper<User>()
                    .eq(User::getUsername, username));
            if (email.equals(userDb.getEmail())) {

                userDb.setPassword(SecureUtil.md5(password));
                this.updateById(userDb);
                emailUtil.sendSimpleMessage("RestPassword","you password reset:\n "+password,"1308659229@qq.com",email);
                return CommonResult.successResponse("验证通过", "重置密码已经发送到注册邮箱");
            }else{
                return CommonResult.errorResponse("用户名和邮箱不匹配！");
            }
        }
        return  CommonResult.errorResponse("该用户名未被注册！");
    }

    @Override
    public CommonResult getUserByName(String username) {
        User user =this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername,username));
        return CommonResult.successResponse(user,"成功~");
    }
}
