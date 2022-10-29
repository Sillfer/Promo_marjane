package com.marjane.Dao;

import com.marjane.entity.AdminEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminDaoTest {

    @Test
    void createAdmin() {
        AdminDao adminDao = new AdminDao();
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setaName("Mahdi");
        adminEntity.setaEmail("test@gmail.com");
        adminEntity.setaPassword("123456");
        adminDao.createAdmin(adminEntity);
    }
    @Test
    void validateAdminLogin() {
        AdminDao adminDao = new AdminDao();

        Object[] login = {"test@gmail.com", "123456"};
        assertTrue(adminDao.validateAdminLogin(login));

    }
}
