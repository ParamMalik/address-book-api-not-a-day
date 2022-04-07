package com.address.book.addressbookapi.repo;

import com.address.book.addressbookapi.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    List<ContactEntity> findByFirstName(String firstName);

    ContactEntity findByContactId(Long contactId);

}
