package com.marjane.Dao;

import com.marjane.entity.CategoryEntity;

import java.util.List;

public class CategoryDao extends AbstractHibernateDao<CategoryEntity> {

        public CategoryDao() {
            tableName = "category";
            setClazz(CategoryEntity.class);
        }

        // find all categories
        public List getAllCategories() {
            return findAll();
        }

        // find one category by id
        public CategoryEntity getCategoryById(long id) {
            return findOne(id);
        }

        // find one category by name
        public CategoryEntity getCategoryByName(String name) {
            return jpaService.runInTransaction(entityManager -> {
                return entityManager.createQuery("select c from CategoryEntity c WHERE c.cName = :name", CategoryEntity.class)
                        .setParameter("name", name)
                        .getSingleResult();
            });
        }

        // create category
        public void createCategory(CategoryEntity category) {
            create(category);
        }

        // update category
        public CategoryEntity updateCategory(CategoryEntity category) {
            return update(category);
        }

        // delete category
        public void deleteCategory(CategoryEntity category) {
            delete(category);
        }

        // delete category by id
        public void deleteCategoryById(long id) {
            deleteById(id);
        }

        // get all subcategories by category id
        public List getAllSubCategoriesByCategoryId(long id) {
            return jpaService.runInTransaction(entityManager -> {
                return entityManager.createQuery("select s from SubCategoryEntity s WHERE s.scCategory = :id", CategoryEntity.class)
                        .setParameter("id", id)
                        .getResultList();
            });
        }
}

