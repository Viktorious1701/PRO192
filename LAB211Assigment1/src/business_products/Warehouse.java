/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_products;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Windows
 */
public class Warehouse implements Serializable {
    private List<ImportReceipt> importReceiptList;
    private List<ExportReceipt> exportReceiptList;
    
    public Warehouse() {
       importReceiptList = new ArrayList<>();
       exportReceiptList = new ArrayList<>();
    }

    public Warehouse(List<ImportReceipt> ImportReceipt, List<ExportReceipt> ExportReceipt) {
        this.importReceiptList = ImportReceipt;
        this.exportReceiptList = ExportReceipt;
    }

    public List<ImportReceipt> getImportReceipt() {
        return importReceiptList;
    }

    public void setImportReceipt(List<ImportReceipt> ImportReceiptList) {
        this.importReceiptList = ImportReceiptList;
    }

    public List<ExportReceipt> getExportReceipt() {
        return exportReceiptList;
    }

    public void setExportReceipt(List<ExportReceipt> exportReceiptList) {
        this.exportReceiptList = exportReceiptList;
    }
    
}
