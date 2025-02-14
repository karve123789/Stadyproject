package ru.practicum.shareit.item.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemDtoWithRequestId extends ItemDto {
    private Integer requestId;

    public ItemDtoWithRequestId(ItemDto itemDto, Integer requestId) {
        super(itemDto.getId(), itemDto.getName(), itemDto.getDescription(), itemDto.getAvailable(), itemDto.getPrice());
        this.requestId = requestId;
    }

    public ItemDtoWithRequestId(Integer id, String name, String description, Boolean available, BigDecimal price, Integer requestId) {
        super(id, name, description, available, price);
        this.requestId = requestId;
    }
}