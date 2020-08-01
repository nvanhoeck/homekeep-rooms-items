package com.homekeep.rooms.items.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


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
    private Double costPerItem;
    @Column
    private Double totalCost;
    @Column
    private Double spendedCost;
    @Column
    private Integer amountWanted;
    @Column
    private Integer amountOwned;
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
    private Set<Long> alternatives = new HashSet<>();
    @Column
    private Long alternativeOf;
    @Column
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "roomItem", cascade = CascadeType.ALL)
    private Set<RoomItemColor> colors = new HashSet<>();
}
