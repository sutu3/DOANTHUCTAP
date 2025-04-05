package com.ddd.scheduleservice.Mapper;

import com.ddd.scheduleservice.Dto.Request.SubjectRequest;
import com.ddd.scheduleservice.Dto.Response.SubjectResponse;
import com.ddd.scheduleservice.Entity.Subject;
import com.ddd.scheduleservice.Form.SubjectUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    Subject toSubject(SubjectRequest request);
    SubjectResponse toSubjectResponse(Subject Subject);
    void updateSubject(@MappingTarget Subject Subject, SubjectUpdate update);
}
