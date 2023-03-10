package ru.practicum.shareit.request.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ItemRequestDtoShort {
    private Long id;

    @NotBlank
    private String description;

    private Long requesterId;

    private LocalDateTime created;
}