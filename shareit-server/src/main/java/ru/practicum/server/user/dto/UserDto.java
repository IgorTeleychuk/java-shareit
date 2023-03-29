package ru.practicum.server.user.dto;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {
    private Long id;

    private String name;

    private String email;
}
