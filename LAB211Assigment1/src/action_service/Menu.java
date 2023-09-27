/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import java.util.Scanner;

/**
 *
 * @author Windows
 */
public class Menu {
    /**
     * menu board for main menu , sub menu
     * @param list
     * @param choice
     * @return 
     */
     public static int showMenu(String[] list, int choice){
        int kq = 0;
        Scanner sc = new Scanner(System.in);
        String[] menuType = {"===============Store Management at Convenience Store===============",
                             "=================== Product Management Menu =======================",
                             "================== Warehouse Management Menu ======================",
                             "==================  Report Management Menu  =======================",
                             "=================== File Management Menu =========================="
        };
         System.out.println(menuType[choice]);
        for (String s : list) 
            // show the list of choices
            System.out.println(s);
         System.out.println("===================================================================");
        //scan the next choice user wants
        kq = sc.nextInt();
        return kq;
    }
}
