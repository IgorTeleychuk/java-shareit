package ru.practicum.shareit.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.util.Create;
import ru.practicum.shareit.util.Update;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAll() {
        log.info("GET:/users request received");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        log.info("GET:/users/{id} request received");
        return userService.getById(id);
    }

    @PostMapping
    public UserDto create(@Validated(Create.class) @RequestBody UserDto userDto) {
        log.info("POST:/users request received");
        return userService.create(userDto);
    }

    @PatchMapping("/{id}")
    public UserDto update(@Validated(Update.class) @RequestBody UserDto userDto, @PathVariable Long id) {
        log.info("PATCH:/users/{id} request received");
        return userService.update(userDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("DELETE:/users/{id} request received");
        userService.delete(id);
    }
}

