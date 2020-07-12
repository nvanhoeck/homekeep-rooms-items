package com.homekeep.rooms.items.dtos;

import com.homekeep.rooms.items.entities.RoomItemColor;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.util.Set;

@Immutable
@Data
public class RoomItemDto {
    private Long id;
    private String name;
    private Double costPerItem;
    private Double totalCost;
    private Double spendedCost;
    private Integer amountWanted;
    private Integer amountOwned;
    @NotNull
    private Long roomId;
    private String image;
    private String urlLink;
    private Boolean locked;
    private Long[] alternatives;
    private Long alternativeOf;
    private RoomItemColor[] colors;
}
