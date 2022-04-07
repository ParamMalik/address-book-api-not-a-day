package com.address.book.addressbookapi.controller;

import com.address.book.addressbookapi.service.impl.ExcelServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.jboss.logging.Logger.Level.INFO;
import static org.jboss.logging.Logger.Level.WARN;

@RestController
@RequestMapping(value = "/addressBook")
public class ExcelUploadDownloadController {
    @Autowired
    ExcelServiceImpl fileService;


    @ApiOperation("Download Address Book From Database To Excel File")
    @GetMapping(path = "/download")
    public ResponseEntity<Resource> downloadAddressBookToExcelFile() {
        String filename = "Address_Book.xlsx";

        InputStreamResource file = new InputStreamResource(fileService.loadDataFromDatabase());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }


    @ApiOperation("Upload Address Book From Excel To Database")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadAddressBookExcelFileToDatabase(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        Logger logger = LoggerFactory.logger(ExcelUploadDownloadController.class);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        fileService.uploadExcelDataToDatabase(multipartFile);

        stopWatch.stop();
        logger.log(INFO, "Total Time --->> ");
        logger.log(WARN, stopWatch.getTotalTimeSeconds());

        return ResponseEntity.ok("file is uploaded");

    }


}



