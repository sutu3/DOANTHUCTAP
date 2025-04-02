package com.example.serviceteacschedule_and_makeup_schedule_service.Service;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.SubjectRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.SubjectResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Subject;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.AppException;
import com.example.serviceteacschedule_and_makeup_schedule_service.Exception.ErrorCode;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.SubjectUpdate;
import com.example.serviceteacschedule_and_makeup_schedule_service.Mapper.SubjectMapper;
import com.example.serviceteacschedule_and_makeup_schedule_service.Repo.SubjectRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class SubjectService {
    private final SubjectMapper subjectMapper;

    SubjectRepo subjectRepo;

    public List<SubjectResponse> getAllSubjects() {
        return subjectRepo.findAll().stream()
                .map(subjectMapper::toSubjectResponse)
                .collect(Collectors.toList());
    }


    public SubjectResponse getSubjectById(int id) {
        return subjectMapper.toSubjectResponse(
                subjectRepo.findById(id).orElseThrow(()->
                        new AppException(ErrorCode.MONHOC_NOT_FOUND)));
    }


    public SubjectResponse createSubject(SubjectRequest request) {
        Subject subject=subjectMapper.toSubject(request);
        return subjectMapper.toSubjectResponse(subjectRepo.save(subject));
    }


    public SubjectResponse updateSubject(int id, SubjectUpdate update) {
        Subject subject= subjectRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.MONHOC_NOT_FOUND));
        subjectMapper.updateSubject(subject, update);
        return subjectMapper.toSubjectResponse(subjectRepo.save(subject));
    }

  
    public void deleteSubject(int id) {
        subjectRepo.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.MONHOC_NOT_FOUND));
        subjectRepo.deleteById(id);
    }
}