package ru.practicum.shareit.item.dto;

import org.junit.jupiter.api.Test;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.user.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ItemMapperTest {

    private final ItemMapper itemMapper = ItemMapper.INSTANCE;

    @Test
    void toItemDto_FromItem() {
        Item item = new Item(1, "Balalaika", "Brand new balalaika", true, null, null, new BigDecimal("100.00"));

        ItemDto itemDto = itemMapper.toItemDto(item);

        assertNotNull(itemDto);

        assertEquals(item.getId(), itemDto.getId());
        assertEquals(item.getName(), itemDto.getName());
        assertEquals(item.getDescription(), itemDto.getDescription());
        assertEquals(item.getAvailable(), itemDto.getAvailable());
        assertEquals(item.getPrice(), itemDto.getPrice());
    }

    @Test
    void toItemDtoWithRequestId_FromItem() {
        User userRequesting = new User(2, "Shaun", "shaun@ya.ru");
        Request request = new Request(1, "Looking for a balalaika", LocalDateTime.now(), userRequesting);
        User owner = new User(1, "Jason", "jason@ya.ru");
        Item item = new Item(1, "Balalaika", "Brand new balalaika", true, owner, request, new BigDecimal("100.00"));

        ItemDtoWithRequestId itemDtoWithRequestId = itemMapper.toItemDtoWithRequestId(item);

        assertNotNull(itemDtoWithRequestId);

        assertEquals(item.getId(), itemDtoWithRequestId.getId());
        assertEquals(item.getName(), itemDtoWithRequestId.getName());
        assertEquals(item.getDescription(), itemDtoWithRequestId.getDescription());
        assertEquals(item.getAvailable(), itemDtoWithRequestId.getAvailable());
        assertEquals(item.getPrice(), itemDtoWithRequestId.getPrice());
        assertNull(itemDtoWithRequestId.getRequestId());
    }

    @Test
    void toItem_FromItemDto() {
        ItemDto itemDto = new ItemDto(1, "Balalaika", "Brand new balalaika", true, new BigDecimal("100.00"));
        Item item = itemMapper.toItem(itemDto);

        assertNotNull(item);

        assertEquals(itemDto.getId(), item.getId());
        assertEquals(itemDto.getName(), item.getName());
        assertEquals(itemDto.getDescription(), item.getDescription());
        assertEquals(itemDto.getAvailable(), item.getAvailable());
        assertEquals(itemDto.getPrice(), item.getPrice());
    }

    @Test
    void toItem_FromItemDtoWithRequestId() {
        ItemDto itemDto = new ItemDto(1, "Balalaika", "Brand new balalaika", true, new BigDecimal("100.00"));
        ItemDtoWithRequestId itemDtoWithRequestId = new ItemDtoWithRequestId(itemDto, 1);

        Item item = itemMapper.toItem(itemDtoWithRequestId);

        assertNotNull(item);

        assertEquals(itemDtoWithRequestId.getId(), item.getId());
        assertEquals(itemDtoWithRequestId.getName(), item.getName());
        assertEquals(itemDtoWithRequestId.getDescription(), item.getDescription());
        assertEquals(itemDtoWithRequestId.getAvailable(), item.getAvailable());
        assertEquals(itemDto.getPrice(), item.getPrice());
        assertNull(item.getRequest());
    }
}
//package ru.practicum.shareit.item.dto;
//
//import org.junit.jupiter.api.Test;
//import ru.practicum.shareit.item.model.Item;
//import ru.practicum.shareit.request.model.Request;
//import ru.practicum.shareit.user.model.User;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ItemMapperTest {
//
//    @Test
//    void toItemDto_FromItem() {
//        Item item = new Item(1, "Balalaika", "Brand new balalaika", true, );
//
//        ItemDto itemDto = ItemMapper.INSTANCE.toItemDto(item);
//
//        assertNotNull(itemDto);
//
//        assertEquals(item.getId(), itemDto.getId());
//        assertEquals(item.getName(), itemDto.getName());
//        assertEquals(item.getDescription(), itemDto.getDescription());
//        assertEquals(item.getAvailable(), itemDto.getAvailable());
//    }
//
//    @Test
//    void toItemDtoWithRequestId_FromItem() {
//        User userRequesting = new User(2, "Shaun", "shaun@ya.ru");
//        Request request = new Request(1, "Looking for a balalaika", LocalDateTime.now(), userRequesting);
//        User owner = new User(1, "Jason", "jason@ya.ru");
//        Item item = new Item(1, "Balalaika", "Brand new balalaika", true, owner, request);
//
//        ItemDtoWithRequestId itemDtoWithRequestId = ItemMapper.INSTANCE.toItemDtoWithRequestId(item);
//
//        assertNotNull(itemDtoWithRequestId);
//
//        assertEquals(item.getId(), itemDtoWithRequestId.getId());
//        assertEquals(item.getName(), itemDtoWithRequestId.getName());
//        assertEquals(item.getDescription(), itemDtoWithRequestId.getDescription());
//        assertEquals(item.getAvailable(), itemDtoWithRequestId.getAvailable());
//        assertNull(itemDtoWithRequestId.getRequestId());
//    }
//
//    @Test
//    void toItem_FromItemDto() {
//        ItemDto itemDto = new ItemDto(1, "Balalaika", "Brand new balalaika", true);
//        Item item = ItemMapper.INSTANCE.toItem(itemDto);
//
//        assertNotNull(item);
//
//        assertEquals(itemDto.getId(), item.getId());
//        assertEquals(itemDto.getName(), item.getName());
//        assertEquals(itemDto.getDescription(), item.getDescription());
//        assertEquals(itemDto.getAvailable(), item.getAvailable());
//    }
//
//    @Test
//    void toItem_FromItemDtoWithRequestId() {
//        ItemDto itemDto = new ItemDto(1, "Balalaika", "Brand new balalaika", true);
//        ItemDtoWithRequestId itemDtoWithRequestId = new ItemDtoWithRequestId(itemDto, 1);
//
//        Item item = ItemMapper.INSTANCE.toItem(itemDtoWithRequestId);
//
//        assertNotNull(item);
//
//        assertEquals(itemDtoWithRequestId.getId(), item.getId());
//        assertEquals(itemDtoWithRequestId.getName(), item.getName());
//        assertEquals(itemDtoWithRequestId.getDescription(), item.getDescription());
//        assertEquals(itemDtoWithRequestId.getAvailable(), item.getAvailable());
//        assertNull(item.getRequest());
//    }
//}