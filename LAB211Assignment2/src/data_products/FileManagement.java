package data_products;

import business_products.Employee;
import business_products.Flight;
import business_products.Reservation;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManagement {

    public static final String PRODUCT_PATH = "./Product.dat";

    public FileManagement() {
    }

    public void saveToFile(Map<String, Flight> flightManagement, List<Employee> crewList, List<Reservation> reservations) {
        // add existing data of flight,crew, reservation management
        ProductData productData = new ProductData(flightManagement, crewList, reservations);

        try (FileOutputStream fos = new FileOutputStream(new File(PRODUCT_PATH));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // write all product data
            oos.writeObject(productData);

            System.out.println("=========================================================");
            System.out.println("        Save Product.dat to file successfully !");
            System.out.println("=========================================================");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(FlightManagement flightManagement, CrewManagement crewManagement, ReservationManagement RManagement)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        // call load function
        ProductData productData = loadProductData();
        if (productData != null) {
            // clear the existing data, and replace it
            flightManagement.clear();
            flightManagement.putAll(productData.getFlightManagement());
            crewManagement.getCrewList().clear();
            crewManagement.getCrewList().addAll(productData.getCrewList());
            RManagement.getReservation().clear();
            RManagement.getReservation().addAll(productData.getReservations());
        }
    }

    private ProductData loadProductData() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(new File(PRODUCT_PATH));
        ObjectInputStream ois = new ObjectInputStream(fis);
        ProductData productData = null;

        try {
            productData = (ProductData) ois.readObject();
        } catch (EOFException e) {
            // Reached the end of the file, stop reading
        }

        ois.close();
        // check if product data has loaded successfully!
        if (productData != null) {
            System.out.println("=========================================================");
            System.out.println("        Load Product.dat from file successfully !");
            System.out.println("=========================================================");
        }

        return productData;
    }
}
