package com.molecular.dao;

import com.molecular.dao.impl.BaseDaoImpl;
import junit.framework.TestCase;

public class BaseDaoTest extends TestCase {

    private BaseDao baseDao;
    private String venueId;

    public void setUp() throws Exception {
        super.setUp();
        baseDao = new BaseDaoImpl();
        venueId = "6666666";
    }

    public void testDelete() {
//        baseDao.delete(VenueModify.class,venueId);
    }
}