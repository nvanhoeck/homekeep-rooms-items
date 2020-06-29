package com.homekeep.rooms.items.repositories;

import com.homekeep.rooms.items.entities.RoomItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomItemRepository extends JpaRepository<RoomItemEntity, Long> {

    List<RoomItemEntity> findAllByRoomId(long roomId);
}
