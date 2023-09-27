/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_products;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Windows
 */
public class ExportReceipt implements Serializable{
    private String exportCode;
    // currentTimeMillis is miliseconds after 1/1/1970
    private long timeOfCreation;
    private List<Product> productList;

    public ExportReceipt(String exportCode, long dateOfCreation, List<Product> productList) {
        this.exportCode = exportCode;
        this.timeOfCreation = dateOfCreation;
        this.productList = productList;
    }

    public ExportReceipt() 
    {   productList = new ArrayList<>();
    }

    public String getExportCode() {
        return exportCode;
    }

    public void setExportCode(String exportCode) {
        this.exportCode = exportCode;
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
        String result = String.format("|%-16s|%-22s",exportCode,date);
        
        return result;
    }
    
    
}
