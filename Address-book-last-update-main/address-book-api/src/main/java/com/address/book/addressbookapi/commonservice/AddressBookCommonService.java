package com.address.book.addressbookapi.commonservice;

import com.address.book.addressbookapi.dto.ContactDTO;

import java.util.List;

public interface AddressBookCommonService {
    List<ContactDTO> getAllAddressBook(Boolean isConnectingToExternalMachine);

    List<ContactDTO> getAddressByFirstName(String firstName, Boolean isConnectingToExternalMachine);

    ContactDTO saveAddress(ContactDTO contactDTO, Boolean isConnectingToExternalMachine);

    void updateAddressBook(Long contactId, Boolean isConnectingToExternalMachine);

}
