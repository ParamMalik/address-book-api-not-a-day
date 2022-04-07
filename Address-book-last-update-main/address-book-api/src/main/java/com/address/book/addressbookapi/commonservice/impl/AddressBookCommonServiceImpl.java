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
    public List<ContactDTO> getAllAddressBook(boolean isConnectingToExternalMachine) {
        if (isConnectingToExternalMachine) {
            return List.of(externalAddressBookService.getContactList());
        } else {
            return addressBookInternalService.getListOfAddress();
        }
    }

    @Override
    public List<ContactDTO> getAddressByFirstName(String firstName, boolean isConnectingToExternalMachine) {
        if (isConnectingToExternalMachine) {
            return List.of(externalAddressBookService.getContactListByFirstName(firstName));
        } else {
            return addressBookInternalService.findAddressByFirstName(firstName);
        }


    }

    @Override
    public ContactDTO saveAddress(ContactDTO contactDTO, boolean isConnectingToExternalMachine) {
        if (isConnectingToExternalMachine) {
            return externalAddressBookService.saveContact(contactDTO);
        } else {
            return addressBookInternalService.saveAddress(contactDTO);
        }

    }

    @Override
    public void updateAddressBook(Long contactId, boolean isConnectingToExternalMachine) {
        if (isConnectingToExternalMachine) {
            externalAddressBookService.deleteContact(contactId);
        } else {
            addressBookInternalService.deleteContact(contactId);
        }

    }


}
