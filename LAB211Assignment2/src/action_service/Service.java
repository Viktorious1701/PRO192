/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import business_products.User;
import data_products.CrewManagement;
import data_products.FileManagement;
import data_products.FlightManagement;
import data_products.ReservationManagement;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows
 */
public class Service implements IService {
    private User currentUser;
    private FlightManagement flightManagement;
    private ReservationManagement RManagement;
    private CrewManagement crewManagement;
    private FileManagement fileManagement;
    public Service() {
        fileManagement = new FileManagement();
        flightManagement = new FlightManagement();
        RManagement = new ReservationManagement();
        crewManagement = new CrewManagement();
    }

    public FileManagement getFileManagement() {
        return fileManagement;
    }

    public void setFileManagement(FileManagement fileManagement) {
        this.fileManagement = fileManagement;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public CrewManagement getCrewManagement() {
        return crewManagement;
    }

    public void setCrewManagement(CrewManagement crewManagement) {
        this.crewManagement = crewManagement;
    }
    
    public FlightManagement getFlightManagement() {
        return flightManagement;
    }

    public void setFlightManagement(FlightManagement flightManagement) {
        this.flightManagement = flightManagement;
    }

    public ReservationManagement getRManagement() {
        return RManagement;
    }

    public void setRManagement(ReservationManagement RManagement) {
        this.RManagement = RManagement;
    }

    @Override
    public void makeReservation() {
        this.RManagement.makeReservation(flightManagement);
    }

    @Override
    public void check_Ins() {
        this.RManagement.checkin(flightManagement);
    }

    @Override
    public void crewAssignment() {
        this.crewManagement.crewAssignMentAndAdminAcess(currentUser, flightManagement);
    }

    @Override
    public void loginForm() {
        currentUser = null;
        int loginChoice = -1;
        do
        {            
            try
            {
                loginChoice = Menu.showMenu(Menu.userType, 2);
                switch(loginChoice){
                    case 1:
                        currentUser = new User("Passenger","", "Passenger");
                        System.out.println("Login Successfully!");
                        break;
                    case 2:
                        String temp = Input.inputNonBlankStr("Enter Password: ");
                        if(temp.equalsIgnoreCase("staff")){
                            currentUser = new User("Staff", "staff", "Staff");
                            System.out.println("Login Successfully!");
                        }else{
                            currentUser = new User("Passenger","", "Passenger");
                            System.out.println("Login FAIL! Login As a Passenger!");
                        }
                        break;
                    case 3:
                        String adminPassword = Input.inputNonBlankStr("Enter Password: ");
                        if(adminPassword.equalsIgnoreCase("admin")){
                            currentUser = new User("admin", "admin", "admin");
                            System.out.println("Login Successfully!");
                        }else{
                            currentUser = new User("Passenger","", "Passenger");
                            System.out.println("Login FAIL! Login as a Passenger!");
                        }
                        break;
                    default: System.out.println("Invalid choice!");
                }
            } catch (Exception e)
            {
                System.out.println("Invalid choice!");
            }
        } while (loginChoice <= 0);
    }

    @Override
    public void saveToFile() {
        fileManagement.saveToFile(flightManagement, crewManagement.getCrewList(), RManagement.getReservation());
    }

    @Override
    public void loadFromFile() {
        try
        {
            fileManagement.loadFromFile(flightManagement, crewManagement, RManagement);
        } catch (IOException ex)
        {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
