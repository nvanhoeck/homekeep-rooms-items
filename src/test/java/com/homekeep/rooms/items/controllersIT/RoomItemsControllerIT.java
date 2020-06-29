package com.homekeep.rooms.items.controllersIT;

import com.homekeep.rooms.items.SampleDataUtil;
import com.homekeep.rooms.items.controllers.RoomItemsController;
import com.homekeep.rooms.items.dtos.RoomItemDto;
import com.homekeep.rooms.items.entities.RoomItemEntity;
import com.homekeep.rooms.items.repositories.RoomItemRepository;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:test.properties")
public class RoomItemsControllerIT {

    @Autowired
    private RoomItemRepository roomItemRepository;

    @Autowired
    private RoomItemsController roomItemsController;


    @Test
    public void whenFindAll_ReturnsRoomsDtos() {

        RoomItemEntity storedRoomItem = this.roomItemRepository.saveAndFlush(SampleDataUtil.buildRoomEntity("test"));
        List<RoomItemDto> roomItemDtos = roomItemsController.getRoomItems(1L);
        assertThat(roomItemDtos).extracting("id", "name").contains(Tuple.tuple(storedRoomItem.getId(), "test"));
    }

    @Test
    public void whenUpdateRoomItem_ReturnsUpdatedRoomItem() {
        RoomItemEntity storedRoomItem = this.roomItemRepository.saveAndFlush(SampleDataUtil.buildRoomEntity("old"));
        RoomItemDto sendRoomItem = SampleDataUtil.buildRoomItemDto(storedRoomItem.getId(), "updated");
        RoomItemDto updatedRoom = roomItemsController.updateRoomItem(sendRoomItem);
        assertThat(updatedRoom).isEqualTo(sendRoomItem);
    }
    @Test
    public void whenAddRoomItem_ReturnsUpdatedRoomItem() {
        RoomItemDto sendRoomItem =  SampleDataUtil.buildRoomItemDto("sended");
        RoomItemDto newRoomItem = roomItemsController.addRoomItem(sendRoomItem);
        assertThat(newRoomItem.getId()).isNotNull();
        assertThat(newRoomItem).extracting("name").isEqualTo(sendRoomItem.getName());
    }


    @Test
    public void whenDeleteRoomItem_ReturnTrue() {
        RoomItemEntity deleteMe = this.roomItemRepository.saveAndFlush(SampleDataUtil.buildRoomEntity("delete me"));
        boolean isDeleted = roomItemsController.deleteRoomItem(deleteMe.getId());
        assertTrue(isDeleted);
    }
}
