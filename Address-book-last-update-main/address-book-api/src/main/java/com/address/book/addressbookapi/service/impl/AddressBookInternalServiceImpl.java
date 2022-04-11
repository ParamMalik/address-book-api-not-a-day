package com.address.book.addressbookapi.service.impl;

import com.address.book.addressbookapi.dto.ContactDTO;
import com.address.book.addressbookapi.entity.ContactEntity;
import com.address.book.addressbookapi.exception.customexception.ContactIdNotPresentException;
import com.address.book.addressbookapi.exception.customexception.ContactNotFoundInDatabaseException;
import com.address.book.addressbookapi.exception.customexception.EmptyDatabaseException;
import com.address.book.addressbookapi.mapper.DtoAndEntityMapper;
import com.address.book.addressbookapi.repo.ContactRepository;
import com.address.book.addressbookapi.service.AddressBookInternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AddressBookInternalServiceImpl implements AddressBookInternalService {

    @Autowired
    ContactRepository contactRepository;

    // Save New
    @Override
    public ContactDTO saveContactInAddressBook(ContactDTO contactDTO) {
        log.info("executing saveAddress() Method in AddressBookInternalServiceImpl");
        ContactEntity save = contactRepository.save(DtoAndEntityMapper.MAPPER.dtoToEntity(contactDTO));
        return DtoAndEntityMapper.MAPPER.entityToDto(save);
    }


    // Get All Address
    @Override
    public List<ContactDTO> getListOfContactsFromAddressBook() {

        log.info("executing getListOfAddress() Method in AddressBookInternalServiceImpl");
        List<ContactEntity> allContacts = contactRepository.findAll();
        if (allContacts.isEmpty()) {
            throw new EmptyDatabaseException();
        }
        return DtoAndEntityMapper.MAPPER.contactEntityListToDto(allContacts);

    }

    // Get Address By firstName
    @Override
    public List<ContactDTO> findContactFromAddressBookByFirstName(String firstName) {

        log.info("executing findAddressByFirstName() Method in AddressBookInternalServiceImpl");
        List<ContactEntity> listOfContacts = contactRepository.findByFirstName(firstName);
        if (listOfContacts.isEmpty()) {
            throw new ContactNotFoundInDatabaseException();
        }

        return DtoAndEntityMapper.MAPPER.contactEntityListToDto(listOfContacts);


    }

    // Delete Address By changing the status
    @Override
    public void deleteContactFromAddressBook(Long customerId) {
        log.info("executing deleteContact() Method in AddressBookInternalServiceImpl");
        ContactEntity contactEntityById = contactRepository.findByContactId(customerId);
        if (contactEntityById == null) {
            throw new ContactIdNotPresentException();
        }
        contactEntityById.setIsActive("N");
        contactRepository.save(contactEntityById);

    }

}
