package com.ddd.scheduleservice.Service;

import com.ddd.scheduleservice.Dto.Request.SubjectRequest;
import com.ddd.scheduleservice.Dto.Request.TokenRequest;
import com.ddd.scheduleservice.Dto.Response.SubjectResponse;
import com.ddd.scheduleservice.Entity.Subject;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import com.ddd.scheduleservice.Form.SubjectUpdate;
import com.ddd.scheduleservice.Mapper.SubjectMapper;
import com.ddd.scheduleservice.Repo.SubjectRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class SubjectService {
    SubjectMapper subjectMapper;

    SubjectRepo subjectRepo;
    private final AuthenService authenService;

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


    public SubjectResponse createSubject(SubjectRequest request,String token) {
        authenService.authenAdmin(TokenRequest.builder()
                .Token(token)
                .build());
        Subject subject=subjectMapper.toSubject(request);
        return subjectMapper.toSubjectResponse(subjectRepo.save(subject));
    }


    public SubjectResponse updateSubject(int id, SubjectUpdate update,String token) {
        authenService.authenAdmin(TokenRequest.builder()
                .Token(token)
                .build());
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