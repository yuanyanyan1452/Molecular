package com.molecular.service.impl;

import com.sun.mail.util.MailSSLSocketFactory;
import com.molecular.dao.UserDao;
import com.molecular.entity.Login;
import com.molecular.entity.User;
import com.molecular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public Login login(String id, String password) {
        return userDao.login(id,password);
    }

    public String sendCode(String toMail){
        Properties properties = new Properties();

        //开启debug调试
        properties.setProperty("mail.debug","true");
        //发送服务器需要身份验证
        properties.setProperty("mail.smtp.auth","true");
        //设置邮件服务器主机名
        properties.setProperty("mail.host","smtp.qq.com");
        //发送邮件协议名称
        properties.setProperty("mail.transport.protocol","smtp");

        //开启SSL加密
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return "";
        }
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.ssl.socketFactory",sf);

        //产生4位随机验证码
        Random random = new Random();
        String code = random.nextInt(10000) + "";
        int randLength = code.length();
        if(randLength<4) {
            for (int i = 1; i <= 4 - randLength; i++)
                code = "0" + code;
        }

        //产生邮件内容
        String subject = "黑猫票务-邮箱验证";
        StringBuilder builder = new StringBuilder();
        builder.append("欢迎您使用黑猫票务！");
        builder.append("\n您的验证码是：");
        builder.append("\n"+code);
        builder.append("\n祝好！");

        Session session = Session.getInstance(properties);
        Message msg = new MimeMessage(session);
        try {
            msg.setSubject(subject);
            msg.setText(builder.toString());
            msg.setFrom(new InternetAddress("2029213761@qq.com"));
            Transport transport = session.getTransport();
            transport.connect("smtp.qq.com","2029213761@qq.com","ebishtnhiqnschif");
            transport.sendMessage(msg,new Address[]{new InternetAddress(toMail)});
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
            return "";
        }

        return code;
    }

    public String register(String username, String password, String mail) {
        return userDao.register(username,password,mail);
    }

    public boolean drawback(String userId, double money) {
        User user = userDao.load(userId);
        user.setBalance(user.getBalance()+money);
        return userDao.update(user);
    }

    public boolean pay(String userId, double money) {
        User user = userDao.load(userId);
        user.setBalance(user.getBalance()-money);
        user.setPoint(user.getPoint()+10);
        user.setLevel(user.getPoint()/500 + 1);
        return userDao.update(user);
    }

    public User getUserInfo(String userId) {
        return userDao.load(userId);
    }

    public boolean modifyUser(User user) {
        return userDao.update(user);
    }

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
