package com.address.book.addressbookapi.service;

import com.address.book.addressbookapi.dto.ContactDTO;

import java.util.List;

public interface AddressBookInternalService {

    // To add a new Contact
    ContactDTO saveAddress(ContactDTO contactDTO);

    // To get List Of all contacts
    List<ContactDTO> getListOfAddress();

    // To find contact by firstName
    List<ContactDTO> findAddressByFirstName(String firstName);

    // To delete address (Not hard delete)
    void deleteContact(Long customerId);
}
