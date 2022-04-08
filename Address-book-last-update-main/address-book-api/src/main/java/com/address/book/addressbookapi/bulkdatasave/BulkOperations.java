package com.address.book.addressbookapi.bulkdatasave;

import com.address.book.addressbookapi.entity.ContactEntity;

import java.util.List;

public interface BulkOperations {
    void bulkPersist(List<ContactEntity> entityList);
}
