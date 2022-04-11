package com.address.book.addressbookapi.repo;

import com.address.book.addressbookapi.entity.ContactEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setContactId(1L);
        contactEntity.setFirstName("Rahul");
        contactEntity.setLastName("Singh");
        contactEntity.setIsActive("Yes");
        contactEntity.setEmailAddress("rahul@gamil.com");

        contactRepository.save(contactEntity);


    }

    @AfterEach
    void tearDown() {
        contactRepository.deleteAll();
    }

    @Test
    void shouldGiveArrayListWhenFindByFirstName() {
        // Given
        String firstName = "Rahul";

        // When
        List<ContactEntity> contactWithTheFirstName = contactRepository.findByFirstName(firstName);

        // Then
        assertThat(contactWithTheFirstName.get(0).getFirstName()).isEqualTo("Rahul");

    }

    @Test
    void shouldGiveSingleContactWhenFindByContactId() {
        // Given
        Long contactId = 1L;

        // When
        ContactEntity contactFindById = contactRepository.findByContactId(contactId);

        // Then
        assertThat(contactFindById.getContactId()).isEqualTo(contactId);


    }
}