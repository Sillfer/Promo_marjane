package com.marjane.view;

import com.marjane.utils.Menus;

import static com.marjane.utils.MainUtils.*;

public class main {

    private static int choice;
    public static void main(String[] args) {
            choice = Menus.mainMenu();
            if (choice == 1) {
                choice = Menus.loginMenu();
                switch (choice) {
                    // Seperate Users login in 3 cases (SuperAdmin, Admin, Manager)
                    case 1:
                        superAdminWorkFlow();
                        break;
                    case 2:
                        managerWorkflow();
                        break;
                    case 3:
                        respRayonWorkflow();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                }

            }
    }
}
