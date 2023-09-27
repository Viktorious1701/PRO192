/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_products;

import action_service.IWareHouse;
import action_service.Input;
import business_products.DailyProduct;
import business_products.ExportReceipt;
import business_products.ImportReceipt;
import business_products.LongProduct;
import business_products.Product;
import business_products.Warehouse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Windows
 */
public class WarehouseController implements IWareHouse{
    private Warehouse warehouse;
    
    public WarehouseController() {
        this.warehouse = new Warehouse();
    }
    
    public WarehouseController(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
    
    /**
     * Import new product quantity to the store, warehouse will create a receipt to save information
     * @param products 
     */
    @Override
    public void createImportReceipt(List<Product> products) {
        if(products.isEmpty())
            System.out.println("Product list is empty. Proceed to main menu");
        else
        {
        String temp = "";
        int importQuantity = 0;
        // get import code
        String importCode = selfIncrementingCode(warehouse.getImportReceipt().size());
        // get current time stamp since 1/1/1970 
        long creationTime = System.currentTimeMillis();
        List<Product> importList = new ArrayList<>();
        boolean flag = false;
        try
        {
            do{
                flag = false;
                // VALIDATION, code must have been used x
                temp = Input.inputNonBlankStr(" Enter the Product code to import: ");
                
                for(Product i : products){
                    //check if code is there 
                if(i.getProductCode().equals(temp)){
                    
                   importQuantity = Input.inputInt("Current Quantity:" +  + i.getQuantity() + ""
                           + "\n Enter the quantity to import: ",0);
                   // generate the same product type, with new quantiy
                   if(i instanceof DailyProduct){
                        Product x = new DailyProduct("daily",i.getProductCode(), i.getProductName(), i.getManufacturingDate(), i.getExpirationDate(),  importQuantity, i.getPrice(),true,i.isExportState());
                        importList.add(x);
                   }
                   else if (i instanceof LongProduct){
                        Product x = new LongProduct("long",i.getProductCode(), i.getProductName(), i.getManufacturingDate(), i.getExpirationDate(),  importQuantity, i.getPrice(),true,i.isExportState());
                        importList.add(x);
                   }
                   else{
                       System.out.println("Invalid Type of product, return to menu");
                       return;
                   }
                   
                   // add product to the receipt import
                  
                   
                   
                   // change the quantity of the product
                   i.setQuantity(i.getQuantity()+ importQuantity);
                   i.setImportState(true);
                   flag = true;
               }
                
           }
            if(!flag)
                   System.out.println("Invalid Code, Code has not been used! ");
            temp = Input.inputNonBlankStr("Continue to add product to the import receipt? (y to continue, others to stop): ");
        } while (temp.equalsIgnoreCase("y"));
        } catch (Exception e)
        {
            System.out.println("Invalid input");
            System.out.println(e);
        }
        // add an import receipt to list
        
        ImportReceipt newImport = new ImportReceipt(importCode, creationTime, importList);
        if(newImport.getProductList().size() != 0){
            warehouse.getImportReceipt().add(newImport);
            System.out.println("Import Receipt generated successfully!");
        }
        else    
                System.out.println("Import Receipt Failed to generated");
    }
    }
    /**
     * export Product's out of the store (decreasing the products quantity), warehouse will create a receipt to save information
     * @param products 
     */
    @Override
    public void createExportReceipt(List<Product> products) {
        if(products.isEmpty())
            System.out.println("Product list is empty. Proceed to main menu");
        else
        {
            String temp = "";
            int exportQuantity = 0;
            // get export code
            String exportCode = selfIncrementingCode(warehouse.getExportReceipt().size());
            // get current time stamp since 1/1/1970
            long creationTime = System.currentTimeMillis();
            boolean flag = false;
            List<Product> exportList = new ArrayList<>();
            do{
               temp = Input.inputNonBlankStr("Enter the code of product to export: ");
               for(Product i : products){
                   if(i.getProductCode().equals(temp)){
                       //Check if the product quantity has run out 
                       if(i.getQuantity() == 0){
                           flag = true;
                           System.out.println("Product out of stock!");
                           continue;
                       }
                       // VALIDATION: take out the maximum number of the product
                       exportQuantity = Input.inputIntMax("Current Quantity: + " + i.getQuantity() +"\nEnter the quantity to export: ",0, i.getQuantity());
                       // generate the same product type, with new quantiy
                        if(i instanceof DailyProduct){
                            Product x = new DailyProduct("daily",i.getProductCode(), i.getProductName(), i.getManufacturingDate(), i.getExpirationDate(),  exportQuantity, i.getPrice(),true,i.isExportState());
                            exportList.add(x);
                        }
                        else if (i instanceof LongProduct){
                             Product x = new LongProduct("long",i.getProductCode(), i.getProductName(), i.getManufacturingDate(), i.getExpirationDate(),  exportQuantity, i.getPrice(),true,i.isExportState());
                             exportList.add(x);
                        }
                        else{
                            System.out.println("Invalid Type of product, return to menu");
                            return;
                        }
                       // add product to the receipt export list
                       
                       // change the quantity of the product
                       i.setQuantity(i.getQuantity() - exportQuantity);
                       i.setExportState(true);
                       flag = true;
                   }

            }
            if(!flag)
                   System.out.println("Invalid Code, Code has not been used! ");
            temp = Input.inputNonBlankStr("Continue to add product to the import receipt? (y to continue, others to stop): ");
        } while (temp.equalsIgnoreCase("y"));
                // add export receipt to list 
                ExportReceipt newExport = new ExportReceipt(exportCode, creationTime, exportList);
                if(newExport.getProductList().size() != 0 ){
                warehouse.getExportReceipt().add(newExport);    
                System.out.println("Export receipt successfully generated! ");
                }
                else
                    System.out.println("Receipt Failed to generated!");
        }       
    }
    /**
     * create a self increment number used for both import and export receipt
     * @param size
     * @return 
     */
    private String selfIncrementingCode(int size ) {
        
        String result = String.format( "%07d", size + 1); // take import code as 7 digit  0000001,..
       
        System.out.println("Initialize code as: " + result);
        return result;
    }
}
