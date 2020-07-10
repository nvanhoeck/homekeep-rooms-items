package com.homekeep.rooms.items;

import com.homekeep.rooms.items.dtos.RoomItemDto;
import com.homekeep.rooms.items.entities.RoomItemColor;
import com.homekeep.rooms.items.entities.RoomItemEntity;

import java.util.TreeSet;

public class SampleDataUtil {
    public static RoomItemEntity buildRoomEntity(String name) {
        RoomItemEntity roomItemEntity = new RoomItemEntity();
        roomItemEntity.setName(name);
        roomItemEntity.setRoomId(1L);
        roomItemEntity.setAlternatives(new TreeSet<>());
        roomItemEntity.setColors(new TreeSet<>());
        return roomItemEntity;
    }

    public static RoomItemEntity buildRoomEntity(Long id, String name) {
        RoomItemEntity roomItemEntity = new RoomItemEntity();
        roomItemEntity.setId(id);
        roomItemEntity.setName(name);
        roomItemEntity.setRoomId(1L);
        roomItemEntity.setAlternatives(new TreeSet<>());
        roomItemEntity.setColors(new TreeSet<>());
        return roomItemEntity;
    }

    public static RoomItemDto buildRoomItemDto(Long id, String name) {
        RoomItemDto roomItemDto = new RoomItemDto();
        roomItemDto.setId(id);
        roomItemDto.setName(name);
        roomItemDto.setRoomId(1L);
        roomItemDto.setAlternatives(new Long[]{});
        roomItemDto.setColors(new RoomItemColor[]{});
        return roomItemDto;
    }

    public static RoomItemDto buildRoomItemDto(String name) {
        RoomItemDto roomItemDto = new RoomItemDto();
        roomItemDto.setName(name);
        roomItemDto.setRoomId(1L);
        roomItemDto.setAlternatives(new Long[]{});
        roomItemDto.setColors(new RoomItemColor[]{});
        return roomItemDto;
    }

    public static RoomItemColor buildColor(String color) {
        RoomItemColor roomItemColor = new RoomItemColor();
        roomItemColor.setValue(color);
        return roomItemColor;
    }

    public static RoomItemEntity buildFullRoomItemEntity() {
        RoomItemEntity roomItemEntity = new RoomItemEntity();
        roomItemEntity.setAlternatives(new TreeSet<>());
        roomItemEntity.setColors(new TreeSet<>());
        roomItemEntity.setRoomId(1L);
        roomItemEntity.setName("Test");
        roomItemEntity.setAlternativeOf(1L);
        roomItemEntity.setAmountWanted("1");
        roomItemEntity.setAmountOwned("1");
        roomItemEntity.setCostPerItem("1.25");
        roomItemEntity.setTotalCost("1.25");
        roomItemEntity.setImage("img.jpg");
        roomItemEntity.setLocked(false);
        roomItemEntity.setSpendedCost("0.0");
        roomItemEntity.setUrlLink("www.google.be");
        return roomItemEntity;
    }

    public static RoomItemDto buildFullRoomItemDto() {
        RoomItemDto roomItemDto = new RoomItemDto();
        roomItemDto.setAlternatives(new Long[]{});
        roomItemDto.setColors(new RoomItemColor[]{});
        roomItemDto.setRoomId(1L);
        roomItemDto.setName("Test");
        roomItemDto.setAlternativeOf(1L);
        roomItemDto.setAmountWanted(1);
        roomItemDto.setAmountOwned(1);
        roomItemDto.setCostPerItem(1.25);
        roomItemDto.setTotalCost(1.25);
        roomItemDto.setImage("img.jpg");
        roomItemDto.setLocked(false);
        roomItemDto.setSpendedCost(0.0);
        roomItemDto.setUrlLink("www.google.be");
        return roomItemDto;
    }
}
