package com.ddd.scheduleservice.Mapper;

import com.ddd.scheduleservice.Dto.Request.MakeupRequestDTORequest;
import com.ddd.scheduleservice.Dto.Response.MakeupRequestDTOResponse;
import com.ddd.scheduleservice.Entity.MakeupRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MakeupRequestMapper {
    @Mapping(target = "subject",ignore = true)
    @Mapping(target = "room",ignore = true)
    @Mapping(target = "user",ignore = true)
    MakeupRequest toMakeupRequestDTO(MakeupRequestDTORequest request);
    MakeupRequestDTOResponse toMakeupRequestDTOResponse(MakeupRequest MakeupRequestDTO);
/*    void updateMakeupRequestDTO(@MappingTarget MakeupRequestDTO MakeupRequestDTO, MakeupRequestDTOUpdate update);*/
}
