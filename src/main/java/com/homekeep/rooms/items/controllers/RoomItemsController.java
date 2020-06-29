package com.homekeep.rooms.items.controllers;

import com.homekeep.rooms.items.dtos.RoomItemDto;
import com.homekeep.rooms.items.managers.RoomItemManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class RoomItemsController {

    private final RoomItemManager roomItemManager;


    public RoomItemsController(RoomItemManager roomItemManager) {
        this.roomItemManager = roomItemManager;
    }

    public List<RoomItemDto> getRoomItems(Long roomId) {
        return this.roomItemManager.findAll(roomId);
    }

    public RoomItemDto getRoomItem(@PathVariable("id") Long id) {
        return this.roomItemManager.findById(id);
    }

    public RoomItemDto addRoomItem(RoomItemDto roomItemDto) {
        return this.roomItemManager.addRoomItem(roomItemDto);
    }

    public RoomItemDto updateRoomItem(RoomItemDto roomItemDto) {
        return this.roomItemManager.updateRoomItem(roomItemDto);
    }

    public Boolean deleteRoomItem(Long id) {
        return this.roomItemManager.delete(id);
    }
}
