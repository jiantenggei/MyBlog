package com.work.utils;

import com.work.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;


/**
 * @Author: Himit_ZH
 * @Date: 2020/7/20 14:13
 * @Description:
 */
public class ShiroUtil {
    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
