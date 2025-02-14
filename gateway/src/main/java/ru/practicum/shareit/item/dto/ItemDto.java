package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Integer id;
    private String name;
    private String description;
    private Boolean available;
    private BigDecimal price;
}
