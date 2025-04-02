package com.example.serviceteacschedule_and_makeup_schedule_service.Mapper;

import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Request.RoomRequest;
import com.example.serviceteacschedule_and_makeup_schedule_service.Dto.Response.RoomResponse;
import com.example.serviceteacschedule_and_makeup_schedule_service.Entity.Room;
import com.example.serviceteacschedule_and_makeup_schedule_service.Form.RoomUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    Room toRoom(RoomRequest request);
    RoomResponse toRoomResponse(Room Room);
    void updateRoom(@MappingTarget Room Room, RoomUpdate update);
}
