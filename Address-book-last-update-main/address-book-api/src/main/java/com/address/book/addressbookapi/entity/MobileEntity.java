package com.address.book.addressbookapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Mobile")
public class MobileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mobileId;

    @NotBlank(message = "Mobile Number Can not be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid Mobile Number")
    private String mobileNumber;

    private String countryCode;

    private String type;

    private String createdBy;

    @CreationTimestamp
    private Date createdDate;

    private String updatedBy;

    @UpdateTimestamp
    private Date updatedDate;

    private Long contactId;

}
