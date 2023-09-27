/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import action_service.Menu;
import data_products.FileController;
import data_products.ProductController;
import data_products.ReportController;
import data_products.WarehouseController;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is the main service
 * @author Windows
 */
public class MainController {
    private ProductController productController;
    private WarehouseController warehouseController;
    private ReportController reportController;
    private FileController fileController;
    private static String[] productSubMenu = {
                                        "1 - Add a product",
                                        "2 - update product information",
                                        "3 - Delete product",
                                        "4 - Show all Product"};
    private static String[] warehouseSubMenu = {
                                        "1 - Create an import receipt",
                                        "2 - Create an export receipt"
    };
    private static String[] report = {
                                        "1 - Products that have expired",
                                        "2 - The products that the store is selling",
                                        "3 - Products that are running out of stock (sorted in ascending order)",
                                        "4 - Import/export receipt of a product"
    };
    private static String[] file = {
                                        "1 - save data to file",
                                        "2 - load data to file",
                                        
    };
    /**
     * initialize controller to control Products, warehouse, report and file
     */
    public MainController() {
        productController = new ProductController();
        warehouseController = new WarehouseController();
        reportController = new ReportController();
        fileController = new FileController();
    }
    /**
     * Open the sub menu managing products, and make changes to product list
     */
    public void manageProducts(){
        int subChoice = Menu.showMenu(productSubMenu,1);
                        switch(subChoice){
                            case 1:
                                this.productController.addProduct();
                                break;
                            case 2:
                                this.productController.updateProductInformation(this.warehouseController.getWarehouse());
                                break;
                            case 3:
                                this.productController.deleteProduct();
                                break;
                            case 4:
                                this.productController.ShowAllProducts();
                                break;
                            default:
                                System.out.println("Not a valid choice!");
                        }
    }
    /**
     * open the sub menu managing warehouse, and make change to the warehouse, import/export receipt
     */
    public void manageWarehouse(){
        int subChoice = Menu.showMenu(warehouseSubMenu,2);
                        switch(subChoice){
                            case 1:
                                this.warehouseController.createImportReceipt(this.productController.getProducts());
                                break;
                            case 2:
                                this.warehouseController.createExportReceipt(this.productController.getProducts());
                                break;
                            default:
                                System.out.println("Not a valid choice!");
                        }
    }
    /**
     * open the sub menu managing report, and List out the store, warehouse information including products, import/export receipt
     */
    public void manageReport(){
        int subChoice = Menu.showMenu(report,3);
                        switch(subChoice){
                            case 1:
                                
                                this.reportController.showExpiredProduct(this.productController.getProducts());
                                break;
                            case 2:
                                this.reportController.showSellingProduct(this.productController.getProducts());
                                break;
                            case 3:
                                this.reportController.showProductRunningOutOfStock(this.productController.getProducts());
                                break;
                            case 4:
                                this.reportController.importExportReceipt(this.productController.getProducts(),this.warehouseController.getWarehouse());
                                break;
                            default:
                                System.out.println("Not a valid choice!");
                        }
                        
    }
    /**
     * manage file saving , storing for products.dat, warehouse.dat
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public void storeDataToFile() throws IOException, FileNotFoundException, ClassNotFoundException {
        int subchoice = Menu.showMenu(file,4);
                        switch(subchoice){
                            case 1:
                                this.fileController.saveToFile(this.productController.getProducts(),this.warehouseController.getWarehouse());
                                break;
                            case 2:
                                this.fileController.loadFromFile(this.productController.getProducts(),this.warehouseController.getWarehouse());
                                break;
                           
                            default: 
                                System.out.println("Not a valid choice!");
                        }
    }
   
}
   
