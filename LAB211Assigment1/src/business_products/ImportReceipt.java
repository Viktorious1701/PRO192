/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_products;

import java.io.Serializable;
import static java.lang.System.currentTimeMillis;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Windows
 */
public class ImportReceipt implements Serializable {
    private String importCode;
    // currentTimeMillis is miliseconds after 1/1/1970
    private long timeOfCreation;
    private List<Product> productList;
    
    public ImportReceipt() {
        productList = new ArrayList<>();
    }

    public ImportReceipt(String receiptCode, long dateOfCreation, List<Product> productList) {
        this.importCode = receiptCode;
        this.timeOfCreation = dateOfCreation;
        this.productList = productList;
    }

    public String getImportCode() {
        return importCode;
    }

    public void setImportCode(String importCode) {
        // check validation
        this.importCode = importCode;
    }

    public long getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(long timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeOfCreation);

        String formattedDate = sdf.format(date);
        String result = String.format("|%-16s|%-22s",importCode,date);
        
        return result;
    }
    
}
