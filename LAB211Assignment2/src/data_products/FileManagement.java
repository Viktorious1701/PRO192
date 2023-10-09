/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_products;

import business_products.Employee;
import business_products.Flight;
import business_products.Reservation;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Windows
 */
public class FileManagement {

    public static final String FLIGHT_PATH = "./Flight.dat";
    public static final String RESERVATION_PATH = "./Reservation.dat";
    public static final String CREW_PATH = "./CrewMembers.dat";

    public FileManagement() {
    }

    public void saveToFile(FlightManagement flightManagement, ReservationManagement RManagement, CrewManagement crewManagement) {
        saveFlights(flightManagement);
        saveCrewMembers(crewManagement);
        saveReservations(RManagement);
    }

    private void saveFlights(FlightManagement flightManagement) {
        try (FileOutputStream fos = new FileOutputStream(new File(FLIGHT_PATH));
                ObjectOutputStream oos = new ObjectOutputStream(fos))
        {

            for (Flight i : flightManagement.values())
            {
                oos.writeObject(i);
            }

            System.out.println("=========================================================");
            System.out.println("        Save Flight.dat to file successfully !");
            System.out.println("=========================================================");

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void saveCrewMembers(CrewManagement crewManagement) {
        try (FileOutputStream fos = new FileOutputStream(new File(CREW_PATH));
                ObjectOutputStream oos = new ObjectOutputStream(fos))
        {

            if (!crewManagement.getCrewList().isEmpty())
            {
                for (Employee i : crewManagement.getCrewList())
                {
                    oos.writeObject(i);
                }
            }

            System.out.println("=========================================================");
            System.out.println("        Save CrewMembers.dat to file successfully !");
            System.out.println("=========================================================");

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void saveReservations(ReservationManagement RManagement) {
        try (FileOutputStream fos = new FileOutputStream(new File(RESERVATION_PATH));
                ObjectOutputStream oos = new ObjectOutputStream(fos))
        {

            for (Reservation i : RManagement.getReservation())
            {
                oos.writeObject(i);
            }

            System.out.println("=========================================================");
            System.out.println("        Save Reservation.dat to file successfully !");
            System.out.println("=========================================================");

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadFromFile(FlightManagement flightManagement, ReservationManagement RManagement, CrewManagement crewManagement) throws FileNotFoundException, IOException, ClassNotFoundException {
        loadFlights(flightManagement);
        loadCrewMembers(crewManagement);
        loadReservations(RManagement);
    }

    private void loadFlights(FlightManagement flightManagement) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(new File(FLIGHT_PATH));
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Flight> flightFromFile = new ArrayList<>();

        try
        {
            while (true)
            {
                Flight savedFlight = (Flight) ois.readObject();
                flightFromFile.add(savedFlight);
            }
        } catch (EOFException e)
        {
            // Reached the end of the file, stop reading
        }

        ois.close();

        System.out.println("=========================================================");
        System.out.println("        Load Flight.dat to file successfully !");
        System.out.println("=========================================================");
        // Update the flight management object
        flightManagement.clear();
        for (Flight i : flightFromFile)
        {
            flightManagement.put(i.getFlightCode(), i);
        }
    }

    private void loadCrewMembers(CrewManagement crewManagement) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(new File(CREW_PATH));
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Employee> employeeFromFile = new ArrayList<>();

        try
        {
            while (true)
            {
                Employee savedEmp = (Employee) ois.readObject();
                employeeFromFile.add(savedEmp);
            }
        } catch (EOFException e)
        {
            // Reached the end of the file, stop reading
        }

        ois.close();
        System.out.println("=========================================================");
        System.out.println("        Load CrewMembers.dat to file successfully !");
        System.out.println("=========================================================");
        // Update the crew management object
        crewManagement.getCrewList().clear();
        crewManagement.getCrewList().addAll(employeeFromFile);
    }

    private void loadReservations(ReservationManagement RManagement) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(new File(RESERVATION_PATH));
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Reservation> reservationFromFile = new ArrayList<>();

        try
        {
            while (true)
            {
                Reservation savedRes = (Reservation) ois.readObject();
                reservationFromFile.add(savedRes);
            }
        } catch (EOFException e)
        {
            // Reached the end of the file, stop reading
        }

        ois.close();
        System.out.println("=========================================================");
        System.out.println("        Load Reservation.dat to file successfully !");
        System.out.println("=========================================================");
        // Update the reservation management object
        RManagement.getReservation().clear();
        RManagement.getReservation().addAll(reservationFromFile);
    }

}
