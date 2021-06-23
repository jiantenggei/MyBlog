package com.work.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.work.common.results.CommonResult;
import com.work.user.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jian
 * @since 2021-05-27
 */
public interface UserService extends IService<User> {

    CommonResult login(User user);

    CommonResult register(User user);

    CommonResult resetPwd(User user);

    CommonResult getUserByName(String username);
}
