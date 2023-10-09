/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import business_products.Employee;
import business_products.Flight;
import business_products.Reservation;
import business_products.Seat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Windows
 */
public class Menu {

    public static final String[] choice =
    {
        "1. Flight Schedule Management",
        "2. Passenger Reservation and booking",
        "3. Passenger Check-In and Seat Allocation and Availability",
        "4. Crew Management and Assignments",
        "5. Save to file ",
        "6. Print all lists from file ",
        "7. Log in as Administrator",
        "0. Quit the program"
    };
    public static final String[] searchByType =
    {

        "1. Search flight by date",
        "2. Search flight by departure location",
        "3. Seach flight by destination location"
    };
    public static final String[] userType =
    {
        "1. Login As Passenger",
        "2. Login As Airline Staff",
        "3. Login As Administrator"
    };
    public static final String[] crewType =
    {
        "1. Pilot",
        "2. Ground Staff",
        "3. Flight Attendant"
    };
    public static final String[] crewMenu =
    {
        "1. Add a crew member (Administrator only)",
        "2. Update a crew member (Administrator only) ",
        "3. Delete a crew member (Administrator only) ",
        "4. Assign a crew member",

    };

    /**
     * menu board for main menu ,
     *
     * @param list
     * @param choice
     * @return
     */
    public static int showMenu(String[] list, int choice) {
        int kq = 0;
        Scanner sc = new Scanner(System.in);
        // choice here will show the menu type name depending on what the user wants to do
        String[] menuType =
        {
            "=============== Flight Management System ================",
            "=============== Search Available Flight  ================",
            "===================== Login Form ========================",
            "=================== Crew Member Role ====================",
            "=================== Crew Assignment ====================="

        };
        System.out.println(menuType[choice]);
        for (String s : list)
        // show the list of choices
        {
            System.out.println(s);
        }
        System.out.println("=========================================================");
        //scan the next choice user wants
        kq = sc.nextInt();
        return kq;
    }

    /**
     * false == empty seat ,true == occupied seat
     *
     * @param list
     * @param choice
     */
    public static void showAvailableSeat(Seat[] list) {
        int number = 1;
        System.out.println("Remaining Seats Available: ");
        for (int i = 0; i < list.length; i++)
        {

            if (list[i].isOccupied() == false)
            {
                System.out.println("|----------|");
                System.out.println(list[i]);
                System.out.println("|----------|");

            } else
            {
                // skip the seat occupied
                number++;
                continue;
            }
            number++;
        }
        System.out.println("");
    }

    public static void showCrewMembers(List<Employee> crewList ) {
        if (!crewList.isEmpty())
        {
            System.out.println("Crew Members List:");
            System.out.println("|----------|--------------------|---------------|----------|---------------|----------------|--------------------|--------------------|------------|---------------|");
            System.out.println("| empID    |        Name        |     Role      | FlightID |   From. City  |    To. City    |   Departure time   |   Arrival time     | total Seats|   Location    |");
            System.out.println("|----------|--------------------|---------------|----------|---------------|--------------- |--------------------|--------------------|------------|---------------|");
            for (Employee i : crewList)
            {

                System.out.println(i);
                System.out.println("|----------|--------------------|---------------|----------|---------------|--------------- |--------------------|--------------------|------------|---------------|");
            }
        } else

        {
            System.out.println("Crew members are empty!");
        }
    }

    public static void showAllFLightAvailable(List<Flight> flightList) {
        if (!flightList.isEmpty())
        {
            int count = 1;
            System.out.println("Flight List: ");
            Collections.sort(flightList, dateDesc);
            System.out.println("|-------|----------|---------------|----------------|--------------------|--------------------|------------|");
            System.out.println("|Number | FlightID |   From. City  |     To. City   |   Departure Time   |   Arrival Time     | Total Seats|");
            System.out.println("|-------|----------|---------------|----------------|--------------------|--------------------|------------|");
            for (Flight i : flightList)
            {
                System.out.println("|" + count + "      " + i);
                System.out.println("|-------|----------|---------------|----------------|--------------------|--------------------|------------|");
                count++;
            }
        } else
        {
            System.out.println("Flight list is currently Empty");
        }
    }

    public static void showAllReservations(List<Reservation> resList) {
        if (!resList.isEmpty())
        {
            System.out.println("Reservation List: ");
            System.out.println("|---------------|---------------|---------------|---------------|---------------|");
            System.out.println("|Reservation ID |  Flight ID    | P. Name       |  Contact Nums |  Seat Name    |");
            System.out.println("|---------------|---------------|---------------|---------------|---------------|");
            for (Reservation i : resList)
            {
                System.out.println(i);
                System.out.println("|---------------|---------------|---------------|---------------|---------------|");
            }
        } else
        {
            System.out.println("Reservation List is currently empty!");
        }
    }
    private static Comparator<Flight> dateDesc = new Comparator<Flight>() {
        @Override
        public int compare(Flight o1, Flight o2) {
            if (o1.getDepTime().after(o2.getDepTime()))
            {
                return 1;
            } else if (o1.getDepTime().before(o2.getDepTime()))
            {
                return -1;
            } else
            {
                return 0;
            }
        }
    };

    public static void showFlightCrew(List<String> crewID, String flightID) {
        for(String i : crewID){
            System.out.print("| "+ flightID  + " | " +i + " |");
        }
        System.out.println("");
    }
}
