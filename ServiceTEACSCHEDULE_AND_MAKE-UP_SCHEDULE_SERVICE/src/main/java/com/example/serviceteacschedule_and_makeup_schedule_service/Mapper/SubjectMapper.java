package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.SubjectRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.SubjectResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Subject;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.SubjectUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    Subject toSubject(SubjectRequest request);
    SubjectResponse toSubjectResponse(Subject Subject);
    void updateSubject(@MappingTarget Subject Subject, SubjectUpdate update);
}
