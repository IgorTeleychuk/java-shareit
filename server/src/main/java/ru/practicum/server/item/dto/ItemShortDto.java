package ru.practicum.server.item.dto;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode
public class ItemShortDto {
    private Long id;

    private String name;

    private String description;

    private Boolean available;

    private Long requestId;
}