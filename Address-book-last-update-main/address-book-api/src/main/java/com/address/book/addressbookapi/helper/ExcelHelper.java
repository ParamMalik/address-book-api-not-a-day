package com.address.book.addressbookapi.helper;

import com.address.book.addressbookapi.entity.ContactEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.util.StopWatch;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.jboss.logging.Logger.Level.INFO;
import static org.jboss.logging.Logger.Level.WARN;


public final class ExcelHelper {
    private ExcelHelper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    static String[] headers = {"ContactId", "FirstName", "LastName", "Email", "isActive", "createdBy", "createdDate", "updatedBy", "updatedDate"};
    static String sheetOne = "Address_Book";

    public static ByteArrayInputStream contactsToExcel(List<ContactEntity> contacts) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet(sheetOne);

            CellStyle styleOne = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            styleOne.setFont(font);


            // Header
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < headers.length; col++) {

                Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers[col]);
                headerRow.setRowStyle(styleOne);
            }


            int rowIdx = 1;
            for (ContactEntity contact : contacts) {
                Row row = sheet.createRow(rowIdx++);

                CellStyle style = workbook.createCellStyle();

                if (contact.getContactId() % 2 == 0) {

                    // Setting Background color
                    style.setFillBackgroundColor(IndexedColors.SEA_GREEN.getIndex());
                    style.setFillPattern(FillPatternType.BIG_SPOTS);
                } else {
                    style.setFillBackgroundColor(IndexedColors.ROSE.getIndex());
                    style.setFillPattern(FillPatternType.DIAMONDS);
                }


                Cell cell1 = row.createCell(0);
                cell1.setCellValue(contact.getContactId());
                cell1.setCellStyle(style);

                Cell cell2 = row.createCell(1);
                cell2.setCellValue(contact.getFirstName());
                cell2.setCellStyle(style);

                Cell cell3 = row.createCell(2);
                cell3.setCellValue(contact.getLastName());
                cell3.setCellStyle(style);

                Cell cell4 = row.createCell(3);
                cell4.setCellValue(contact.getEmailAddress());
                cell4.setCellStyle(style);

                Cell cell5 = row.createCell(4);
                cell5.setCellValue(contact.getIsActive());
                cell5.setCellStyle(style);

                Cell cell6 = row.createCell(5);
                cell6.setCellValue(contact.getCreatedBy());
                cell6.setCellStyle(style);

                // This is date cell
                Cell cell = row.createCell(6);
                cell.setCellStyle(style);

                Cell cell7 = row.createCell(7);
                cell7.setCellValue(contact.getUpdatedBy());
                cell7.setCellStyle(style);

                // This is date cell
                Cell cellOne = row.createCell(8);
                cellOne.setCellStyle(style);




            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new IllegalArgumentException("fail to import data to Excel file: " + e.getMessage());
        }
    }


    // convert excel to list of contacts

    public static List<ContactEntity> convertExcelToListOfProduct(InputStream file) {
        Logger logger = LoggerFactory.logger(ExcelHelper.class);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ArrayList<ContactEntity> contactEntities = new ArrayList<>();


        try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {


            XSSFSheet sheet = workbook.getSheet("Address_Book");

            int rows = 0;

            for (Row row : sheet) {
                if (rows == 0) {
                    rows++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                ContactEntity contact = new ContactEntity();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0:
                            contact.setContactId((long) cell.getNumericCellValue());
                            break;
                        case 1:
                            contact.setFirstName(cell.getStringCellValue());
                            break;

                        case 2:
                            contact.setLastName(cell.getStringCellValue());
                            break;
                        case 3:
                            contact.setEmailAddress(cell.getStringCellValue());
                            break;
                        case 4:
                            contact.setIsActive(cell.getStringCellValue());
                            break;
                        case 5:
                            contact.setCreatedBy(cell.getStringCellValue());
                            break;
                        case 7:
                            contact.setUpdatedBy(cell.getStringCellValue());
                            break;
                        default:
                            break;

                    }
                    cid++;
                }
                contactEntities.add(contact);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        logger.log(INFO, "Conversion Time ----->> ");
        logger.log(WARN,stopWatch.getTotalTimeSeconds());
        return contactEntities;
    }


}
