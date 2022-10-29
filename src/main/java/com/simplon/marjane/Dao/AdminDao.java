package com.simplon.marjane.Dao;

import com.simplon.marjane.entity.AdminEntity;
import com.simplon.marjane.services.JpaService;

import java.util.List;

import static com.simplon.marjane.utils.MainUtils.println;
import static com.simplon.marjane.utils.Security.checkPassword;
import static com.simplon.marjane.utils.Security.hashPassword;

public class AdminDao extends AbstractHibernateDao<AdminEntity> {

    public AdminDao() {
        tableName = "admin";
        setClazz(AdminEntity.class);
    }

    // find all admins
    public List getAllAdmins() {
        return findAll();
    }

    // find one admin by id
    public AdminEntity getAdminById(long id) {
        return findOne(id);
    }

    // find one admin by email
    public AdminEntity getAdminByEmail(String email) {
        return jpaService.runInTransaction(entityManager -> {
            return entityManager.createQuery("select a from AdminEntity a WHERE a.aEmail = :email", AdminEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
        });
    }

    // find one admin by email and password
    public boolean validateAdminLogin(Object[] login){
        String email = (String) login[0];
        String password = (String) login[1];
        AdminEntity admin = getAdminByEmail(email);
        if (admin != null){
            println("email valid");
        }else {
            println("wrong email");
        }
        assert admin != null;
        if (checkPassword(password, admin.getaPassword())){
            println("connected with success");
            return true;
        }else {
            println("wrong password");
            return false;
        }
    }

    // create admin
    public boolean createAdmin(AdminEntity admin) {
        admin.setaPassword(hashPassword(admin.getaPassword()));
        return create(admin);
    }

    // update admin
    public AdminEntity updateAdmin(AdminEntity admin) {
        return update(admin);
    }

    // delete admin
    public void deleteAdmin(AdminEntity admin) {
        delete(admin);
    }

    // delete admin by id
    public void deleteAdminById(long id) {
        deleteById(id);
    }
}


