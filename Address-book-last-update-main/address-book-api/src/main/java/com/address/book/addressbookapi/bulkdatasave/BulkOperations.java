package com.address.book.addressbookapi.bulkdatasave;

import com.address.book.addressbookapi.entity.ContactEntity;

import java.util.List;

public interface BulkOperations {
    public void bulkPersist(List<ContactEntity> entityList);
}
