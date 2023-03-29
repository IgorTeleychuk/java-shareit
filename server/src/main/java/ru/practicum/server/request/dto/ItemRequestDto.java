package ru.practicum.server.request.dto;

import lombok.*;
import ru.practicum.server.item.dto.ItemDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ItemRequestDto {
    private Long id;

    private String description;

    private Long requesterId;

    private LocalDateTime created;

    private List<ItemDto> items;
}
