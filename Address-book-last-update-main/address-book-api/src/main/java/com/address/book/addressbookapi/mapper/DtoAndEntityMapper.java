package com.address.book.addressbookapi.mapper;

import com.address.book.addressbookapi.dto.ContactDTO;
import com.address.book.addressbookapi.entity.ContactEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DtoAndEntityMapper {

    DtoAndEntityMapper MAPPER = Mappers.getMapper(DtoAndEntityMapper.class);

    // Converting Entity Class Obj to DTO class Object

    //    @Mapping(target = "mobileDtos", source = "mobileEntities")
    ContactDTO entityToDto(ContactEntity contactEntity);

    // Converting DTO class Obj to Entity Class Object

    //    @Mapping(target = "mobileEntities", source = "mobileDtos")
    ContactEntity dtoToEntity(ContactDTO contactDTO);

    // Converting Entity List To DTO list
    List<ContactDTO> contactEntityListToDto(List<ContactEntity> contactEntityList);

    List<ContactEntity> dtoListTOEntityList(List<ContactDTO> contactDTOS);


}
