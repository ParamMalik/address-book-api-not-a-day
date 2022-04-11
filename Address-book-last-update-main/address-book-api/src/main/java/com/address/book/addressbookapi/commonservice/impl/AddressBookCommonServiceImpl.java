package com.address.book.addressbookapi.commonservice.impl;

import com.address.book.addressbookapi.commonservice.AddressBookCommonService;
import com.address.book.addressbookapi.dto.ContactDTO;
import com.address.book.addressbookapi.externalservice.impl.ExternalAddressBookServiceImpl;
import com.address.book.addressbookapi.service.impl.AddressBookInternalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookCommonServiceImpl implements AddressBookCommonService {

    @Autowired
    ExternalAddressBookServiceImpl externalAddressBookService;

    @Autowired
    AddressBookInternalServiceImpl addressBookInternalService;

    @Override
    public List<ContactDTO> getAllAddressBook(Boolean isConnectingToExternalMachine) {
        if (Boolean.TRUE.equals(isConnectingToExternalMachine)) {
            return List.of(externalAddressBookService.getContactList());
        } else {
            return addressBookInternalService.getListOfContactsFromAddressBook();
        }
    }

    @Override
    public List<ContactDTO> getAddressByFirstName(String firstName, Boolean isConnectingToExternalMachine) {
        if (Boolean.TRUE.equals(isConnectingToExternalMachine)) {
            return List.of(externalAddressBookService.getContactListByFirstName(firstName));
        } else {
            return addressBookInternalService.findContactFromAddressBookByFirstName(firstName);
        }


    }

    @Override
    public ContactDTO saveAddress(ContactDTO contactDTO, Boolean isConnectingToExternalMachine) {
        if (Boolean.TRUE.equals(isConnectingToExternalMachine)) {
            return externalAddressBookService.saveContact(contactDTO);
        } else {
            return addressBookInternalService.saveContactInAddressBook(contactDTO);
        }

    }

    @Override
    public void updateAddressBook(Long contactId, Boolean isConnectingToExternalMachine) {
        if (Boolean.TRUE.equals(isConnectingToExternalMachine)) {
            externalAddressBookService.deleteContact(contactId);
        } else {
            addressBookInternalService.deleteContactFromAddressBook(contactId);
        }

    }


}
