package ru.practicum.shareit.booking.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookingDtoInputTest {

    @Autowired
    private JacksonTester<BookingDtoInput> jacksonTester;

    @Test
    void testSerialize() throws Exception {
        // Создаем Item с обязательными параметрами
        Item item = new Item(1, "Balalaika", "Brand new balalaika", true, null, null, new BigDecimal("100.00"));

        // Создаем User
        User user = new User(1, "Jason", "jason@ya.ru");

        // Создаем BookingDtoInput
        LocalDateTime now = LocalDateTime.now();
        BookingDtoInput bookingDtoInput = new BookingDtoInput(user.getId(), item.getId(), now, now.plusHours(1));

        // Сериализуем BookingDtoInput в JSON
        JsonContent<BookingDtoInput> bookingDtoInputSaved = jacksonTester.write(bookingDtoInput);

        // Проверяем JSON-структуру и значения
        assertThat(bookingDtoInputSaved).hasJsonPath("$.start");
        assertThat(bookingDtoInputSaved).hasJsonPath("$.end");
        assertThat(bookingDtoInputSaved).hasJsonPath("$.bookerId");
        assertThat(bookingDtoInputSaved).hasJsonPath("$.itemId");

        // Обратите внимание: LocalDateTime сериализуется в строку, поэтому мы не можем использовать isEqualTo(now) напрямую.
        // Вместо этого мы просто проверяем, что поля присутствуют.
        assertThat(bookingDtoInputSaved).hasJsonPathValue("$.start");
        assertThat(bookingDtoInputSaved).hasJsonPathValue("$.end");

        assertThat(bookingDtoInputSaved).extractingJsonPathNumberValue("$.bookerId").isEqualTo(user.getId().intValue()); // Преобразуем Integer в int для сравнения
        assertThat(bookingDtoInputSaved).extractingJsonPathNumberValue("$.itemId").isEqualTo(item.getId().intValue()); // Преобразуем Integer в int для сравнения
    }
}

//package ru.practicum.shareit.booking.dto;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.JsonTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.boot.test.json.JsonContent;
//import ru.practicum.shareit.item.model.Item;
//import ru.practicum.shareit.user.model.User;
//
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@JsonTest
//public class BookingDtoInputTest {
//
//    @Autowired
//    private JacksonTester<BookingDtoInput> jacksonTester;
//
//    @Test
//    void testSerialize() throws Exception {
//        Item item = new Item(1, "Balalaika", "Brand new balalaika", true, );
//        User user = new User(1, "Jason", "jason@ya.ru");
//        BookingDtoInput bookingDtoInput = new BookingDtoInput(1, 1, LocalDateTime.now(), LocalDateTime.now());
//
//        JsonContent<BookingDtoInput> bookingDtoInputSaved = jacksonTester.write(bookingDtoInput);
//
//        assertThat(bookingDtoInputSaved).hasJsonPath("$.start");
//        assertThat(bookingDtoInputSaved).hasJsonPath("$.end");
//        assertThat(bookingDtoInputSaved).hasJsonPath("$.bookerId");
//        assertThat(bookingDtoInputSaved).hasJsonPath("$.itemId");
//        assertThat(bookingDtoInputSaved).hasJsonPathValue("$.start");
//        assertThat(bookingDtoInputSaved).hasJsonPathValue("$.end");
//        assertThat(bookingDtoInputSaved).extractingJsonPathNumberValue("$.bookerId").isEqualTo(user.getId());
//        assertThat(bookingDtoInputSaved).extractingJsonPathNumberValue("$.itemId").isEqualTo(item.getId());
//    }
//}