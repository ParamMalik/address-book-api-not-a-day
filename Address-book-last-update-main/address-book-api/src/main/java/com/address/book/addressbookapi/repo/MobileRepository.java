package com.address.book.addressbookapi.repo;

import com.address.book.addressbookapi.entity.MobileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileRepository extends JpaRepository<MobileEntity, Long> {
}
