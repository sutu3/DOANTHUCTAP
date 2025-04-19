package com.ddd.scheduleservice.Mapper;

import com.ddd.scheduleservice.Dto.Request.RoomRequest;
import com.ddd.scheduleservice.Dto.Response.NotificationResponse;
import com.ddd.scheduleservice.Dto.Response.RoomResponse;
import com.ddd.scheduleservice.Entity.Notification;
import com.ddd.scheduleservice.Entity.Room;
import com.ddd.scheduleservice.Form.RoomUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationResponse toNotificationResponse(Notification entity);
}
