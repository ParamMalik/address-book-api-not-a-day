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

    @Autowired
    private AddressBookCommonServiceImpl addressBookCommonService;


    // Get List Of All Contacts
    @ApiOperation("Get All Data From the database")
    @GetMapping("/search/{connectToExternalMachine}")
    @CrossOrigin(origins = "http://10.50.2.203:8080")
    public ResponseEntity<List<ContactDTO>> getAllContactsFromAddressBook(@NotNull @PathVariable(name = "connectToExternalMachine") Boolean isConnectingToExternalMachine) {

        return ResponseEntity.ok(addressBookCommonService.getAllAddressBook(isConnectingToExternalMachine));

    }


    // Get List Of Contacts by firstName
    @ApiOperation("Get Data On the basis of Name")
    @GetMapping("/search/{firstName}/{connectToExternalMachine}")
    public ResponseEntity<List<ContactDTO>> getContactsByFirstName(@NotNull @PathVariable("firstName") String firstName,@NotNull  @PathVariable(name = "connectToExternalMachine") Boolean isConnectingToExternalMachine) {

        return ResponseEntity.ok(addressBookCommonService.getAddressByFirstName(firstName, isConnectingToExternalMachine));

    }

    // Save New Contact To address book
    @ApiOperation("Save New Contact In the database")
    @PostMapping(path = "/save/{connectToExternalMachine}")
    public ResponseEntity<ContactDTO> saveNewContactToAddressBook(@Valid @RequestBody ContactDTO contactDTO,@NotNull @PathVariable(name = "connectToExternalMachine") Boolean isConnectingToExternalMachine) {

        return new ResponseEntity<>(addressBookCommonService.saveAddress(contactDTO, isConnectingToExternalMachine), HttpStatus.OK);

    }

    // Partial Delete Contact From Address Book
    @ApiOperation("Delete On the basis of Contact ID")
    @PutMapping(path = "/update/{contactId}/{connectToExternalMachine}")
    public void deleteContactFromAddressBook(@NotNull @PathVariable Long contactId,@NotNull  @PathVariable(name = "connectToExternalMachine") Boolean isConnectingToExternalMachine) {

        addressBookCommonService.updateAddressBook(contactId, isConnectingToExternalMachine);


    }


}
