package com.simplon.marjane.utils;

import com.simplon.marjane.Dao.AdminDao;
import com.simplon.marjane.Dao.PromotionDao;
import com.simplon.marjane.Dao.RespRayonDao;
import com.simplon.marjane.Dao.SuperAdminDao;
import com.simplon.marjane.entity.AdminEntity;
import com.simplon.marjane.entity.PromotionEntity;
import com.simplon.marjane.entity.RespRayonEntity;

import java.util.List;
import java.util.Scanner;

public class MainUtils {

    private static int choice;

    public static void println(String text) {
        System.out.println(text);
    }

    public static void print(String text) {
        System.out.print(text);
    }

    public static Scanner scan() {
        return new Scanner(System.in);
    }

    public static void superAdminWorkFlow() {
        // superAdminWorkFlow display menu login and superAdmin main menu
        // store email and password in an array
        Object[] superAdminLogin = (Object[]) Menus.superAdminLogin();

        SuperAdminDao superAdminDao = new SuperAdminDao();
        // check if email and password are correct
        if (superAdminDao.validateSuperAdminLogin(superAdminLogin)) {
            println("SuperAdmin login successfully");
            // do while loop to display superAdmin main menu
            do {
                choice = Menus.superAdminMainMenu();
                switch (choice) {
                    case 1:
                        // Create Manager
                        AdminEntity newManager = Menus.createManager();
                        AdminDao adminDao = new AdminDao();
                        String password = newManager.getaPassword();
                        if (adminDao.createAdmin(newManager)) {
                            println("Manager created successfully");
                            SimpleEmail.sendSimpleEmail("gliouinemahdi@gmail.com",
                                    "Welcome to Marjane",
                                    "Hello, admin " + newManager.getaName() + " this email containes your login information <br>"
                                            + " your email is: <B>" + newManager.getaEmail() + "</B> <br>"
                                            + " your password is: <B>" + password + "</B> <br>");
                        } else {
                            println("Manager not created");
                        }
                        break;
                    case 2:
                        // Promotions
                        println("-------------------- List Of Promotions ---------------------");
                        PromotionDao promotionDao = new PromotionDao();//find all promotions and display them
                        List<PromotionEntity> promotions = promotionDao.findAll();
                        for (PromotionEntity promotion : promotions) {
                            println(promotion.toString());
                        }

                        break;
                    case 3:
                        // Statistics
                        // TODO : Statistics Menu and methods to display statistics of Marjane app and Users
                        // print this action is in maintenance
                        println("Statistics in maintenance");
                        break;
                    case 4:
                        // Exit
                        break;
                    default:
                        println("Invalid choice");
                        break;
                }

            } while (choice != 4);
        } else {
            println("SuperAdmin login failed");
        }


    }

    public static void managerWorkflow() {
        // adminWorkflow display menu login and admin main menu
        Object[] adminLogin = (Object[]) Menus.adminLoginMenu();
        AdminDao adminDao = new AdminDao();
        if (adminDao.validateAdminLogin(adminLogin)) {
            do {
                choice = Menus.managerMenu();
                switch (choice) {
                    case 1:
                        // Create Responsable Rayon
                        RespRayonDao respRayonDao = new RespRayonDao();
                        RespRayonEntity newRespRayon = Menus.createResponsibleRayon();
                        String password = newRespRayon.getRrPassword();
                        if (respRayonDao.createRespRayon(newRespRayon)) {
                            println("Responsable Rayon created successfully");
                            // send email with login and password information
                            SimpleEmail.sendSimpleEmail("oussamaelbechari@gmail.com", "Welcome to Marjane",
                                    "Your email is : <B> " + newRespRayon.getRrEmail() + "</B> and your password is: <B>" + password + "</B>");
                        } else {
                            println("Responsable Rayon creation failed");
                        }
                        break;
                    case 2:
                        // Promotion menu
                        choice = Menus.promotionsMenu();
                        // while loop to display promotions menu
                        while (choice != 4) {
                            PromotionDao promotionDao = new PromotionDao();
                            switch (choice) {
                                case 1:
                                    // view all promotions
                                    List<PromotionEntity> promotions = promotionDao.findAll();
                                    for (PromotionEntity promotion : promotions) {
                                        println(promotion.toString());
                                    }
                                    break;
                                case 2:
                                    // Create Promotion using setters
                                    PromotionEntity newPromotion = Menus.createNewPromotion(new PromotionEntity());
                                    if (promotionDao.createPromotion(newPromotion)) {
                                        println("Promotion created successfully");
                                    } else {
                                        println("Promotion creation failed");
                                    }
                                    break;
                                case 3:
                                    // Update Promotion
                                    break;
                                case 4:
                                    // Delete Promotion
                                    break;
                                default:
                                    println("Invalid choice");
                                    break;
                            }
                            choice = Menus.promotionsMenu();
                        }
                        break;
                    case 3:
                        // Create statistics
                        break;
                    case 4:
                        // Exit
                        break;
                    default:
                        println("Invalid choice");
                        break;
                }

            } while (choice != 6);
        } else {
            println("Admin login failed");
        }
    }

    public static void respRayonWorkflow() {
        // respRayonWorkflow display menu login and manager main menu
        Object[] managerLogin = (Object[]) Menus.managerLoginMenu();
        PromotionDao promotionDao = new PromotionDao();
        RespRayonDao respRayonDao = new RespRayonDao();
        if (respRayonDao.validateRespRayonLogin(managerLogin)) {
            do {
                choice = Menus.managerMainMenu();
                switch (choice) {
                    case 1:
                        List<PromotionEntity> promotions = promotionDao.findAll();
                        for (PromotionEntity promotion : promotions) {
                            println(promotion.toString());
                        }
                        break;
                    case 2:
                        // Exit
                        break;
                    default:
                        println("Invalid choice");
                        break;
                }

            } while (choice != 2);
        } else {
            println("Responsable Rayon login failed");
        }

    }

}
