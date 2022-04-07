package com.address.book.addressbookapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface ExcelService {
    ByteArrayInputStream loadDataFromDatabase();
    void uploadExcelToDatabase(MultipartFile multipartFile) throws IOException;
}
