package com.homekeep.rooms.items.managers;

import com.homekeep.rooms.items.SampleDataUtil;
import com.homekeep.rooms.items.dtos.RoomItemDto;
import com.homekeep.rooms.items.entities.RoomItemEntity;
import com.homekeep.rooms.items.managers.impl.RoomItemManagerImpl;
import com.homekeep.rooms.items.services.RoomItemService;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomItemManagerTest {

    @InjectMocks
    private RoomItemManagerImpl fixture;

    @Mock
    private RoomItemService roomItemService;


    @Test
    public void whenFindAllRoomsItemsByRoomId_returnRoomItemsDtoList() {
        RoomItemEntity roomItemEntity = SampleDataUtil.buildRoomEntity(1L, "test");
        when(roomItemService.findAll(1L)).thenReturn(new ArrayList<>(Arrays.asList(roomItemEntity)));
        List<RoomItemDto> roomItemDtos = this.fixture.findAll(1L);
        assertThat(roomItemDtos).extracting("id", "name").contains(Tuple.tuple(1L, "test"));
    }

    @Test
    public void whenUpdateRoomItem_returnUpdatedRoomItem() {
        RoomItemEntity sendRoomItemEnity = SampleDataUtil.buildRoomEntity(1L, "send");
        RoomItemDto sendRoomItemDto= SampleDataUtil.buildRoomItemDto(1L, "send");
        when(roomItemService.updateRoomItem(sendRoomItemEnity)).thenReturn(sendRoomItemEnity);
        RoomItemDto updatedRoomItem = this.fixture.updateRoomItem(sendRoomItemDto);
        assertThat(updatedRoomItem).isEqualTo(sendRoomItemDto);
    }

    @Test
    public void whenDeleteRoomItem_returnTrue() {
        when(roomItemService.delete(2L)).thenReturn(true);
        boolean deleted = this.fixture.delete(2L);
        assertTrue(deleted);
    }
}
