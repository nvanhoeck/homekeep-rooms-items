package com.homekeep.rooms.items;

import com.homekeep.rooms.items.dtos.RoomItemDto;
import com.homekeep.rooms.items.entities.RoomItemEntity;

public class SampleDataUtil {
    public static RoomItemEntity buildRoomEntity(String name) {
        RoomItemEntity roomItemEntity = new RoomItemEntity();
        roomItemEntity.setName(name);
        roomItemEntity.setRoomId(1L);
        return roomItemEntity;
    }

    public static RoomItemEntity buildRoomEntity(Long id, String name) {
        RoomItemEntity roomItemEntity = new RoomItemEntity();
        roomItemEntity.setId(id);
        roomItemEntity.setName(name);
        roomItemEntity.setRoomId(1L);
        return roomItemEntity;
    }

    public static RoomItemDto buildRoomItemDto(Long id, String name) {
        RoomItemDto roomItemDto = new RoomItemDto();
        roomItemDto.setId(id);
        roomItemDto.setName(name);
        roomItemDto.setRoomId(1L);
        return roomItemDto;
    }

    public static RoomItemDto buildRoomItemDto(String name) {
        RoomItemDto roomItemDto = new RoomItemDto();
        roomItemDto.setName(name);
        roomItemDto.setRoomId(1L);
        return roomItemDto;
    }
}
