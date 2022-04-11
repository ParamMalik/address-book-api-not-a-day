package com.address.book.addressbookapi.controller;

import com.address.book.addressbookapi.commonservice.impl.AddressBookCommonServiceImpl;
import com.address.book.addressbookapi.dto.ContactDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class AddressBookController {

    static final String GET_ALL_SWAGGER_DEFINITION = "Get All Data From the database";
    static final String GET_BY_FIRSTNAME_SWAGGER_DEFINITION = "Get Data On the basis of Name";
    static final String SAVE_NEW_CONTACT_SWAGGER_DEFINITION = "Save New Contact In the database";
    static final String DELETE_CONTACT_SWAGGER_DEFINITION = "Delete On the basis of Contact ID";


    @Autowired
    AddressBookCommonServiceImpl addressBookCommonService;


    // Get List Of All Contacts

    @ApiOperation(GET_ALL_SWAGGER_DEFINITION)
    @GetMapping("/search/{connectToExternalMachine}")
    @CrossOrigin(origins = "http://10.50.2.203:8080")
    public ResponseEntity<List<ContactDTO>> getAllContactsFromAddressBook(@NotNull @PathVariable(name = "connectToExternalMachine") Boolean isConnectingToExternalMachine) {

        return new ResponseEntity<>(addressBookCommonService.getAllAddressBook(isConnectingToExternalMachine), HttpStatus.OK);

    }


    // Get List Of Contacts by firstName

    @ApiOperation(GET_BY_FIRSTNAME_SWAGGER_DEFINITION)
    @GetMapping("/search/{firstName}/{connectToExternalMachine}")
    public ResponseEntity<List<ContactDTO>> getContactsByFirstName(@NotNull @PathVariable("firstName") String firstName, @NotNull @PathVariable(name = "connectToExternalMachine") Boolean isConnectingToExternalMachine) {
        return new ResponseEntity<>(addressBookCommonService.getAddressByFirstName(firstName, isConnectingToExternalMachine), HttpStatus.OK);

    }

    // Save New Contact To address book

    @ApiOperation(SAVE_NEW_CONTACT_SWAGGER_DEFINITION)
    @PostMapping(path = "/save/{connectToExternalMachine}")
    public ResponseEntity<ContactDTO> saveNewContactToAddressBook(@Valid @RequestBody ContactDTO contactDTO, @NotNull @PathVariable(name = "connectToExternalMachine") Boolean isConnectingToExternalMachine) {
        return new ResponseEntity<>(addressBookCommonService.saveAddress(contactDTO, isConnectingToExternalMachine), HttpStatus.OK);

    }

    // Partially Delete Contact From Address Book

    @ApiOperation(DELETE_CONTACT_SWAGGER_DEFINITION)
    @PutMapping(path = "/update/{contactId}/{connectToExternalMachine}")
    public void deleteContactFromAddressBook(@NotNull @PathVariable Long contactId, @NotNull @PathVariable(name = "connectToExternalMachine") Boolean isConnectingToExternalMachine) {
        addressBookCommonService.updateAddressBook(contactId, isConnectingToExternalMachine);


    }


}
