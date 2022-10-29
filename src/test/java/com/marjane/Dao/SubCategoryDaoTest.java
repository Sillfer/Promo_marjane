package com.marjane.Dao;

import com.marjane.entity.SubCategoryEntity;
import org.junit.jupiter.api.Test;

class SubCategoryDaoTest {

    @Test
    void createSubCategory() {
        SubCategoryDao subCategoryDao = new SubCategoryDao();
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
        subCategoryEntity.setScCategory(new CategoryDao().getCategoryById(2).getcId());
        subCategoryEntity.setScName("Phones");
        assert subCategoryDao.createSubCategory(subCategoryEntity);
    }
}