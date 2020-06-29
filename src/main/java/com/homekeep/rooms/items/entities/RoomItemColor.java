package com.homekeep.rooms.items.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Room_Item_Color")
public class RoomItemColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String value;
    @ManyToOne
    @JoinColumn(name = "room_item_id")
    private RoomItemEntity roomItem;
}
