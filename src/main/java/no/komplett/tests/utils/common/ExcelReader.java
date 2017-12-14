package no.komplett.tests.utils.common;

import no.komplett.tests.utils.data.KomplettPlatform;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by a.dziashkevich on 6/25/15.
 */
public class ExcelReader {

    public static List<String> getDataSet(KomplettPlatform platform, String dataSet) {
        List<String> results = new ArrayList<>();
        FileInputStream file;
        try {
            file = new FileInputStream(new File(PropertiesReader.getProperty("test.data.file")));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(dataSet);
            int searchingColumn;
            for(searchingColumn = 0; searchingColumn <= sheet.getRow(0).getPhysicalNumberOfCells(); searchingColumn++) {
                if(sheet.getRow(0).getCell(searchingColumn).getStringCellValue().equals(platform.name())) {
                    break;
                }
            }
            if(searchingColumn!=sheet.getRow(0).getPhysicalNumberOfCells()) {
                Iterator<Row> ite = sheet.rowIterator();
                ite.next();
                while(ite.hasNext()){
                    Row row = ite.next();
                    Cell cell = row.getCell(searchingColumn);
                    if(cell != null)
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                results.add(cell.getStringCellValue());
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                DecimalFormat df = new DecimalFormat("#");
                                df.setMaximumFractionDigits(0);
                                double numericCellValue = cell.getNumericCellValue();
                                results.add(df.format(numericCellValue));
                                break;
                            default:
                                break;
                        }
                }
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static String getRandomValueFromDataSet(KomplettPlatform platform, String dataSet) {
        List<String> privateUsers = ExcelReader.getDataSet(platform, dataSet);
        return privateUsers.get(new Random().nextInt(privateUsers.size()));
    }
}
