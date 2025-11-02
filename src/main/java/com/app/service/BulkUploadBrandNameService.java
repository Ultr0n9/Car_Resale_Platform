package com.app.service;

import com.app.entity.cars.Brand;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BulkUploadBrandNameService {



        public List<Brand> readExcel(String filepath) throws IOException{
            List<Brand> list = new ArrayList<>();

            FileInputStream fis = new FileInputStream(new File(filepath));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next(); // skipping header row
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Brand brand = new Brand();
                brand.setId((long)row.getCell(0).getNumericCellValue());
                brand.setName(row.getCell(1).getStringCellValue());
                list.add(brand);
            }

            workbook.close();
            fis.close();
            return list;

        }


}
