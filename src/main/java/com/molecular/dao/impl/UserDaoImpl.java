package com.molecular.dao.impl;

import com.molecular.dao.BaseDao;
import com.molecular.dao.UserDao;
import com.molecular.entity.Login;
import com.molecular.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private BaseDao baseDao;

    public Login login(String id, String password) {
        User user = (User) baseDao.load(User.class,id);
        if (user != null && user.getPassword().equals(password))
            return new Login(id,user.getUsername());
        return null;
    }

    public String register(String username, String password, String mail) {

        //先找到最近注册的用户id
        Session session =  baseDao.getSession();
        List<String> list = new LinkedList<String>();
        String hql = "select userId from User order by startTime desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(1);
        list = query.list();
        session.close();

        String userId = "";
        if (list.size() == 0) {
            userId = "10000000";
        }else {
            userId = String.valueOf(Integer.parseInt(list.get(0))+1);
        }
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = df.format(new Date());
//        try {
//            System.out.println("date:"+df.parse(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        User user = new User(userId,username,password,mail,1,0,5000,new Date());
        baseDao.save(user);

        return userId;
    }

    public boolean update(User user) {
        return baseDao.saveOrUpdate(user);
    }

    public User load(String userId) {
        return (User)baseDao.load(User.class,userId);
    }

    public boolean deleteUser(User user) {
        return baseDao.delete(user);
    }

    public List<User> getAllUser() {
        return baseDao.getAllList(User.class);
    }
}
