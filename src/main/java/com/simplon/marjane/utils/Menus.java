package com.simplon.marjane.utils;

import com.simplon.marjane.Dao.CategoryDao;
import com.simplon.marjane.Dao.SubCategoryDao;
import com.simplon.marjane.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Menus {

    // Main menu
    public static int mainMenu() {
        MainUtils.println("------------------- Main Menu -------------------");
        MainUtils.println("1. Login");
        MainUtils.println("2. Disconnect");
        MainUtils.println("-------------------------------------------------");
        MainUtils.print("Enter your choice: ");
        return MainUtils.scan().nextInt();
    }
    // login menu as SuperAdmin or Admin or manager
    public static int loginMenu() {
        MainUtils.println("------------------- Login Menu -------------------");
        MainUtils.println("1. Login as SuperAdmin");
        MainUtils.println("2. Login as Admin");
        MainUtils.println("3. Login as Manager");
        MainUtils.println("4. Disconnect");
        MainUtils.println("-------------------------------------------------");
        MainUtils.print("Enter your choice: ");
        return MainUtils.scan().nextInt();
    }
    // scan superAdmin email and password to login
    public static Object superAdminLogin() {
        MainUtils.println("------------------- SuperAdmin Login Menu -------------------");
        MainUtils.println("Enter your email: ");
        String email = MainUtils.scan().nextLine();
        MainUtils.println("Enter your password: ");
        String password = MainUtils.scan().nextLine();
        return new Object[]{email, password};
    }
    // scan admin email and password to login
    public static Object adminLoginMenu() {
        MainUtils.println("------------------- Admin Login Menu -------------------");
        MainUtils.println("Enter your email: ");
        String email = MainUtils.scan().nextLine();
        MainUtils.println("Enter your password: ");
        String password = MainUtils.scan().nextLine();
        return new Object[]{email, password};
    }
    // scan manager email and password to login
    public static Object managerLoginMenu() {
        MainUtils.println("------------------- Manager Login Menu -------------------");
        MainUtils.println("Enter your email: ");
        String email = MainUtils.scan().nextLine();
        MainUtils.println("Enter your password: ");
        String password = MainUtils.scan().nextLine();
        return new Object[]{email, password};
    }

    // SuperAdmin main menu Creat manager, Promotions, Statistics, Disconnect
    public static int superAdminMainMenu() {
        MainUtils.println("------------------- SuperAdmin Main Menu -------------------");
        MainUtils.println("1. Create Manager");
        MainUtils.println("2. Promotions");
        MainUtils.println("3. Statistics");
        MainUtils.println("4. Disconnect");
        MainUtils.println("-------------------------------------------------");
        MainUtils.print("Enter your choice: ");
        return MainUtils.scan().nextInt();
    }
    // Admin main menu Creat Responsible Rayon, Promotions, Statistics, Disconnect
    public static int managerMenu() {
        MainUtils.println("------------------- Admin Main Menu -------------------");
        MainUtils.println("1. Create Responsible Rayon");
        MainUtils.println("2. Promotions");
        MainUtils.println("3. Statistics");
        MainUtils.println("4. Disconnect");
        MainUtils.println("-------------------------------------------------");
        MainUtils.print("Enter your choice: ");
        return MainUtils.scan().nextInt();
    }
    // Promotions menu View, Creat, Update, Delete, Exit
    public static int promotionsMenu() {
        MainUtils.println("------------------- Promotions Menu -------------------");
        MainUtils.println("1. View Promotions");
        MainUtils.println("2. Create Promotion");
        MainUtils.println("3. Update Promotion");
        MainUtils.println("4. Delete Promotion");
        MainUtils.println("5. Exit");
        MainUtils.println("-------------------------------------------------");
        MainUtils.print("Enter your choice: ");
        return MainUtils.scan().nextInt();
    }
    // Create promotion
    public static PromotionEntity createNewPromotion(PromotionEntity promotion) {
        List<CategoryEntity> categories = new CategoryDao().getAllCategories();
//        List<SubCategoryEntity> subCategories = new CategoryDao().getAllSubCategories();
        MainUtils.println("------------------- Create Promotion Menu -------------------");
        //display categories
        MainUtils.println("Enter the promotion Category: ");
        for (CategoryEntity category : categories) {
            MainUtils.println(category.getcId() + ". " + category.getcName());
        }
        long cId = MainUtils.scan().nextLong();
        // get category object by id
        CategoryEntity category = new CategoryDao().getCategoryById(cId);
        assert category != null;
        // set category to promotion
        promotion.setPCategory(category);
        // check if category has sub categories
        MainUtils.println("Do you want to add a subCategory? (y/n)");
        String answer = MainUtils.scan().nextLine();
        // if yes
        assert Objects.equals(answer, "y") || Objects.equals(answer, "n");
        if (answer.equals("y")) {
            MainUtils.println("Enter the promotion subCategory: ");
            for (SubCategoryEntity subCategory : category.getSubCategories()) {
                MainUtils.println(subCategory.getScId() + ". " + subCategory.getScName());
            }
            long scId = MainUtils.scan().nextLong();
            SubCategoryEntity subCategory = new SubCategoryDao().getSubCategoryById(scId);
            assert subCategory != null;
            promotion.setPSubCategory(subCategory);
        }else {
            promotion.setPSubCategory(null);
        }
        // set Localdate to promotion
        MainUtils.println("Enter the promotion start date: ");
        // enter year
        MainUtils.println("Enter the year: ");
        int year = MainUtils.scan().nextInt();
        // enter month
        MainUtils.println("Enter the month: ");
        int month = MainUtils.scan().nextInt();
        // enter day
        MainUtils.println("Enter the day: ");
        int day = MainUtils.scan().nextInt();
        promotion.setPStartDate(LocalDate.of(year, month, day));

        // set Localdate to promotion
        MainUtils.println("Enter the promotion end date: ");
        // enter year
        MainUtils.println("Enter the year: ");
        year = MainUtils.scan().nextInt();
        // enter month
        MainUtils.println("Enter the month: ");
        month = MainUtils.scan().nextInt();
        // enter day
        MainUtils.println("Enter the day: ");
        day = MainUtils.scan().nextInt();
        promotion.setPExpireDate(LocalDate.of(year, month, day));
        MainUtils.println("Enter the promotion rate: ");
        int rate = MainUtils.scan().nextInt();
        // set rate to promotion using decimal format
        promotion.setPRate(new BigDecimal(String.valueOf(rate)));
        promotion.setPPointFidelite(rate*10);
        return promotion;
    }
// Manager main menu  Promotions, Statistics, Disconnect
    public static int managerMainMenu() {
        MainUtils.println("------------------- Manager Main Menu -------------------");
        MainUtils.println("1. Promotions");
        MainUtils.println("3. Disconnect");
        MainUtils.println("-------------------------------------------------");
        MainUtils.print("Enter your choice: ");
        return MainUtils.scan().nextInt();
    }

    public static int updatePromotionMenu() {
        MainUtils.println("------------------- Update Promotion Menu -------------------");
        MainUtils.println("1. View Promotions");
        MainUtils.println("2. Exit");
        MainUtils.println("-------------------------------------------------");
        MainUtils.print("Enter your choice: ");
        return MainUtils.scan().nextInt();
    }


    // Create Manager
    public static AdminEntity createManager() {
        MainUtils.println("------------------- Create Manager -------------------");
        MainUtils.println("Enter Manager Name: ");
        String name = MainUtils.scan().nextLine();
        MainUtils.println("Enter Manager Email: ");
        String email = MainUtils.scan().nextLine();
        MainUtils.println("Enter Manager Password: ");
        String password = MainUtils.scan().nextLine();
        return new AdminEntity(name, email, password) ;
    }
    // Create Responsible Rayon
    public static RespRayonEntity createResponsibleRayon() {
        List<CategoryEntity> categories = new CategoryDao().getAllCategories();

        MainUtils.println("------------------- Create Responsible Rayon -------------------");
        MainUtils.println("Enter Responsible Rayon Name: ");
        String name = MainUtils.scan().nextLine();
        MainUtils.println("Enter Responsible Rayon Email: ");
        String email = MainUtils.scan().nextLine();
        MainUtils.println("Enter Responsible Rayon Password: ");
        String password = MainUtils.scan().nextLine();
        //stream to print all categories and get the category id
        MainUtils.println("Enter Responsible Rayon Category: ");
        categories.forEach(category -> {
            MainUtils.println("Category id: " + category.getcId() + " Category name: " + category.getcName());
        });
        MainUtils.println("Enter Category id: ");
        int cId = MainUtils.scan().nextInt();
//        List<SubCategoryEntity> subCategories = new CategoryDao().getAllSubCategoriesByCategoryId(cId);
//        assert subCategories != null;
//        //stream to print all subCategories and get the subCategory id
//        MainUtils.println("Enter Responsible Rayon SubCategory: ");
//        subCategories.forEach(subCategory -> {
//            MainUtils.println("SubCategory id: " + subCategory.getScId() + " SubCategory name: " + subCategory.getScName());
//        });
//        MainUtils.println("Enter SubCategory id: ");



        return new RespRayonEntity(name, email, password,cId);
    }

    // function LocalDate that take parameter int year, int month, int day
    public static LocalDate setLocalDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }
}
