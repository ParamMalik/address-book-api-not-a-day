package com.address.book.addressbookapi.mapper;

import com.address.book.addressbookapi.dto.ContactDTO;
import com.address.book.addressbookapi.dto.MobileDTO;
import com.address.book.addressbookapi.entity.ContactEntity;
import com.address.book.addressbookapi.entity.MobileEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-07T15:39:51+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
public class DtoAndEntityMapperImpl implements DtoAndEntityMapper {

    @Override
    public ContactDTO entityToDto(ContactEntity contactEntity) {
        if ( contactEntity == null ) {
            return null;
        }

        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setContactId( contactEntity.getContactId() );
        contactDTO.setFirstName( contactEntity.getFirstName() );
        contactDTO.setLastName( contactEntity.getLastName() );
        contactDTO.setEmailAddress( contactEntity.getEmailAddress() );
        contactDTO.setCreatedBy( contactEntity.getCreatedBy() );
        contactDTO.setCreatedDate( contactEntity.getCreatedDate() );
        contactDTO.setUpdatedBy( contactEntity.getUpdatedBy() );
        contactDTO.setUpdatedDate( contactEntity.getUpdatedDate() );
        contactDTO.setIsActive( contactEntity.getIsActive() );
        contactDTO.setMobileEntities( mobileEntityListToMobileDTOList( contactEntity.getMobileEntities() ) );

        return contactDTO;
    }

    @Override
    public ContactEntity dtoToEntity(ContactDTO contactDTO) {
        if ( contactDTO == null ) {
            return null;
        }

        ContactEntity contactEntity = new ContactEntity();

        contactEntity.setContactId( contactDTO.getContactId() );
        contactEntity.setFirstName( contactDTO.getFirstName() );
        contactEntity.setLastName( contactDTO.getLastName() );
        contactEntity.setEmailAddress( contactDTO.getEmailAddress() );
        contactEntity.setCreatedBy( contactDTO.getCreatedBy() );
        contactEntity.setCreatedDate( contactDTO.getCreatedDate() );
        contactEntity.setUpdatedBy( contactDTO.getUpdatedBy() );
        contactEntity.setUpdatedDate( contactDTO.getUpdatedDate() );
        contactEntity.setIsActive( contactDTO.getIsActive() );
        contactEntity.setMobileEntities( mobileDTOListToMobileEntityList( contactDTO.getMobileEntities() ) );

        return contactEntity;
    }

    @Override
    public List<ContactDTO> contactEntityListToDto(List<ContactEntity> contactEntityList) {
        if ( contactEntityList == null ) {
            return null;
        }

        List<ContactDTO> list = new ArrayList<ContactDTO>( contactEntityList.size() );
        for ( ContactEntity contactEntity : contactEntityList ) {
            list.add( entityToDto( contactEntity ) );
        }

        return list;
    }

    @Override
    public List<ContactEntity> dtoListTOEntityList(List<ContactDTO> contactDTOS) {
        if ( contactDTOS == null ) {
            return null;
        }

        List<ContactEntity> list = new ArrayList<ContactEntity>( contactDTOS.size() );
        for ( ContactDTO contactDTO : contactDTOS ) {
            list.add( dtoToEntity( contactDTO ) );
        }

        return list;
    }

    protected MobileDTO mobileEntityToMobileDTO(MobileEntity mobileEntity) {
        if ( mobileEntity == null ) {
            return null;
        }

        MobileDTO mobileDTO = new MobileDTO();

        mobileDTO.setMobileId( mobileEntity.getMobileId() );
        mobileDTO.setMobileNumber( mobileEntity.getMobileNumber() );
        mobileDTO.setCountryCode( mobileEntity.getCountryCode() );
        mobileDTO.setType( mobileEntity.getType() );
        mobileDTO.setCreatedBy( mobileEntity.getCreatedBy() );
        mobileDTO.setCreatedDate( mobileEntity.getCreatedDate() );
        mobileDTO.setUpdatedBy( mobileEntity.getUpdatedBy() );
        mobileDTO.setUpdatedDate( mobileEntity.getUpdatedDate() );

        return mobileDTO;
    }

    protected List<MobileDTO> mobileEntityListToMobileDTOList(List<MobileEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<MobileDTO> list1 = new ArrayList<MobileDTO>( list.size() );
        for ( MobileEntity mobileEntity : list ) {
            list1.add( mobileEntityToMobileDTO( mobileEntity ) );
        }

        return list1;
    }

    protected MobileEntity mobileDTOToMobileEntity(MobileDTO mobileDTO) {
        if ( mobileDTO == null ) {
            return null;
        }

        MobileEntity mobileEntity = new MobileEntity();

        mobileEntity.setMobileId( mobileDTO.getMobileId() );
        mobileEntity.setMobileNumber( mobileDTO.getMobileNumber() );
        mobileEntity.setCountryCode( mobileDTO.getCountryCode() );
        mobileEntity.setType( mobileDTO.getType() );
        mobileEntity.setCreatedBy( mobileDTO.getCreatedBy() );
        mobileEntity.setCreatedDate( mobileDTO.getCreatedDate() );
        mobileEntity.setUpdatedBy( mobileDTO.getUpdatedBy() );
        mobileEntity.setUpdatedDate( mobileDTO.getUpdatedDate() );

        return mobileEntity;
    }

    protected List<MobileEntity> mobileDTOListToMobileEntityList(List<MobileDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<MobileEntity> list1 = new ArrayList<MobileEntity>( list.size() );
        for ( MobileDTO mobileDTO : list ) {
            list1.add( mobileDTOToMobileEntity( mobileDTO ) );
        }

        return list1;
    }
}
