package com.simplon.marjane.Dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperAdminDaoTest {

    @Test
    void validateSuperAdminLogin() {
        SuperAdminDao superAdminDao = new SuperAdminDao();
        Object[] login = {"mahdi@gmail.com", "123456"};
        assertTrue(superAdminDao.validateSuperAdminLogin(login));


    }
}