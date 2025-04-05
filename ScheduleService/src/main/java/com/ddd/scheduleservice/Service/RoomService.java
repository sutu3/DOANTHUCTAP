package com.ddd.scheduleservice.Service;

import com.ddd.scheduleservice.Dto.Request.RoomRequest;
import com.ddd.scheduleservice.Dto.Response.RoomResponse;
import com.ddd.scheduleservice.Entity.Room;
import com.ddd.scheduleservice.Exception.AppException;
import com.ddd.scheduleservice.Exception.ErrorCode;
import com.ddd.scheduleservice.Form.RoomUpdate;
import com.ddd.scheduleservice.Mapper.RoomMapper;
import com.ddd.scheduleservice.Repo.RoomRepo;
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
public class RoomService {
    RoomMapper roomMapper;

    RoomRepo roomRepo;

    public List<RoomResponse> getAllRooms() {
        return roomRepo.findAll().stream()
                .map(roomMapper::toRoomResponse)
                .collect(Collectors.toList());
    }


    public RoomResponse getRoomById(int id) {
        return roomMapper.toRoomResponse(roomRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.PHONGMAY_NOT_FOUND)));
    }

    public RoomResponse createRoom(RoomRequest request) {
        Room room=roomMapper.toRoom(request);
        return roomMapper.toRoomResponse(roomRepo.save(room));
    }

    public RoomResponse updateRoom(int id, RoomUpdate roomDetails) {
        Room room=roomRepo.findById(id).orElseThrow(()->
                new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        roomMapper.updateRoom(room,roomDetails);
        return roomMapper.toRoomResponse(roomRepo.save(room));
    }


    public void deleteRoom(int id) {
        roomRepo.findById(id).orElseThrow(()->
                        new AppException(ErrorCode.PHONGMAY_NOT_FOUND));
        roomRepo.deleteById(id);
    }
}