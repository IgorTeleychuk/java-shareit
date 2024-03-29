package ru.practicum.server.item.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class CommentDto {
    private Long id;

    private String text;

    private String authorName;

    private LocalDateTime created;
}
