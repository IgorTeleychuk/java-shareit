package ru.practicum.server.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CommentShortDto {
    private Long id;

    private String text;

    private String authorName;

    private LocalDateTime created;
}