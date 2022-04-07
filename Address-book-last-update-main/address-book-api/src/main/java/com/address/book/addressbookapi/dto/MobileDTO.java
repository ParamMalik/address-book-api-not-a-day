package com.address.book.addressbookapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MobileDTO {
    private Long mobileId;

    private String mobileNumber;

    private String countryCode;

    private String type;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

}
