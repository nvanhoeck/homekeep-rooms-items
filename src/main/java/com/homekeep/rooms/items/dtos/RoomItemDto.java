package com.homekeep.rooms.items.dtos;

import com.homekeep.rooms.items.entities.RoomItemColor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.util.Set;

@Immutable
@Data
public class RoomItemDto {
    private Long id;
    private String name;
    private String costPerItem;
    private String totalCost;
    private String spendedCost;
    private String amountWanted;
    private Long roomId;
    private String image;
    private String urlLink;
    private Boolean locked;
    private Set<Long> alternatives;
    private Long alternativeOf;
    private Set<RoomItemColor> colors;
}
