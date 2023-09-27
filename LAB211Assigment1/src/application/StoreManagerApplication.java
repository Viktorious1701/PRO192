/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import action_service.Input;
import action_service.Menu;
import action_service.MainController;
import java.util.Scanner;

/**
 *
 * @author Windows
 */
public class StoreManagerApplication {
    private static String[] mainMenu = {
                                        "1 - Manage products",
                                        "2 - Manage Warehouse",
                                        "3 - Report",
                                        "4 - Store data to File",
                                        "0 - Exit the program!"
    };
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainController application = new MainController();
        //set to -1 at the beginning
        int choice = -1;
            do{
                try
                {
                    
                    choice = Menu.showMenu(mainMenu,0);

                    switch(choice){
                        case 0:
                            String temp = Input.inputNonBlankStr("Quit the program? (Y/N): ");
                            if(temp.equalsIgnoreCase("y")){
                                choice = 0;
                            }
                            else if(temp.equalsIgnoreCase("n")){
                                choice = -1;
                            }
                            else {
                                System.out.println("Not a valid choice, Return to Main Menu");
                                choice = -1;
                            }
                            break;
                        case 1:

                            application.manageProducts();
                            break;
                        case 2: 
                            application.manageWarehouse();
                            break;
                        case 3:
                            application.manageReport();
                            break;
                        case 4: 
                            application.storeDataToFile();
                        default: 
                                    System.out.println("returning to main menu");
                                
                }
                } catch (Exception e)
                {
                    System.out.println("your choice is wrong");
                    System.err.println(e);
                }
            } while (choice !=0);
            System.out.println("================================");
            System.out.println("            Goodbye!            ");
            System.out.println("================================");
    }
    
   
}
