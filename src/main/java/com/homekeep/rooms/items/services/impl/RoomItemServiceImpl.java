package com.homekeep.rooms.items.services.impl;

import com.homekeep.rooms.items.entities.RoomItemEntity;
import com.homekeep.rooms.items.services.RoomItemService;
import com.homekeep.rooms.items.repositories.RoomItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomItemServiceImpl implements RoomItemService {

    private final RoomItemRepository roomItemRepository;

    public RoomItemServiceImpl(RoomItemRepository roomItemRepository) {
        this.roomItemRepository = roomItemRepository;
    }

    @Override
    public List<RoomItemEntity> findAll(long roomId) {
        return this.roomItemRepository.findAllByRoomId(roomId);
    }

    @Override
    public Optional<RoomItemEntity> findById(Long id) {
        return this.roomItemRepository.findById(id);
    }

    @Override
    public RoomItemEntity updateRoomItem(RoomItemEntity roomItemEntity) {
        return this.roomItemRepository.saveAndFlush(roomItemEntity);
    }

    @Override
    public boolean delete(Long id) {
        this.roomItemRepository.deleteById(id);
        return !this.roomItemRepository.findById(id).isPresent();
    }

    @Override
    public RoomItemEntity addRoomItem(RoomItemEntity roomItemEntity) {
        return this.roomItemRepository.saveAndFlush(roomItemEntity);
    }
}
