package ru.practicum.shareit.booking;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDto.Booker;
import ru.practicum.shareit.booking.dto.BookingDto.Item;
import ru.practicum.shareit.booking.model.BookingStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
class BookingDtoJsonTest {

    @Autowired
    private JacksonTester<BookingDto> json;
    private LocalDateTime start = LocalDateTime.now().plusDays(1);
    private LocalDateTime end = start.plusDays(2);
    private final BookingDto.Booker booker = new BookingDto.Booker(1L, "Alex");
    private final BookingDto.Item bookingItem = new BookingDto.Item(1L, "Item");


    @BeforeEach
    void beforeEach() {
    }

    @Test
    void testUserDto() throws Exception {
        BookingDto userDto = new BookingDto(
                1L,
                start,
                end,
                BookingStatus.WAITING,
                booker,
                bookingItem);

        JsonContent<BookingDto> result = json.write(userDto);

        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.status").isEqualTo("WAITING");

    }

    @Test
    void testSerialize() throws Exception {
        LocalDateTime start = LocalDateTime.of(2025, 1, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2026, 1, 1, 0, 0, 0);
        BookingDto bookingDto = new BookingDto(1L, start, end, BookingStatus.WAITING,
                new Booker(1L, "user name"),
                new Item(1L, "item name"));

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        JsonContent<BookingDto> result = json.write(bookingDto);

        Assertions.assertThat(result).hasJsonPath("$.id");
        Assertions.assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        Assertions.assertThat(result).hasJsonPath("$.start");
        Assertions.assertThat(result).extractingJsonPathStringValue("$.start")
                .isEqualTo(bookingDto.getStart().format(formatter));
        Assertions.assertThat(result).hasJsonPath("$.end");
        Assertions.assertThat(result).extractingJsonPathStringValue("$.end")
                .isEqualTo(bookingDto.getEnd().format(formatter));
        Assertions.assertThat(result).hasJsonPath("$.item");
        Assertions.assertThat(result).extractingJsonPathNumberValue("$.item.id")
                .isEqualTo(1);
        Assertions.assertThat(result).extractingJsonPathStringValue("$.item.name")
                .isEqualTo(bookingDto.getItem().getName());
        Assertions.assertThat(result).hasJsonPath("$.booker");
        Assertions.assertThat(result).extractingJsonPathNumberValue("$.booker.id")
                .isEqualTo(1);
        Assertions.assertThat(result).extractingJsonPathStringValue("$.booker.name")
                .isEqualTo(bookingDto.getBooker().getName());
        Assertions.assertThat(result).hasJsonPath("$.status");
        Assertions.assertThat(result).extractingJsonPathStringValue("$.status")
                .isEqualTo(bookingDto.getStatus().toString());
        BookingDto bookingDtoTest = json.parseObject(result.getJson());
        Assertions.assertThat(bookingDtoTest).isEqualTo(bookingDto);
    }
}
