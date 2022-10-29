package com.simplon.marjane.Dao;

import com.simplon.marjane.entity.RespRayonEntity;

import java.util.List;

import static com.simplon.marjane.utils.Security.hashPassword;

public class RespRayonDao extends AbstractHibernateDao<RespRayonEntity> {

    public RespRayonDao() {
        tableName = "resp_rayon";
        setClazz(RespRayonEntity.class);
    }

    // find all respRayons
    public List getAllRespRayons() {
        return findAll();
    }

    // find one respRayon by id
    public RespRayonEntity getRespRayonById(long id) {
        return findOne(id);
    }

    // find one respRayon by email
    public RespRayonEntity getRespRayonByEmail(String email) {
        return jpaService.runInTransaction(entityManager -> {
            return entityManager.createQuery("select r from RespRayonEntity r WHERE r.rrEmail = :email", RespRayonEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
        });
    }

    // find one respRayon by email and password
    public static boolean validateRespRayonLogin(Object[] login){
        String email = (String) login[0];
        String password = (String) login[1];
        return jpaService.runInTransaction(entityManager -> {
            return entityManager.createQuery("select r from RespRayonEntity r WHERE r.rrEmail = :email and r.rrPassword = :password", RespRayonEntity.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getResultList().size() > 0;
        });
    }

    // create respRayon

    /**
     * Create respRayon.
     * @param respRayon RespRayonEntity
     */
    public  boolean createRespRayon(RespRayonEntity respRayon) {
        // hash password
        respRayon.setRrPassword(hashPassword(respRayon.getRrPassword()));
       return create(respRayon);
    }

    // update respRayon
    public RespRayonEntity updateRespRayon(RespRayonEntity respRayon) {
        return update(respRayon);
    }

    // delete respRayon
    public void deleteRespRayon(RespRayonEntity respRayon) {
        delete(respRayon);
    }

    // delete respRayon by id
    public void deleteRespRayonById(long id) {
        deleteById(id);
    }


}
