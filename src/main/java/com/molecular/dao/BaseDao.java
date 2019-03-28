package com.molecular.dao;

import org.hibernate.Session;

import java.util.List;

public interface BaseDao {

    public Session getSession();

    public void flush();

    public void clear() ;

    public Object load(Class c, String id) ;

    public List getAllList(Class c) ;

    public Long getTotalCount(Class c) ;

    public void save(Object bean) ;

    public boolean update(Object bean) ;

    public boolean saveOrUpdate(Object bean);

    public boolean delete(Object bean) ;

    public boolean delete(Class c, String id) ;

    public void delete(Class c, String[] ids) ;
}
