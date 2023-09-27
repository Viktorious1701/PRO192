/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_products;

import action_service.IReport;
import action_service.Input;
import business_products.DailyProduct;
import business_products.ExportReceipt;
import business_products.ImportReceipt;
import business_products.LongProduct;
import business_products.Product;
import business_products.Warehouse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Windows
 */
public class ReportController implements IReport {
    /**
     * Show products that have expiration date before manufacturing date
     * @param products 
     */
    @Override
    public void showExpiredProduct(List<Product> products) {
        List<Product> expiredList = new ArrayList<>();
        for(Product expiredProduct : products){
            if(expiredProduct.getManufacturingDate().after(expiredProduct.getExpirationDate())) // manufacturing date after expiration will be expired
            {
                expiredList.add(expiredProduct);
            }
        }
        ProductController.ShowFilteredProducts(expiredList);
    }
    /**
     * Show what products is on the store , if product's quantity = 0 then filter out
     * @param products 
     */
    @Override
    public void showSellingProduct(List<Product> products) {
        List<Product> sellingList = new ArrayList<>();
        for(Product sellingProduct : products){
            if(sellingProduct.getQuantity() > 0){
                sellingList.add(sellingProduct);
            }
        }
        Collections.sort(sellingList,sortAscending);
        ProductController.ShowFilteredProducts(sellingList);
    }
    /**
     * show Product quantity that is less than 0
     * @param products 
     */
    @Override
    public void showProductRunningOutOfStock(List<Product> products) {
        List<Product> filteredList = new ArrayList<>();
        for(Product runOutProduct : products){
            if(runOutProduct.getQuantity() <= 3){
                filteredList.add(runOutProduct);
            }
        }
        Collections.sort(filteredList,sortAscending);
        ProductController.ShowFilteredProducts(filteredList);
    }
    /**
     * print out the list of import/export receipts that user has created before
     * @param products
     * @param warehouse 
     */
    @Override
    public void importExportReceipt(List<Product> products, Warehouse warehouse) {
        boolean continueFlag = false;
        int index = -1;
        if(products.isEmpty()){
            // ask user if they want to load warehouse file to the current collections.
            String temp = Input.inputNonBlankStr("Product List is empty! Show receipts in warehouse.dat?\n"
                    +                           " (Warning: This will overwrite current data) (y/n):");
            if(temp.equalsIgnoreCase("y")){
                FileController newController = new FileController();
                try
                {
                    // load from file 
                    newController.loadFromFile(products, warehouse);
                    // show all import/export
                    showAllImportExportList(warehouse);
                } catch (IOException ex)
                {
                    Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex)
                {
                    Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else{
                System.out.println("Proceed to main menu! ");
                return;
            }
            return;
        }
        String showAllProducts = Input.inputNonBlankStr("Show all products in data? ( y to show all, n to find all receipt that has a specific product) (y/n):");
        // show all the import export receipts
        if(showAllProducts.equalsIgnoreCase("y")){
            showAllImportExportList(warehouse);
        }
        
        else if(showAllProducts.equalsIgnoreCase("n")){
            
            do
            {   
                String temp = Input.inputNonBlankStr("Enter the product Code: ");
                for(int i = 0 ; i < products.size(); i++){
                    if(products.get(i).getProductCode().equals(temp)){
                        continueFlag = true;
                        // find the product on the product list
                        index = i;
                    }
                }
                if(!continueFlag) 
                    System.out.println("Product code does not exist!");
            } while (!continueFlag);
        
            // find the import/ export receipt of the product

            List<ImportReceipt> filterImportList = new ArrayList<>();
            List<ExportReceipt> filterExportList = new ArrayList();
            
                    // check if there is an import of a product  
                    if(products.get(index).isImportState()){
                        // traverse the list of import receipt
                        for(ImportReceipt i : warehouse.getImportReceipt()){
                            //traverse each productlist of each receipt
                            for(Product j : i.getProductList()){
                                if(j.getProductCode().equals(products.get(index).getProductCode())){
                                    ImportReceipt filteredImport = new ImportReceipt(i.getImportCode(), i.getTimeOfCreation(), new ArrayList<>());
                                    
                                    filteredImport.getProductList().add(j);
                                    filterImportList.add(filteredImport);
                                  
                                }
                            }
                        }
                    }     
                        if(!filterImportList.isEmpty()){
                            //first time flag 
                            boolean firstTime = true;
                                // print out the import/export list related to the product
                            // Print table header
                            System.out.println("------------------------------------------------------|");
                            System.out.println("|               Import Receipt Table                  |");
                            System.out.println("|-----------------------------------------------------|-------------------------|----------------------------|----------------------------|----------|----------|-------------------------|----------|");
                            System.out.println("| ICode          | Time of Creation           | PCode |           PName         |   Manufacturing Date       |      Expiration Date       |  Price   | Quantity | State of Current Product| Type     |");
                            System.out.println("|-----------------------------------------------------|-------------------------|----------------------------|----------------------------|----------|----------|-------------------------|----------|");
                            for(ImportReceipt i : filterImportList){
    
                                for(Product j : i.getProductList()){
                                    System.out.print(i);
                                    System.out.println((j instanceof DailyProduct? ((DailyProduct)j) :((LongProduct)j)));
                                }
                            }
                            System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|");
                    }
                    // check export state   
                    if(products.get(index).isExportState()){
                        // traverse the list of export receipt
                        for(ExportReceipt i : warehouse.getExportReceipt()){
                            //traverse each productlist of each receipt
                            for(Product j : i.getProductList()){
                                if(j.getProductCode().equals(products.get(index).getProductCode())){
                                    //Create a new receipt with only the product specific by user and add to list
                                    ExportReceipt filteredExport = new ExportReceipt(i.getExportCode(), i.getTimeOfCreation(), new ArrayList<>());
                                    filteredExport.getProductList().add(j);
                                    
                                    filterExportList.add(filteredExport);
                                   
                                }
                            }
                        }
                    }
                    if(!filterExportList.isEmpty()){
                          //first time flag 
                            boolean firstTime = true;
                                // print out the import/export list related to the product
                            // Print table header
                            System.out.println("------------------------------------------------------|");
                            System.out.println("|               Export Receipt Table                  |");
                            System.out.println("|-----------------------------------------------------|-------------------------|----------------------------|----------------------------|----------|----------|-------------------------|----------|");
                            System.out.println("| ECode          | Time of Creation           | PCode |           PName         |   Manufacturing Date       |      Expiration Date       |  Price   | Quantity | State of Current Product| Type     |");
                            System.out.println("|-----------------------------------------------------|-------------------------|----------------------------|----------------------------|----------|----------|-------------------------|----------|");
                            for(ExportReceipt i : filterExportList){
                                    
                                    for(Product j : i.getProductList()){
                                        System.out.print(i);
                                        System.out.println((j instanceof DailyProduct? ((DailyProduct)j) :((LongProduct)j)));
                                    }
                             }
                             System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|");
                    }
                    
                    if(filterExportList.isEmpty() && filterImportList.isEmpty() )
                    {
                        System.out.println("---------------------------------------------------------------");
                        System.out.println(" Product has not imported/exported before! ");
                        System.out.println("---------------------------------------------------------------");
                    }
                    
        }
        else{
            System.out.println("Invalid choice! return to menu");
            return;
        }
                
    }
    private static Comparator<Product> sortAscending = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            int a = o1.getQuantity();
            int b = o2.getQuantity();
            if(a > b)
                return 1;
            else if(a < b)
                return -1;
            else
                return 0;
        }
    };

    private void showAllImportExportList(Warehouse warehouse) {
 //first time flag 
 boolean firstTime = true;
 // print out the import/export list related to the product
 // Print table header
 System.out.println("------------------------------------------------------|");
 System.out.println("|               Export Receipt Table                  |");
 System.out.println("|-----------------------------------------------------|-------------------------|----------------------------|----------------------------|----------|----------|-------------------------|----------|");
 System.out.println("| ECode          | Time of Creation           | PCode |           PName         |   Manufacturing Date       |      Expiration Date       |  Price   | Quantity | State of Current Product| Type     |");
 System.out.println("|-----------------------------------------------------|-------------------------|----------------------------|----------------------------|----------|----------|-------------------------|----------|");
 for(ExportReceipt i : warehouse.getExportReceipt()){
     
     for(Product j : i.getProductList()){
         System.out.print(i);
         System.out.println((j instanceof DailyProduct? ((DailyProduct)j) :((LongProduct)j)));
     }
 }
 System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|");
 //import receipt table 
 firstTime = true;
 // print out the import/export list related to the product
 // Print table header
 System.out.println("------------------------------------------------------|");
 System.out.println("|               Import Receipt Table                  |");
 System.out.println("|-----------------------------------------------------|-------------------------|----------------------------|----------------------------|----------|----------|-------------------------|----------|");
 System.out.println("| ICode          | Time of Creation           | PCode |           PName         |   Manufacturing Date       |      Expiration Date       |  Price   | Quantity | State of Current Product| Type     |");
 System.out.println("|-----------------------------------------------------|-------------------------|----------------------------|----------------------------|----------|----------|-------------------------|----------|");
 for(ImportReceipt i : warehouse.getImportReceipt()){
    
     for(Product j : i.getProductList()){
         System.out.print(i);
         System.out.println((j instanceof DailyProduct? ((DailyProduct)j) :((LongProduct)j)));
     }
 }
 System.out.println("|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|");
    
    }
}
