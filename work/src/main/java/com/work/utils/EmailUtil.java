package com.work.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
    @Autowired
     JavaMailSenderImpl mailSender;//java已经封装装好的
    /**
     *
     * @param title 邮件标题
     * @param text  邮件内容
     * @param email_from
     * @param email_to
     */
    public  void sendSimpleMessage(String title,String text,String email_from,String email_to){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(title);//设置标题
        mailMessage.setText(text);
        mailMessage.setTo(email_to);
        mailMessage.setFrom(email_from);
        mailSender.send(mailMessage);
    }
}
