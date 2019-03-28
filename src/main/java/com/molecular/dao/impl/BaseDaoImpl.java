package com.molecular.dao.impl;

import com.molecular.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BaseDaoImpl implements BaseDao{

    @Autowired
    protected SessionFactory sessionFactory;

    public Session getSession() {
        return this.sessionFactory.openSession();
    }

    public void flush() {
        Session session = getSession();
        getSession().flush();
        getSession().close();
    }

    public void clear() {
        getSession().clear();
        getSession().close();
    }

    public Object load(Class c, String id) {
        Session session = getSession();
        Object o = session.get(c,id);
        session.close();
        return o;
    }

    public List getAllList(Class c) {
        String hql = "from " + c.getName();
        Session session = getSession();
        List list = session.createQuery(hql).list();
        session.close();
        return list;
    }

    public Long getTotalCount(Class c) {
        Session session = getSession();
        String hql = "select count(*) from " + c.getName();
        Long count = (Long) session.createQuery(hql).uniqueResult();
        session.close();
        return count != null ? count.longValue() : 0;
    }

    public void save(Object bean) {
        try {
            System.out.println("ready to getsession");
            Session session = getSession() ;
//            session.merge(bean);
            session.save(bean);
            session.flush();
            session.clear();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(Object bean) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        boolean flag = false;
        try{
            session.update(bean);
            transaction.commit();
            flag = true;
        }catch(Exception ex){
            transaction.rollback();
        }finally{
            session.flush();
            session.close();
        }
        return flag;
    }

    public boolean saveOrUpdate(Object bean) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try{
            session.saveOrUpdate(bean);
            tx.commit();
            flag = true;
        }catch(Exception ex){
            tx.rollback();
        }finally{
            session.flush();
            session.close();
        }
        return flag;
    }

    public boolean delete(Object bean) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try{
            session.delete(bean);
            tx.commit();
            flag = true;
        }catch(Exception ex){
            tx.rollback();
        }finally{
            session.flush();
            session.close();
        }
        return flag;
    }

    @SuppressWarnings({"rawtypes"})
    public boolean delete(Class c, String id) {
        Session session = getSession();
        Object obj = session.get(c,id);
        Transaction tx = session.beginTransaction();
        boolean flag = false;
        try{
            session.delete(obj);
            tx.commit();
            flag = true;
        }catch(Exception ex){
            tx.rollback();
        }finally{
            session.flush();
            session.close();
        }
        return flag;
    }

    @SuppressWarnings({"rawtypes"})
    public void delete(Class c, String[] ids) {
        Session session = getSession();
        for (String id : ids) {
            Object obj = session.get(c, id);
            if (obj != null) {
                session.delete(obj);
            }
        }
        flush();
        clear();
    }
}
