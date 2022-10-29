package com.simplon.marjane.Dao;

import com.simplon.marjane.entity.SubCategoryEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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