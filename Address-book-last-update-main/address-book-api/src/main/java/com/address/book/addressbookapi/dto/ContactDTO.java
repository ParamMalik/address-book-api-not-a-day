package com.address.book.addressbookapi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ContactDTO {

    private Long contactId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private String isActive;

    private List<MobileDTO> mobileEntities;


}
