package com.address.book.addressbookapi.service.impl;

import com.address.book.addressbookapi.dto.ContactDTO;
import com.address.book.addressbookapi.entity.ContactEntity;
import com.address.book.addressbookapi.exception.customexception.ContactIdNotPresentException;
import com.address.book.addressbookapi.exception.customexception.ContactNotFoundInDatabaseException;
import com.address.book.addressbookapi.exception.customexception.EmptyDatabaseException;
import com.address.book.addressbookapi.mapper.DtoAndEntityMapper;
import com.address.book.addressbookapi.repo.ContactRepository;
import com.address.book.addressbookapi.service.AddressBookInternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookInternalServiceImpl implements AddressBookInternalService {

    @Autowired
    private ContactRepository contactRepository;

    // Save New
    @Override
    public ContactDTO saveAddress(ContactDTO contactDTO) {
        ContactEntity save = contactRepository.save(DtoAndEntityMapper.MAPPER.dtoToEntity(contactDTO));
        return DtoAndEntityMapper.MAPPER.entityToDto(save);
    }


    // Get All Address
    @Override
    public List<ContactDTO> getListOfAddress() {

        List<ContactEntity> allContacts = contactRepository.findAll();
        if (allContacts.isEmpty()) {
            throw new EmptyDatabaseException();
        }
        return DtoAndEntityMapper.MAPPER.contactEntityListToDto(allContacts);

    }

    // Get Address By firstName
    @Override
    public List<ContactDTO> findAddressByFirstName(String firstName) {

        List<ContactEntity> byFirstName = contactRepository.findByFirstName(firstName);
        if (byFirstName.isEmpty()) {
            throw new ContactNotFoundInDatabaseException();
        }

        return DtoAndEntityMapper.MAPPER.contactEntityListToDto(byFirstName);


    }

    // Delete Address By changing the status
    @Override
    public void deleteContact(Long customerId) {
        ContactEntity byContactId = contactRepository.findByContactId(customerId);
        if (byContactId == null) {
            throw new ContactIdNotPresentException();
        }
        byContactId.setIsActive("N");
        contactRepository.save(byContactId);

    }

}
