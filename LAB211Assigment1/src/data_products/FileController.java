/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_products;

import business_products.ExportReceipt;
import business_products.ImportReceipt;
import business_products.Product;
import business_products.Warehouse;
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
public class FileController {

    public static final String PRODUCT_PATH = "./Product.dat";
    public static final String WAREHOUSE_PATH = "./WareHouse.dat";

    /**
     *
     * @param products to save to products.dat
     * @param warehouse to save to warehouse.dat
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveToFile(List<Product> products, Warehouse warehouse) {
        try
        {
            //1: Create a stream mapped to the file object on the storage device
            FileOutputStream fos = new FileOutputStream(new File(PRODUCT_PATH));

            //2: Create a service object for writing product data on a previously mapped stream
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //3: Iterate over the product object set to write to the file
            for (Product i : products)
            {
                oos.writeObject(i);
            }

            // close the service object, file
            oos.close();
            fos.close();
            System.out.println("===================================================================");
            System.out.println("        Save products.dat to file successfully !");
            System.out.println("===================================================================");

            // Assuming WarehouseSaveToFile(warehouse) can also throw exceptions
            WarehouseSaveToFile(warehouse);
        } catch (FileNotFoundException e)
        {
            // Handle FileNotFoundException (e.g., log the error or throw a custom exception)
            e.printStackTrace();
        } catch (IOException e)
        {
            // Handle IOException (e.g., log the error or throw a custom exception)
            e.printStackTrace();
        }
    }

    /**
     *
     * @param products to overwrite product list with data in file
     * @param warehouse to overwrite import/export receipt list with data in
     * file
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadFromFile(List<Product> products, Warehouse warehouse) throws FileNotFoundException, IOException, ClassNotFoundException {
        // 1: Create a stream mapped to the file object on the storage device
        FileInputStream fis = new FileInputStream(new File(PRODUCT_PATH));
        // 2: Create a service object for reading product data from the file stream
        ObjectInputStream ois = new ObjectInputStream(fis);
        // 3: Initialize a list to store the loaded products
        List<Product> productFromFile = new ArrayList<>();
        // 4: Read objects from the file until the end of the stream
        try
        {
            while (true)
            {
                Product savedProduct = (Product) ois.readObject();
                productFromFile.add(savedProduct);
            }
        } catch (EOFException e)
        {
            // Reached the end of the file, stop reading
        }
        // 5: Close the service object
        ois.close();
        // -- Notification ------------------------------------
        System.out.println("====================================================================");
        System.out.println("    Load products.dat from file successfully!");
        System.out.println("====================================================================");
        // Do something with the 'products' list containing loaded data.
        // Update the products list object with the loaded data
        products.clear(); // Clear the existing data in products list
        for (Product i : productFromFile)
        {
            products.add(i); // Add the loaded products to list
        }
        loadWarehouseFromFile(warehouse);
    }

    /**
     * method to save in warehouse.dat file
     *
     * @param warehouse to get 2 import/export list
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void WarehouseSaveToFile(Warehouse warehouse) throws FileNotFoundException, IOException {
        //1: Create a stream mapped to the file object on the storage device
        FileOutputStream fos = new FileOutputStream(new File(WAREHOUSE_PATH));
        //2: Create a service object for writing product data on a previously mapped stream
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //3: Iterate over the product object set to write to the file
        try
        {
            // Store ImportReceipt and associated products
            for (ImportReceipt importReceipt : warehouse.getImportReceipt())
            {
                oos.writeObject("ImportReceipt");
                oos.writeObject(importReceipt);
            }
            // Store ExportReceipt and associated products
            for (ExportReceipt exportReceipt : warehouse.getExportReceipt())
            {
                oos.writeObject("ExportReceipt");
                oos.writeObject(exportReceipt);
            }
        } catch (IOException e)
        {
            System.out.println("===================================================================");
            System.out.println("        Save warehouse.dat FAILED !.");
            System.out.println("===================================================================");
            e.printStackTrace();
        } finally
        {
            oos.close();
            fos.close();
        }
        // close the service object, file
        oos.close();
        fos.close();
        System.out.println("===================================================================");
        System.out.println("        Save warehouse.dat to file successfully !.");
        System.out.println("===================================================================");

    }

    /**
     * to load warehouse.dat data to current program
     *
     * @param warehouse
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadWarehouseFromFile(Warehouse warehouse) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(new File(WAREHOUSE_PATH));
        ObjectInputStream ois = new ObjectInputStream(fis);
        warehouse.getImportReceipt().clear();
        warehouse.getExportReceipt().clear();
        try
        {
            while (true)
            {
                String marker;
                try
                {
                    marker = (String) ois.readObject();
                } catch (EOFException e)
                {
                    break; // End of file
                }

                if ("ImportReceipt".equals(marker))
                {
                    ImportReceipt savedImport = (ImportReceipt) ois.readObject();
                    warehouse.getImportReceipt().add(savedImport);
                } else if ("ExportReceipt".equals(marker))
                {
                    ExportReceipt savedExport = (ExportReceipt) ois.readObject();
                    warehouse.getExportReceipt().add(savedExport);
                } else
                {
                    // Handle unrecognized marker if needed
                }
            }
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println("===================================================================");
            System.out.println("        Load warehouse.dat FAILED !.");
            System.out.println("===================================================================");
            e.printStackTrace();
        } finally
        {
            ois.close();
        }

        System.out.println("====================================================================");
        System.out.println("    Load warehouse.dat successfully!");
        System.out.println("====================================================================");
    }

}
