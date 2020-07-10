package com.homekeep.rooms.items.managers;

import com.homekeep.rooms.items.SampleDataUtil;
import com.homekeep.rooms.items.dtos.RoomItemDto;
import com.homekeep.rooms.items.entities.RoomItemColor;
import com.homekeep.rooms.items.entities.RoomItemEntity;
import com.homekeep.rooms.items.managers.impl.RoomItemManagerImpl;
import com.homekeep.rooms.items.services.RoomItemService;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomItemManagerTest {
    private RoomItemManagerImpl fixture;

    @Mock
    private RoomItemService roomItemService;

    @BeforeEach
    void setUp() {
        this.fixture = new RoomItemManagerImpl(roomItemService);
    }

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

    @Test
    public void whenMapArray_returnSet() {
        RoomItemEntity sendRoomItemEnity = SampleDataUtil.buildRoomEntity(1L, "send");
        sendRoomItemEnity.setColors(new TreeSet(Collections.singleton(SampleDataUtil.buildColor("#fff"))));
        sendRoomItemEnity.setAlternatives(new TreeSet(Arrays.asList(1L, 3L, 5L)));
        RoomItemDto sendRoomItemDto= SampleDataUtil.buildRoomItemDto(1L, "send");
        sendRoomItemDto.setColors(new RoomItemColor[]{SampleDataUtil.buildColor("#fff")});
        sendRoomItemDto.setAlternatives(new Long[]{1L, 3L, 5L});
        when(roomItemService.updateRoomItem(sendRoomItemEnity)).thenReturn(sendRoomItemEnity);
        RoomItemDto updatedRoomItem = this.fixture.updateRoomItem(sendRoomItemDto);
        assertThat(updatedRoomItem).isEqualTo(sendRoomItemDto);
    }

    @Test
    public void whenFullMapping() {
        RoomItemDto roomItemDto = SampleDataUtil.buildFullRoomItemDto();
        RoomItemEntity roomItemEntity = SampleDataUtil.buildFullRoomItemEntity();
        when(roomItemService.updateRoomItem(roomItemEntity)).thenReturn(roomItemEntity);
        RoomItemDto mappedRoomItem = this.fixture.updateRoomItem(roomItemDto);
        assertThat(mappedRoomItem).isEqualTo(roomItemDto);
    }
}
