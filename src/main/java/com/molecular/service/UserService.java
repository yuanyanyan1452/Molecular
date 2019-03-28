package com.molecular.service;

import com.molecular.entity.Login;
import com.molecular.entity.User;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface UserService {

    /**
     * 根据id和密码判断是否登录成功
     * @param id
     * @param password
     * @return
     */
    public Login login(String id, String password);

    /**
     *发送邮箱验证码
     * @param toMail
     * @return 验证码
     */
    public String sendCode(String toMail);

    /**
     * 用户注册
     * @param username
     * @param password
     * @param mail
     * @return 系统分配的8位用户识别码（userId）
     */
    public String register(String username,String password,String mail);

    /**
     * 退订退款
     * @param userId
     * @param money
     * @return
     */
    public boolean drawback(String userId,double money);

    /**
     * 支付付款
     * @param userId
     * @param money
     * @return
     */
    public boolean pay(String userId,double money);

    /**
     * 根据userId查找user信息
     * @param userId
     * @return
     */
    public User getUserInfo(String userId);

    /**
     * 修改user信息
     * @param user
     * @return
     */
    public boolean modifyUser(User user);

    /**
     * 获得所有user信息
     * @return
     */
    public List<User> getAllUser();
}
