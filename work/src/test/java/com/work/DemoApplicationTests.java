package com.work;

import com.work.user.service.UserService;
import com.work.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailUtil emailUtil;
    @Test
    void contextLoads () throws MessagingException {
        System.out.println();
       // emailUtil.sendSimpleMessage("test","you password reseted as ","1308659229@qq.com","1308659229@qq.com");
    }

}
