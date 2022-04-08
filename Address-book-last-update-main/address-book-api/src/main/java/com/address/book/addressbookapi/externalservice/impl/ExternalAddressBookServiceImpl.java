package com.address.book.addressbookapi.externalservice.impl;

import com.address.book.addressbookapi.dto.ContactDTO;
import com.address.book.addressbookapi.externalservice.ExternalAddressBookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class ExternalAddressBookServiceImpl implements ExternalAddressBookService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    RestTemplate restTemplate;

    @Value("${externalAddressBook.uri}")
    String uri;


    // Search
    @Override
    @SneakyThrows
    public ContactDTO[] getContactList() {
        log.info("Executing getContactList() method of ExternalAddressBookServiceImpl class");
        String addressList = restTemplate.getForObject(uri + "/getAllContact/false", String.class);
        return objectMapper.readValue(addressList, ContactDTO[].class);
    }

    // Update
    @Override
    public void deleteContact(Long id) {
        log.info("Executing deleteContact() method of ExternalAddressBookServiceImpl class");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        restTemplate.exchange(uri + "/partialUpdateContact/" + id + "/false", HttpMethod.PUT, entity, String.class);
    }

    // Find By First Name
    @Override
    @SneakyThrows
    public ContactDTO[] getContactListByFirstName(String firstName) {
        log.info("Executing getContactListByFirstName() method of ExternalAddressBookServiceImpl class");
        String addressString = restTemplate.getForObject(uri + "/getContactByName/" + firstName + "/false", String.class);
        return objectMapper.readValue(addressString, ContactDTO[].class);

    }

    // Save
    @Override
    public ContactDTO saveContact(ContactDTO contact) {
        log.info("Executing saveContact() method of ExternalAddressBookServiceImpl class");

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String jsonString;

        ContactDTO contactDTO = null;
        try {
            jsonString = objectMapper.writeValueAsString(contact);
            HttpEntity<String> stringHttpEntity = new HttpEntity<>(jsonString, httpHeaders);
            String responseEntity = restTemplate.postForObject(uri + "/saveContact/false", stringHttpEntity, String.class);
            contactDTO = objectMapper.readValue(responseEntity, ContactDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return contactDTO;
    }

}
