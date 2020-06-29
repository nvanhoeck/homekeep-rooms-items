package com.homekeep.rooms.items.services;

import com.homekeep.rooms.items.entities.RoomItemEntity;

import java.util.List;
import java.util.Optional;

public interface RoomItemService {
    List<RoomItemEntity> findAll(long l);

    Optional<RoomItemEntity> findById(Long id);

    RoomItemEntity updateRoomItem(RoomItemEntity roomItemEntity);

    boolean delete(Long id);

    RoomItemEntity addRoomItem(RoomItemEntity roomItemEntity);
}
