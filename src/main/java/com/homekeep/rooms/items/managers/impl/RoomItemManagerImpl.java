package com.homekeep.rooms.items.managers.impl;

import com.homekeep.rooms.items.dtos.RoomItemDto;
import com.homekeep.rooms.items.entities.RoomItemEntity;
import com.homekeep.rooms.items.managers.RoomItemManager;
import com.homekeep.rooms.items.managers.exceptions.ResourceNotFoundException;
import com.homekeep.rooms.items.services.RoomItemService;
import lombok.SneakyThrows;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomItemManagerImpl implements RoomItemManager {

    private final RoomItemService roomItemService;
    private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private final BoundMapperFacade<RoomItemEntity, RoomItemDto> roomMapper = mapperFactory.getMapperFacade(RoomItemEntity.class, RoomItemDto.class);

    public RoomItemManagerImpl(RoomItemService roomItemService) {
        this.roomItemService = roomItemService;
    }

    @Override
    public List<RoomItemDto> findAll(long roomId) {
        return this.roomItemService.findAll(roomId).stream()
                .map(roomMapper::map)
                .collect(Collectors.toList());

    }

    @SneakyThrows
    @Override
    public RoomItemDto findById(Long id) {
        return this.roomItemService.findById(id).map(this.roomMapper::map).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find resource for id: %d", id)));
    }

    @Override
    public RoomItemDto updateRoomItem(RoomItemDto roomItemDto) {
        return this.roomMapper.map(this.roomItemService.updateRoomItem(roomMapper.mapReverse(roomItemDto)));
    }

    @Override
    public boolean delete(Long id) {
        return this.roomItemService.delete(id);
    }

    @Override
    public RoomItemDto addRoomItem(RoomItemDto roomItemDto) {
        return this.roomMapper.map(this.roomItemService.addRoomItem(roomMapper.mapReverse(roomItemDto)));
    }
}
