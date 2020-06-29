package com.homekeep.rooms.items.managers;

import com.homekeep.rooms.items.dtos.RoomItemDto;

import java.util.List;

public interface RoomItemManager {
    List<RoomItemDto> findAll(long roomId);

    RoomItemDto findById(Long id);

    RoomItemDto updateRoomItem(RoomItemDto roomItemDto);

    boolean delete(Long id);

    RoomItemDto addRoomItem(RoomItemDto roomItemDto);
}
