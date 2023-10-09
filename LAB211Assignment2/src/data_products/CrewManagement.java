/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_products;

import action_service.Input;
import action_service.Menu;
import business_products.Employee;
import business_products.Flight;
import business_products.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Windows
 */
public class CrewManagement implements ICrew, Serializable {

    private List<Employee> crewList;

    public CrewManagement() {
        crewList = new ArrayList<>();
    }

    public CrewManagement(List<Employee> crewList) {
        this.crewList = crewList;
    }

    public List<Employee> getCrewList() {
        return crewList;
    }

    public void setCrewList(List<Employee> crewList) {
        this.crewList = crewList;
    }

    @Override
    public void addCrewMember() {

        Employee newEmp = new Employee();
        String empID = Input.inputValidEmpCode();
        // add if it's empty , and check if there is duplicate code
        if (!this.crewList.isEmpty())
        {
            for (Employee i : this.crewList)
            {
                if (i.getEmpID().equalsIgnoreCase(empID))
                {
                    System.out.println("The ID is existed! Return to main menu..");
                    return;
                }
            }
        }
        String name = Input.inputNonBlankStr("Enter crew member Name:");
        String type;
        int subChoice = Menu.showMenu(Menu.crewType, 3);
        switch (subChoice)
        {
            case 1:
                System.out.println("Assigning member as Pilot");
                type = "pilot";
                break;
            case 2:
                System.out.println("Assigning member as Ground Staff");
                type = "groundStaff";
                break;
            case 3:
                System.out.println("Assigning member as Flight Attendant");
                type = "flightAttendant";
                break;
            default:
                System.out.println("Invalid choice! return to main menu");
                return;
        }
        newEmp.setEmpID(empID);
        newEmp.setName(name);
        newEmp.setRole(type);

        this.crewList.add(newEmp);
        System.out.println("Add new member successfully!");
    }

    @Override
    public void deleteCrewMember() {
        if (!this.crewList.isEmpty())
        {
            String empID = Input.inputValidEmpCode();
            for (Employee i : this.crewList)
            {
                if (i.getEmpID().equalsIgnoreCase(empID))
                {

                    System.out.println("Delete " + empID + " successfully");
                    this.crewList.remove(i);
                    return;
                }
            }
        } else
        {
            System.out.println("Crew member is empty!");
        }
    }

    @Override
    public void assignCrewMember(FlightManagement flightManagement) {
        if (!this.crewList.isEmpty())
        {
            // show all crew members
            showAll();

            String empID = Input.inputValidEmpCode();
            for (Employee i : this.crewList)
            {
                // search emp ID to select the one to choose
                if (i.getEmpID().equalsIgnoreCase(empID))
                {
                    flightManagement.showAllFlights();
                    String FlightID = Input.inputValidCode();
                    if (flightManagement.containsKey(FlightID))
                    {

                        // get the key of the flight
                        Flight flight = new Flight(flightManagement.get(FlightID));
                        if (i.getLocation() == null || i.getLocation().equalsIgnoreCase(flight.getDepCity()))
                        {
                            i.setCurrentFlight(flight);
                            // set the arrival location for the crew members
                            i.setLocation(flight.getDesCity());
                            
                            // set crew member to the flight 
                            flightManagement.get(FlightID).getCrewID().add(empID);
                        } else
                        {
                            System.out.println("The current crew member is in " + i.getLocation() + ", Cannot be assign");
                            return;
                        }
                    }
                }
            }
        } else
        {
            System.out.println("Crew member is empty! ");
        }
    }

    @Override
    public void updateCrewMember() {
        if (!this.crewList.isEmpty())
        {
            // show all crew members
            showAll();
            String empID = Input.inputValidEmpCode();
            for (Employee i : crewList)
            {
                if (i.getEmpID().equalsIgnoreCase(empID))
                {
                    if (i.getCurrentFlight() != null)
                    {
                        System.out.println("Employee already on a flight, Cannot update!");
                    } else
                    {
                        String newName = Input.inputNonBlankStr("Enter the updated Name: ");
                        String type ="";
                        int subChoice = Menu.showMenu(Menu.crewType, 3);
                        switch (subChoice)
                        {
                            case 1:
                                System.out.println("Assigning member as Pilot");
                                type = "pilot";
                                break;
                            case 2:
                                System.out.println("Assigning member as Ground Staff");
                                type = "groundStaff";
                                break;
                            case 3:
                                System.out.println("Assigning member as Flight Attendant");
                                type = "flightAttendant";
                                break;
                            default:
                                System.out.println("Invalid choice! return to main menu");
                                return;
                        }
                        i.setName(newName);
                        i.setRole(type);
                    }
                }
            }
        } else
        {
            System.out.println("Crewlist is empty! ");
        }
    }

    @Override
    public void showAll() {
        Menu.showCrewMembers(this.crewList);
    }

    @Override
    public void crewAssignMentAndAdminAcess(User user, FlightManagement flightManagement) {
        if (!user.getRole().equalsIgnoreCase("passenger"))
        {
            System.out.println("You are log in as " + user + "!");
            int subChoice = Menu.showMenu(Menu.crewMenu, 4);
            switch (subChoice)
            {
                case 1:
                    if (user.getRole().equalsIgnoreCase("admin"))
                    {
                        addCrewMember();
                    } else
                    {
                        System.out.println("Only Administrator have permission, You are log in as " + user + ".");
                    }
                    break;
                case 2:
                    if (user.getRole().equalsIgnoreCase("admin"))
                    {
                        updateCrewMember();
                    } else
                    {
                        System.out.println("Only Administrator have permission, You are log in as " + user + ".");
                    }
                    break;
                case 3:
                    if (user.getRole().equalsIgnoreCase("admin"))
                    {
                        deleteCrewMember();
                    } else
                    {
                        System.out.println("Only Administrator have permission, You are log in as " + user + ".");
                    }
                    break;
                case 4:
                    assignCrewMember(flightManagement);
                    break;
            }
        } else
        {
            System.out.println("You do not have the permission! Only Staff And Administrator can use this..");
        }
    }

}
