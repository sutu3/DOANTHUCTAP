package com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T>{
    boolean success=false;
    int code=1000;
    String message;
    T Result;
}
