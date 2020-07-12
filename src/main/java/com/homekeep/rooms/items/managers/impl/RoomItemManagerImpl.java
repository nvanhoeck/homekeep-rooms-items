package com.homekeep.rooms.items.managers.impl;

import com.homekeep.rooms.items.dtos.RoomItemDto;
import com.homekeep.rooms.items.entities.RoomItemColor;
import com.homekeep.rooms.items.entities.RoomItemEntity;
import com.homekeep.rooms.items.managers.RoomItemManager;
import com.homekeep.rooms.items.managers.exceptions.ResourceNotFoundException;
import com.homekeep.rooms.items.services.RoomItemService;
import lombok.SneakyThrows;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoomItemManagerImpl implements RoomItemManager {

    private final RoomItemService roomItemService;
    private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private final BoundMapperFacade<RoomItemEntity, RoomItemDto> roomItemMapper = mapperFactory.getMapperFacade(RoomItemEntity.class, RoomItemDto.class);

    public RoomItemManagerImpl(RoomItemService roomItemService) {
        this.roomItemService = roomItemService;
        mapperFactory.classMap(RoomItemEntity.class, RoomItemDto.class)
                .customize(new CustomMapper<RoomItemEntity, RoomItemDto>() {
                    @Override
                    public void mapAtoB(RoomItemEntity roomItemEntity, RoomItemDto roomItemDto, MappingContext context) {
                        mapperFacade.map(roomItemEntity, roomItemDto, context);
                        mapperFacade.mapAsArray(roomItemDto.getAlternatives(), roomItemEntity.getAlternatives(), Long.class);
                        mapperFacade.mapAsArray(roomItemDto.getColors(), roomItemEntity.getColors(), RoomItemColor.class);
                    }

                    @Override
                    public void mapBtoA(RoomItemDto roomItemDto, RoomItemEntity roomItemEntity, MappingContext context) {
                        mapperFacade.map(roomItemDto, roomItemEntity, context);
                        if(Objects.isNull(roomItemDto.getAlternatives())) {
                            mapperFacade.mapAsSet(new Long[]{}, Long.class);
                        } else {
                        mapperFacade.mapAsSet(roomItemDto.getAlternatives(), Long.class);
                        }
                        if(Objects.isNull(roomItemDto.getColors())) {
                            mapperFacade.mapAsSet(new RoomItemColor[]{}, RoomItemColor.class);
                        } else {
                            mapperFacade.mapAsSet(roomItemDto.getColors(), RoomItemColor.class);
                        }
                    }
                }).
                byDefault()
                .register();
    }

    @Override
    public List<RoomItemDto> findAll(long roomId) {
        return this.roomItemService.findAll(roomId).stream()
                .map(roomItemMapper::map)
                .collect(Collectors.toList());

    }

    //TODO tests
    @Override
    public List<RoomItemDto> findAll(List<Long> roomIds) {
        return roomIds.stream()
                .map(this.roomItemService::findAll)
                .reduce((roomItemEntities, roomItemEntitiesNext) -> {
                    roomItemEntities.addAll(roomItemEntitiesNext);
                    return roomItemEntities;
                })
                .map(roomItemEntities -> roomItemEntities.stream().map(roomItemMapper::map).collect(Collectors.toList()))
                .orElse(Collections.emptyList());

    }

    @SneakyThrows
    @Override
    public RoomItemDto findById(Long id) {
        return this.roomItemService.findById(id).map(this.roomItemMapper::map).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find resource for id: %d", id)));
    }

    @Override
    public RoomItemDto updateRoomItem(RoomItemDto roomItemDto) {
        return this.roomItemMapper.map(this.roomItemService.updateRoomItem(roomItemMapper.mapReverse(roomItemDto)));
    }

    @Override
    public boolean delete(Long id) {
        return this.roomItemService.delete(id);
    }

    @Override
    public RoomItemDto addRoomItem(RoomItemDto roomItemDto) {
        return this.roomItemMapper.map(this.roomItemService.addRoomItem(roomItemMapper.mapReverse(roomItemDto)));
    }
}
