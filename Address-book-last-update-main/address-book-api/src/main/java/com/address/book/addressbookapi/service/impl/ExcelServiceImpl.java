package com.address.book.addressbookapi.service.impl;

import com.address.book.addressbookapi.bulkdatasave.JdbcTemplateBulkOperations;
import com.address.book.addressbookapi.controller.ExcelUploadDownloadController;
import com.address.book.addressbookapi.dto.ContactDTO;
import com.address.book.addressbookapi.entity.ContactEntity;
import com.address.book.addressbookapi.exception.customexception.EmptyDatabaseException;
import com.address.book.addressbookapi.helper.ExcelHelper;
import com.address.book.addressbookapi.mapper.DtoAndEntityMapper;
import com.address.book.addressbookapi.repo.ContactRepository;
import com.address.book.addressbookapi.service.ExcelService;
import com.poiji.bind.Poiji;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.jboss.logging.Logger.Level.INFO;
import static org.jboss.logging.Logger.Level.WARN;

@Slf4j
@Service

public class ExcelServiceImpl implements ExcelService {
    @Autowired
    ContactRepository contactRepository;

    @Autowired
    JdbcTemplateBulkOperations jdbcTemplateBulkOperations;


    @Override
    public ByteArrayInputStream loadDataFromDatabase() {
        log.info("Executing loadDataFromDatabase() method of ExcelServiceImpl class");
        List<ContactEntity> allContactsListFromAddressBook = contactRepository.findAll();
        if (allContactsListFromAddressBook.isEmpty()) {
            throw new EmptyDatabaseException();
        } else {
            return ExcelHelper.contactsToExcel(allContactsListFromAddressBook);
        }

    }

    @Override
    public void uploadExcelDataToDatabase(MultipartFile multipartFile) throws IOException {
        log.info("Executing uploadExcelDataToDatabase() method of ExcelServiceImpl class");
        List<ContactEntity> contactsListFromExcelSheet = ExcelHelper.convertExcelToListOfProduct(multipartFile.getInputStream());

        Logger logger = LoggerFactory.logger(ExcelUploadDownloadController.class);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        jdbcTemplateBulkOperations.bulkPersist(contactsListFromExcelSheet);
        stopWatch.stop();
        logger.log(INFO, "Saving Data Time ----->> ");
        logger.log(WARN, stopWatch.getTotalTimeSeconds());


    }

    @Override
    public void uploadExcelToDatabase(MultipartFile multipartFile) throws IOException {
        log.info("Executing uploadExcelDataToDatabase() method of ExcelServiceImpl class");
        InputStream inputStream = multipartFile.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet sheet = workbook.getSheet("Address_Book");
        List<ContactDTO> listOfAllContactsFromExcelSheet = Poiji.fromExcel(sheet, ContactDTO.class);
        List<ContactEntity> contactEntities = DtoAndEntityMapper.MAPPER.dtoListTOEntityList(listOfAllContactsFromExcelSheet);
        jdbcTemplateBulkOperations.bulkPersist(contactEntities);

    }


}
