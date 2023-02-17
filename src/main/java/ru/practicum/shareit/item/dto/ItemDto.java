package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.booking.dto.BookingShortDto;
import ru.practicum.shareit.util.Create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
public class ItemDto {
    private Long id;

    @NotBlank(groups = {Create.class})
    private String name;

    @NotBlank(groups = {Create.class})
    private String description;

    @NotNull(groups = {Create.class})
    private Boolean available;

    private BookingShortDto lastBooking;

    private BookingShortDto nextBooking;

    private List<CommentDto> comments;
}
