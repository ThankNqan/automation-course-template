package com.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtils {

    private static XSSFWorkbook work_book;
    private static XSSFSheet sheet;
    private static File file;
    private static FileInputStream stream;

    public ExcelUtils(String excelfilePath, String excelfileName) {
        try {
            String importFilePath = excelfilePath + excelfileName;
            // String fileNameWithOutExt = FileNameUtils.removeExtension(excelfileName);
            // String exportFilePath = excelfilePath + "Data" + "_Export.xlsx";

            file = new File(importFilePath);
            stream = new FileInputStream(file);
            work_book = new XSSFWorkbook(stream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getData(int sheetIndex, int row, int col) {
        sheet = work_book.getSheetAt(sheetIndex);
        Row xRow = sheet.getRow(row);
        if (xRow == null) {
            return "";
        }
        Cell xCell = xRow.getCell(col);
        if (xCell == null) {
            return "";
        }
        return xCell.toString();
    }

    public int getTotalRow(int sheetIndex) {
        int count = work_book.getSheetAt(sheetIndex).getLastRowNum();
        return count + 1;
    }

    public int getRowByTestcaseId(int sheetIndex, String id) {
        Boolean found = false;
        sheet = work_book.getSheetAt(sheetIndex);
        int totalRows = sheet.getLastRowNum();
        int foundRow = 0;
        for (int i = 1; i <= totalRows; i++) {
            Row row = sheet.getRow(i);
            String dataCell = row.getCell(0).getStringCellValue();
            if (dataCell.equalsIgnoreCase(id)) {
                foundRow = i;
                found = true;
                break;
            }

        }
        if (found) {
            return foundRow;
        } else
            return 0;

    }

    public void setCellData(String result, int sheetIndex, String testId, int colNum) {
        try {
            sheet = work_book.getSheetAt(sheetIndex);
            int rowNumber = getRowByTestcaseId(sheetIndex, testId);
            if (rowNumber == 0) {
                return;
            }
            Row xRow = sheet.getRow(rowNumber);
            Cell xCell = xRow.getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            if (xCell == null) {
                xCell = xRow.createCell(colNum);
                xCell.setCellValue(result);
            } else {
                xCell.setCellValue(result);
            }

            stream.close(); // Close input stream

            FileOutputStream outputStream = new FileOutputStream(file);
            work_book.write(outputStream);

            outputStream.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
