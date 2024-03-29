package ru.practicum.server.booking.dto;

import lombok.*;
import ru.practicum.server.booking.model.BookingStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class BookingDto {
    private Long id;

    private LocalDateTime start;

    private LocalDateTime end;

    private BookingStatus status;

    private Booker booker;

    private Item item;

    @Data
    public static class Booker {
        private final long id;
        private final String name;
    }

    @Data
    public static class Item {
        private final long id;
        private final String name;
    }
}
