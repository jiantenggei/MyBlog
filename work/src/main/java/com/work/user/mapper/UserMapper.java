package com.work.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jian
 * @since 2021-05-27
 */
@Mapper
@Component("UserMapper")
public interface UserMapper extends BaseMapper<User> {

}
