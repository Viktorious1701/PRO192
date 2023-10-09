/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import action_service.Input;
import action_service.Menu;
import action_service.Service;
import business_products.User;

/**
 *
 * @author Windows
 */
class Program {

    public void executed() {
        
        Service service = new Service();
        service.loadFromFile();
        service.loginForm();
        int choice = -1;
        do
        {            
            try
            {
                choice = Menu.showMenu(Menu.choice,0);
                switch(choice){
                    case 0:
                        String temp = Input.inputNonBlankStr("Do you want to quit? (y/n): ");
                        if(temp.equalsIgnoreCase("y"))
                            choice = -1;
                        else
                            System.out.println("Back to main menu!");
                        break;
                    case 1: 
                        if(service.getCurrentUser().getRole().equalsIgnoreCase("admin") || service.getCurrentUser().getRole().equalsIgnoreCase("staff"))
                            service.getFlightManagement().addAFlight();
                        else
                            System.out.println("You do not have the permission to add flights!");
                        break;
                    case 2:
                        service.makeReservation();
                        break;
                    case 3:
                        service.check_Ins();
                        break;
                    case 4:
                        service.crewAssignment();
                        break;
                    case 5:
                        service.saveToFile();
                        break;
                    case 6:
                        service.getFlightManagement().showAllFlights();
                        service.getRManagement().showAll();
                        service.getCrewManagement().showAll();
                   
                        break;
                    case 7:
                        service.loginForm();
                        break;
                }
            } catch (Exception e)
            {
                System.out.println("Your choice is invalid!");
            }
        } while (choice != -1);
    }
    
}
