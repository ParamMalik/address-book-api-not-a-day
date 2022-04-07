package com.address.book.addressbookapi.commonservice;

import com.address.book.addressbookapi.dto.ContactDTO;

import java.util.List;

public interface AddressBookCommonService {
    List<ContactDTO> getAllAddressBook(boolean isConnectingToExternalMachine);

    List<ContactDTO> getAddressByFirstName(String firstName, boolean isConnectingToExternalMachine);

    ContactDTO saveAddress(ContactDTO contactDTO, boolean isConnectingToExternalMachine);

    void updateAddressBook(Long contactId, boolean isConnectingToExternalMachine);

}
