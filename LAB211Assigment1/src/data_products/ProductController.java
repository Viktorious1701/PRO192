/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_products;

import action_service.IStore;
import action_service.Input;
import business_products.DailyProduct;
import business_products.ExportReceipt;
import business_products.ImportReceipt;
import business_products.LongProduct;
import business_products.Product;
import business_products.Warehouse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Windows
 */
public class ProductController implements IStore {

    private List<Product> products;

    public ProductController() {
        this.products = new ArrayList<>();
    }

    public ProductController(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * add new product Information to the store
     */
    @Override
    public void addProduct() {
        Product newProduct;
        String continueAdd = "";
        do
        {
            String optionForProduct = Input.inputNonBlankStr("Enter the type of product to add:\n1. Daily Product\n2. Long Product\n");
            if (optionForProduct.equalsIgnoreCase("daily") || optionForProduct.equalsIgnoreCase("1"))
            {
                newProduct = new DailyProduct();

            } else if (optionForProduct.equalsIgnoreCase("long") || optionForProduct.equalsIgnoreCase("2"))
            {
                newProduct = new LongProduct();

            } else
            {
                System.out.println("Incorrect input, return to main menu!");
                return;
            }
            productValidation(newProduct);
            continueAdd = Input.inputNonBlankStr("Continue to add product? (y/n):");
        } while (continueAdd.equals("y"));

    }

    public void productValidation(Product newProduct) {
        String temp = "";
        boolean flag = false;
        do
        {
            flag = false; // reset flag 
            temp = Input.inputNonBlankStr("Enter the product code: ");
            if (products.isEmpty())
            {
                newProduct.setProductCode(temp);
            } else
            {
                for (Product i : this.products)
                {
                    if (i.getProductCode().equals(temp))
                    {
                        flag = true;
                        System.out.println("Code has already been used");
                    }
                }
            }
        } while (flag == true);

        // set new product code
        newProduct.setProductCode(temp.trim());
        //reset flag
        flag = false;

        // set new product name
        temp = Input.inputNonBlankStr("Enter the product name: ");
        newProduct.setProductName(temp.trim());
        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");

        do
        {
            try
            {
                temp = Input.inputNonBlankStr("Enter the product manufacturing date (DD/MM/YYYY): ");
                Date date = null;
                // If parsing succeeds, the date is valid
                if (isValidDate(temp))
                {
                    date = sdf.parse(temp);
                    flag = true;
                } else
                {
                    System.out.println("Invalid date! try again.");
                }

                //set manufacturing date
                newProduct.setManufacturingDate(date);
            } catch (ParseException e)
            {
                System.out.println("Invalid date format. Please use the format DD/MM/YYYY.");
            }
        } while (!flag);
        // reset flag
        flag = false;
        do
        {
            try
            {
                temp = Input.inputNonBlankStr("Enter the product expiration date (DD/MM/YYYY): ");
                Date date = null;
                // If parsing succeeds, the date is valid
                if (isValidDate(temp))
                {
                    date = sdf.parse(temp);
                    flag = true;
                } else
                {
                    System.out.println("Invalid date! try again.");
                }
                //set expirationdate date
                newProduct.setExpirationDate(date);
            } catch (ParseException e)
            {
                System.out.println("Invalid date format. Please use the format DD/MM/YYYY.");
            }
        } while (!flag);
        //reset flag
        flag = false;

        // set Price, Quantity 
        int newPrice = 0;
        newPrice = Input.inputInt("Enter the price of the product: ", -1);
        newProduct.setPrice(newPrice);

        int newQuantity = 0;
        newQuantity = Input.inputInt("Enter the Quantity of the product: ", -1);
        newProduct.setQuantity(newQuantity);

        // add to list
        products.add(newProduct);
        System.out.println("Added Product successfully");

    }

    /**
     * Update product information
     *
     * @param warehouse is used to update the warehouse import/export product
     * information
     */
    @Override
    public void updateProductInformation(Warehouse warehouse) {
        boolean continueFlag = false;
        boolean flag = false;
        if (this.products.isEmpty())
        {
            System.out.println("Product list is empty. Proceed to main menu");
        } else
        {
            do
            {
                String temp = Input.inputNonBlankStr("Enter the product Code to update ( Product code remain unchanged): ");
                for (Product i : this.getProducts())
                {
                    if (i.getProductCode().equals(temp))
                    {
                        continueFlag = true;
                        temp = Input.inputStr("Enter the product name (press enter to continue without change):");
                        if (!temp.trim().isEmpty())
                        {
                            i.setProductName(temp);
                        }

                        //set new Manufacturing date, Expiration Date
                        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
                        do
                        {
                            try
                            {
                                temp = Input.inputStr("Enter the product manufacturing date (DD/MM/YYYY): ");
                                if (!temp.trim().isEmpty())
                                {
                                    Date date = null;
                                    // If parsing succeeds, the date is valid
                                    if (isValidDate(temp))
                                    {
                                        date = sdf.parse(temp);
                                        flag = true;
                                    } else
                                    {
                                        System.out.println("Invalid date! try again.");
                                    }

                                    //set manufacturing date
                                    i.setManufacturingDate(date);
                                } else
                                {
                                    flag = true;
                                }
                            } catch (ParseException e)
                            {
                                System.out.println("Invalid date format. Please use the format DD/MM/YYYY.");
                            }
                        } while (!flag);
                        // reset flag
                        flag = false;
                        do
                        {
                            try
                            {

                                temp = Input.inputStr("Enter the product expiration date (DD/MM/YYYY): ");
                                if (!temp.trim().isEmpty())
                                {
                                    Date date = null;
                                    // If parsing succeeds, the date is valid
                                    if (isValidDate(temp))
                                    {
                                        date = sdf.parse(temp);
                                        flag = true;
                                    } else
                                    {
                                        System.out.println("Invalid date! try again.");
                                    }
                                    //set expirationdate date
                                    i.setExpirationDate(date);
                                } else
                                {
                                    flag = true;
                                }
                            } catch (ParseException e)
                            {
                                System.out.println("Invalid date format. Please use the format DD/MM/YYYY.");
                            }
                        } while (!flag);

                        //reset flag
                        flag = false;

                        // set new Price
                        do
                        {
                            int newPrice = 0;
                            temp = Input.inputStr("Enter the price of the product: ");
                            if (temp.trim().isEmpty())
                            {
                                flag = true;
                            } else if (Integer.parseInt(temp) >= 0)
                            {
                                newPrice = Integer.parseInt(temp);
                                i.setPrice(newPrice);
                                flag = true;
                            }

                        } while (!flag);

                        // check the information if product is import/export
                        if (i.isImportState())
                        {
                            // look to the list of receipt, and then look to the productlist of EACH receipt
                            for (ImportReceipt j : warehouse.getImportReceipt())
                            {
                                for (Product z : j.getProductList())
                                {
                                    if (z.getProductCode().equals(i.getProductCode()))
                                    {
                                        // Update the product in the import receipt
                                        z.setProductName(i.getProductName());
                                        z.setManufacturingDate(i.getManufacturingDate());
                                        z.setExpirationDate(i.getExpirationDate());
                                        z.setPrice(i.getPrice());
                                        //i is the newly updated product, z is the not updated same product
                                    }
                                }
                            }
                        }

                        if (i.isExportState())
                        {
                            // look to the list of receipt, and then look to the productlist of EACH receipt
                            for (ExportReceipt j : warehouse.getExportReceipt())
                            {
                                for (Product z : j.getProductList())
                                {
                                    if (z.getProductCode().equals(i.getProductCode()))
                                    {
                                        // Update the product in the import receipt
                                        z.setProductName(i.getProductName());
                                        z.setManufacturingDate(i.getManufacturingDate());
                                        z.setExpirationDate(i.getExpirationDate());
                                        z.setPrice(i.getPrice());
                                        //i is the newly updated product, z is the not updated same product
                                    }
                                }
                            }
                        }

                    }
                }
                if (!flag)
                {
                    System.out.println("Code does not exist");
                }
            } while (!continueFlag);
            System.out.println("Product updated sucessfully!");
        }
    }

    /**
     * delete an existing product from the store CANNOT delete product that is
     * imported/exported
     */
    @Override
    public void deleteProduct() {
        if (this.products.isEmpty())
        {
            System.out.println("Product list is empty. Proceed to main menu");
        } else
        {
            boolean flag = false;
            int index = -1; // Initialize the index with -1 to indicate that the product was not found

            String choice = Input.inputNonBlankStr("Enter the product code of the product to be deleted: ");

            for (int i = 0; i < products.size(); i++)
            {
                Product product = products.get(i);

                // Check if the product code is correct and has not been imported or exported before
                if (product.getProductCode().equals(choice) && !product.isImportState() && !product.isExportState())
                {
                    // product found, hide notification
                    flag = true;
                    choice = Input.inputNonBlankStr("Confirm to delete product forever? (y/n):");

                    if (choice.equalsIgnoreCase("y"))
                    {
                        products.remove(i);
                        System.out.println("Deleted successfully");
                    } else if (choice.equalsIgnoreCase("n"))
                    {
                        System.out.println("Failed to delete the product");
                    } else
                    {
                        System.out.println("Wrong choice, proceed to the main menu without deleting.");
                    }

                    // Set the index to the current value of 'i'
                    index = i;
                    break; // Exit the loop once the product is found and processed
                }
            }

            if (!flag)
            {
                System.out.println("Product code not found or the product has already been imported/exported.");
            } else if (index != -1)
            {
                System.out.println("Product found at index: " + index);
            }
        }
    }

    /**
     * List all products of the store
     */
    @Override
    public void ShowAllProducts() {
        if (products == null || products.isEmpty())
        {
            System.out.println("You don't have any products to show");
        } else
        {
            //header
            System.out.println("|-------|-------------------------|----------------------------|----------------------------|----------|----------|------------|------------|----------|");
            System.out.println("|  Code |            Name         |   Manufacturing Date       |      Expiration Date       |  Price   | Quantity |  IsImport  |  IsExport  | Type     |");
            for (Product i : products)
            {

                if (i instanceof DailyProduct)
                {
                    System.out.println((DailyProduct) i);
                } else if (i instanceof LongProduct)
                {
                    System.out.println((LongProduct) i);
                } else
                {
                    System.out.println(i + " N/A |");
                }
            }
            //footer
            System.out.println("|-------|-------------------------|----------------------------|----------------------------|----------|----------|------------|------------|----------|");

        }
    }

    public static void ShowFilteredProducts(List<Product> products) {
        if (products == null || products.isEmpty())
        {
            System.out.println("You don't have any products to show");
        } else
        {
            //header
            System.out.println("|-------|-------------------------|----------------------------|----------------------------|----------|----------|------------|------------|----------|");
            System.out.println("|  Code |            Name         |   Manufacturing Date       |      Expiration Date       |  Price   | Quantity |  IsImport  |  IsExport  | Type     |");
            for (Product i : products)
            {
                if (i instanceof DailyProduct)
                {
                    System.out.println((DailyProduct) i);
                } else if (i instanceof LongProduct)
                {
                    System.out.println((LongProduct) i);
                } else
                {
                    System.out.println(i + " N/A |");
                }
            }
            //footer
            System.out.println("|-------|-------------------------|----------------------------|----------------------------|----------|----------|------------|------------|----------|");

        }
    }

    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        try
        {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Check if the year, month, and day are valid
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Months are 0-based
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            if (year >= 1900 && year <= 9999 && month >= 1 && month <= 12)
            {
                // array so ngay trong 1 thang, rieng thang 2 se tuy thuoc vao leap year
                int[] daysInMonth =
                {
                    31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
                };
                return day >= 1 && day <= daysInMonth[month - 1];
            } else
            {
                return false; // Invalid year or month
            }
        } catch (ParseException e)
        {
            return false; // Invalid date format
        }
    }

    private static boolean isLeapYear(int year) {
        // A year is a leap year if it is evenly divisible by 4.
        //However, if a year is divisible by 100 but not divisible by 400, it is not a leap year.
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

//    public void initializeProducts() {
//    // Create and add some sample products to the list
//    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    
//    try {
//        Date date1 = sdf.parse("01/01/2023");
//        Date date2 = sdf.parse("15/02/2023");
//        Date date3 = sdf.parse("30/03/2023");
//        
//        Product product1 = new Product("1", "Product 1", date1, date2, 10, 100);
//        Product product2 = new Product("2", "Product 2", date2, date3, 15, 75);
//        Product product3 = new Product("3", "Product 3", date1, date3, 20, 50);
//        
//        products.add(product1);
//        products.add(product2);
//        products.add(product3);
//        
//        System.out.println("Sample products added to the list.");
//    } catch (ParseException e) {
//        System.out.println("Error initializing sample products: " + e.getMessage());
//    }
//}
}
