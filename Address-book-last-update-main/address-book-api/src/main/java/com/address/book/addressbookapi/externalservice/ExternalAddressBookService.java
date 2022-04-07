package com.address.book.addressbookapi.externalservice;

import com.address.book.addressbookapi.dto.ContactDTO;
import com.fasterxml.jackson.core.JsonProcessingException;


public interface ExternalAddressBookService {
    // Return List of all Address
    ContactDTO[] getContactList() throws JsonProcessingException;

    // Delete Address(not Hard delete) by changing the status to False Or No
    void deleteContact(Long id);

    // Return List Of all contacts with same first name
    ContactDTO[] getContactListByFirstName(String firstName);

    // Add new Contact
    ContactDTO saveContact(ContactDTO contact);
}
