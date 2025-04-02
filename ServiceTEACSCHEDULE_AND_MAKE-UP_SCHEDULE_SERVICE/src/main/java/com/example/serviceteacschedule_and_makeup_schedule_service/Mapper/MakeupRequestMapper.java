package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.MakeupRequestDTORequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.MakeupRequestDTOResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.MakeupRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MakeupRequestMapper {
    MakeupRequest toMakeupRequestDTO(MakeupRequestDTORequest request);
    MakeupRequestDTOResponse toMakeupRequestDTOResponse(MakeupRequest MakeupRequestDTO);
/*    void updateMakeupRequestDTO(@MappingTarget MakeupRequestDTO MakeupRequestDTO, MakeupRequestDTOUpdate update);*/
}
