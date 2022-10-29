package com.marjane.Dao;

import com.marjane.services.JpaService;
import java.io.Serializable;
import java.util.List;


/**
 * Class to manage CRUD methods by taking Entity class using generic types
 * @param <T>
 */

public abstract class AbstractHibernateDao<T extends Serializable> {
    
        private Class<T> clazz;

        protected String tableName;
        protected static JpaService jpaService = JpaService.getInstance();
        public void setClazz(Class<T> clazzToSet) {
            this.clazz = clazzToSet;
        }

        //find one by id using entity manager
        public T findOne(long id) {
            return jpaService.runInTransaction(entityManager -> {
                return entityManager.find(clazz, id);
            });
        }

        public List findAll() {
                return jpaService.runInTransaction(entityManager -> {
                    return entityManager.createQuery("from " + clazz.getName()).getResultList();
                });
            }
    
        public boolean create(T entity) {
            return jpaService.runInTransaction(entityManager -> {
                entityManager.persist(entity);
                return true;
            });
        }
    
        public T update(T entity) {
            return jpaService.runInTransaction(entityManager -> {
                return entityManager.merge(entity);
            });
        }
    
        public void delete(T entity) {
            jpaService.runInTransaction(entityManager -> {
                entityManager.remove(entity);
                return null;
            });
        }
    
        public void deleteById(long entityId) {
            T entity = findOne(entityId);
            delete(entity);
        }

        // validate if the entity is already in the database
        public boolean validate(T entity) {
            return jpaService.runInTransaction(entityManager -> {
                return entityManager.contains(entity);
            });
        }




}
