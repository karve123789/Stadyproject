package ru.practicum.shareit.request;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.request.model.Request;
import ru.practicum.shareit.request.repository.RequestRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RequestRepositoryTest {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private User user2;
    private Request request;

    @BeforeEach
    void setup() {
        this.user = addUser("Jason", "jason@ya.ru");
        this.user2 = addUser("Shaun", "shaun@ya.ru"); // Create another user
        this.request = addRequest("Looking for a balalaika", LocalDateTime.now().minusDays(6), user);
        addItem("Balalaika", "Brand new balalaika", true, user, request, new BigDecimal("100.00"));
    }

    @AfterEach
    void afterEach() {
        itemRepository.deleteAll();
        userRepository.deleteAll();
        requestRepository.deleteAll();
    }

    @Test
    void findByRequestingUserIdOrderByCreatedDesc() {
        List<Request> requestsSaved = requestRepository.findByRequestingUserIdOrderByCreatedDesc(user.getId());

        assertNotNull(requestsSaved);
        assertEquals(1, requestsSaved.size());

        Request requestSaved = requestsSaved.get(0);

        assertNotNull(requestSaved);
        assertEquals(user.getId(), requestSaved.getRequestingUser().getId());
    }

    @Test
    void findByRequestingUserIdNotOrderByCreatedDesc() {
        addRequest("Looking for something else", LocalDateTime.now().minusDays(5), user2);

        List<Request> requestsSaved = requestRepository.findByRequestingUserIdNotOrderByCreatedDesc(user.getId());

        assertNotNull(requestsSaved);
        assertEquals(1, requestsSaved.size()); // Теперь ожидаем 1, потому что ищем запросы, созданные другим пользователем

        Request requestSaved = requestsSaved.get(0);

        assertNotNull(requestSaved);
        assertNotEquals(user.getId(), requestSaved.getRequestingUser().getId());
    }

    private Request addRequest(String description, LocalDateTime created, User requestingUser) {
        Request requestToSave = new Request();
        requestToSave.setDescription(description);
        requestToSave.setCreated(created);
        requestToSave.setRequestingUser(requestingUser);
        return requestRepository.save(requestToSave);
    }

    private Item addItem(String name, String description, boolean available, User owner, Request request, BigDecimal price) {
        Item itemToSave = new Item();
        itemToSave.setName(name);
        itemToSave.setDescription(description);
        itemToSave.setAvailable(available);
        itemToSave.setOwner(owner);
        itemToSave.setRequest(request);
        itemToSave.setPrice(price);
        return itemRepository.save(itemToSave);
    }

    private User addUser(String name, String email) {
        User userToSave = new User();
        userToSave.setName(name);
        userToSave.setEmail(email);
        return userRepository.save(userToSave);
    }
}
//package ru.practicum.shareit.request;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import ru.practicum.shareit.item.model.Item;
//import ru.practicum.shareit.item.repository.ItemRepository;
//import ru.practicum.shareit.request.model.Request;
//import ru.practicum.shareit.request.repository.RequestRepository;
//import ru.practicum.shareit.user.model.User;
//import ru.practicum.shareit.user.repository.UserRepository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//public class RequestRepositoryTest {
//
//    @Autowired
//    private RequestRepository requestRepository;
//
//    @Autowired
//    private ItemRepository itemRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private User user;
//
//    @BeforeEach
//    void setup() {
//        Item item = addItem("Balalaika", "Brand new balalaika", true, user);
//        this.user = addUser("Jason", "jason@ya.ru");
//    }
//
//    @AfterEach
//    void afterEach() {
//        itemRepository.deleteAll();
//        userRepository.deleteAll();
//        requestRepository.deleteAll();
//    }
//
//    @Test
//    void findByRequestingUserIdOrderByCreatedDesc() {
//        addRequest("Looking for a balalaika", LocalDateTime.now().minusDays(6), user);
//        List<Request> requestsSaved = requestRepository.findByRequestingUserIdOrderByCreatedDesc(user.getId());
//
//        assertNotNull(requestsSaved);
//        assertEquals(1, requestsSaved.size());
//
//        Request requestSaved = requestsSaved.get(0);
//
//        assertNotNull(requestSaved);
//        assertEquals(user.getId(), requestSaved.getRequestingUser().getId());
//    }
//
//    @Test
//    void findByRequestingUserIdNotOrderByCreatedDesc() {
//        addRequest("Looking for a balalaika", LocalDateTime.now().minusDays(6), user);
//        List<Request> requestsSaved = requestRepository.findByRequestingUserIdNotOrderByCreatedDesc(5);
//
//        assertNotNull(requestsSaved);
//        assertEquals(1, requestsSaved.size());
//
//        Request requestSaved = requestsSaved.get(0);
//
//        assertNotNull(requestSaved);
//        assertEquals(user.getId(), requestSaved.getRequestingUser().getId());
//    }
//
//    private Request addRequest(String description, LocalDateTime created, User requestingUser) {
//        Request requestToSave = new Request();
//        requestToSave.setDescription(description);
//        requestToSave.setCreated(created);
//        requestToSave.setRequestingUser(requestingUser);
//        return requestRepository.save(requestToSave);
//    }
//
//    private Item addItem(String name, String description, boolean available, User owner) {
//        Item itemToSave = new Item();
//        itemToSave.setName(name);
//        itemToSave.setDescription(description);
//        itemToSave.setAvailable(available);
//        itemToSave.setOwner(owner);
//        return itemRepository.save(itemToSave);
//    }
//
//    private User addUser(String name, String email) {
//        User userToSave = new User();
//        userToSave.setName(name);
//        userToSave.setEmail(email);
//        return userRepository.save(userToSave);
//    }
//}