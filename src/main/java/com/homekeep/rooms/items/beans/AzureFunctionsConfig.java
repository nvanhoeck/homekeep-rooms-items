package com.homekeep.rooms.items.beans;

import com.homekeep.rooms.items.controllers.RoomItemsController;
import com.homekeep.rooms.items.dtos.RoomItemDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class AzureFunctionsConfig {
    @Bean(name = "get-rooms-items")
    public Function<Long, List<RoomItemDto>> roomItems(RoomItemsController roomItemsController) {
        return roomItemsController::getRoomItems;
    }

    @Bean(name = "get-all-rooms-items")
    public Function<Long[], List<RoomItemDto>> getAllRoomsItems(RoomItemsController roomItemsController) {
        return roomItemsController::getAllRoomsItems;
    }

    @Bean(name = "add-room-item")
    Function<RoomItemDto, RoomItemDto> addRoomItem(RoomItemsController roomItemsController) {return roomItemsController::addRoomItem;}

    @Bean(name = "update-room-item")
    Function<RoomItemDto, RoomItemDto> updateRoomItem(RoomItemsController roomItemsController) {return roomItemsController::updateRoomItem;}

    @Bean(name = "delete-room-item")
    Function<Long, Boolean> deleteRoomItem(RoomItemsController roomItemsController) {return roomItemsController::deleteRoomItem;}
}
