package ru.practicum.shareit.item.dto;

import lombok.*;
import ru.practicum.shareit.booking.dto.BookingForItemDto;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode
public class ItemDto {
    private Long id;

    private String name;

    private String description;

    private Boolean available;

    private BookingForItemDto lastBooking;

    private BookingForItemDto nextBooking;

    private List<CommentDto> comments;

    private Long requestId;
}
