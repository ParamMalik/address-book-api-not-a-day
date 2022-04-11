package com.address.book.addressbookapi.service.impl;

import com.address.book.addressbookapi.repo.ContactRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookInternalServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @Autowired
    private AddressBookInternalServiceImpl addressBookInternalService;




    @Test
    void saveContactInAddressBook() {
    }

    @Test
    void getListOfContactsFromAddressBook() {
    }

    @Test
    void findContactFromAddressBookByFirstName() {
    }

    @Test
    void deleteContactFromAddressBook() {
    }
}