package com.molecular.dao;

import com.molecular.entity.Login;
import com.molecular.entity.User;

import java.util.List;

public interface UserDao {

    /**
     * 根据id和密码判断是否登录成功
     * @param id
     * @param password
     * @return
     */
    public Login login(String id, String password);

    /**
     * 用户注册
     * @param username
     * @param password
     * @param mail
     * @return 系统分配的8位用户识别码（userId）
     */
    public String register(String username,String password,String mail);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public boolean update(User user);

    /**
     * 根据userId查找用户信息
     * @param userId
     * @return
     */
    public User load(String userId);

    /**
     * 根据userId删除用户
     * @param user
     * @return
     */
    public boolean deleteUser(User user);

    /**
     * 获得所有user信息
     * @return
     */
    public List<User> getAllUser();

}
