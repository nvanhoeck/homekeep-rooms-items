package com.homekeep.rooms.items.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity(name = "ROOM_ITEM")
public class RoomItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String costPerItem;
    @Column
    private String totalCost;
    @Column
    private String spendedCost;
    @Column
    private String amountWanted;
    @Column(nullable = false)
    private Long roomId;
    @Column
    private String image;
    @Column
    private String urlLink;
    @Column
    private Boolean locked;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Room_Item_Alternatives", joinColumns = @JoinColumn(name = "Room_Item_Id"))
    private Set<Long> alternatives;
    @Column
    private Long alternativeOf;
    @Column
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "roomItem", cascade = CascadeType.ALL)
    private Set<RoomItemColor> colors;
}
