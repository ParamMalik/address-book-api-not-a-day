package com.address.book.addressbookapi.controller;

import com.address.book.addressbookapi.dto.ContactDTO;
import com.address.book.addressbookapi.externalservice.impl.ExternalAddressBookServiceImpl;
import com.address.book.addressbookapi.service.impl.AddressBookServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AddressBookController {

    @Autowired
    private AddressBookServiceImpl addressBookService;

    @Autowired
    private ExternalAddressBookServiceImpl externalAddressBookService;


    // Get List Of All Contacts


    @ApiOperation("Get All Data From the database")
    @GetMapping("/search/{isRemote}")
    @CrossOrigin(origins = "http://10.50.2.203:8080")
    public ResponseEntity<List<ContactDTO>> getAllAddressBook(@PathVariable(name = "isRemote") String isRemote) {
        if (isRemote.equals("y")) {
            return ResponseEntity.ok(List.of(externalAddressBookService.getContactList()));
        } else {
            return ResponseEntity.ok(addressBookService.getListOfAddress());
        }
    }

    // Get List Of Contacts by firstName

    @ApiOperation("Get Data On the basis of Name")
    @GetMapping("/search/{firstName}/{isRemote}")
    public ResponseEntity<List<ContactDTO>> getAddressByFirstName(@PathVariable("firstName") String firstName, @PathVariable(name = "isRemote") String isRemote) {
        if (isRemote.equals("y")) {
            return ResponseEntity.ok(List.of(externalAddressBookService.getContactListByFirstName(firstName)));
        } else {
            return ResponseEntity.ok(addressBookService.findAddressByFirstName(firstName));
        }


    }

    @ApiOperation("Save New Contact In the database")
    @PostMapping(path = "/save/{isRemote}")
    public ResponseEntity<ContactDTO> saveAddress(@Valid @RequestBody ContactDTO contactDTO, @PathVariable(name = "isRemote") String isRemote) {
        if (isRemote.equals("y")) {
            return new ResponseEntity<>(externalAddressBookService.saveContact(contactDTO), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(addressBookService.saveAddress(contactDTO), HttpStatus.OK);
        }


    }

    @ApiOperation("Delete On the basis of Contact ID")
    @PutMapping(path = "/update/{contactId}/{isRemote}")
    public ResponseEntity<String> updateAddressBook(@PathVariable Long contactId, @PathVariable(name = "isRemote") String isRemote) {
        if (isRemote.equals("y")) {
            externalAddressBookService.deleteContact(contactId);
        } else {
            addressBookService.deleteContact(contactId);
        }
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);


    }


}
