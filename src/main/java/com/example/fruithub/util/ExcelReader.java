package com.example.fruithub.util;

import com.example.fruithub.entity.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
@Component
public class ExcelReader {

    public List<Product> readProductsFromExcel(String filePath){
        List<Product> products = new ArrayList<>();

        try(InputStream inputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(inputStream)){
            Sheet sheet = workbook.getSheetAt(0);

            for(int i =1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                if(row == null) continue;

                String name = row.getCell(0).getStringCellValue();
                double price = row.getCell(1).getNumericCellValue();
                int stockQuantity = (int) row.getCell(2).getNumericCellValue();

                Product product = Product.builder()
                        .name(name)
                        .price(price)
                        .stockQuantity(stockQuantity)
                        .build();

                products.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }
}
