package com.marjane.Dao;

import com.marjane.entity.SubCategoryEntity;

import java.util.List;
import java.util.Objects;

public class SubCategoryDao extends AbstractHibernateDao<SubCategoryEntity> {

        public SubCategoryDao() {
            tableName = "subcategory";
            setClazz(SubCategoryEntity.class);
        }

        // find all subcategories
        public List getAllSubCategories() {
            return findAll();
        }

        // find one subcategory by id
        public SubCategoryEntity getSubCategoryById(long id) {
            return findOne(id);
        }

        // find one subcategory by name
        public SubCategoryEntity getSubCategoryByName(String name) {
            return jpaService.runInTransaction(entityManager -> {
                return entityManager.createQuery("select s from SubCategoryEntity s WHERE s.scName = :name", SubCategoryEntity.class)
                        .setParameter("name", name)
                        .getSingleResult();
            });
        }

        // create subcategory
        public boolean createSubCategory(SubCategoryEntity subCategory) {
            if (Objects.equals(subCategory.getScCategory(), null)) {
                System.out.println("Category is null");
                return false;
            } else {
                create(subCategory);
                return true;
            }
        }
        // update subcategory
        public SubCategoryEntity updateSubCategory(SubCategoryEntity subCategory) {
            return update(subCategory);
        }

        // delete subcategory
        public void deleteSubCategory(SubCategoryEntity subCategory) {
            delete(subCategory);
        }

        // delete subcategory by id
        public void deleteSubCategoryById(long id) {
            deleteById(id);
        }

}
