package com.learn;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExcelReader {

    public static void main(String[] args) throws IOException {
        String zipFilePath = "/Users/sanamudash/Downloads/test.zip";

        try (FileInputStream fis = new FileInputStream(zipFilePath);
             ZipInputStream zis = new ZipInputStream(fis)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().endsWith(".xlsx")) {
                    System.out.println("Reading: " + entry.getName());

                    // Copy zip stream to a byte array (workaround to support POI)
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        baos.write(buffer, 0, len);
                    }

                    try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(baos.toByteArray()))) {
                        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                            Sheet sheet = workbook.getSheetAt(i);
                            System.out.println("  Sheet: " + sheet.getSheetName());

                            for (Row row : sheet) {
                                for (Cell cell : row) {
                                    System.out.print(getCellValue(cell) + "\t");
                                }
                                System.out.println();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static String getCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            case BLANK -> "";
            default -> "UNKNOWN";
        };
    }
}
