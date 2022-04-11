package com.address.book.addressbookapi.service;

import com.address.book.addressbookapi.dto.ContactDTO;

import java.util.List;

public interface AddressBookInternalService {

    // To add a new Contact
    ContactDTO saveContactInAddressBook(ContactDTO contactDTO);

    // To get List Of all contacts
    List<ContactDTO> getListOfContactsFromAddressBook();

    // To find contact by firstName
    List<ContactDTO> findContactFromAddressBookByFirstName(String firstName);

    // To delete address (Not hard delete)
    void deleteContactFromAddressBook(Long customerId);
}
