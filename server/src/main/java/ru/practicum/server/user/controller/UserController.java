package ru.practicum.server.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.server.user.dto.UserDto;
import ru.practicum.server.user.service.UserService;

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
        log.info("GET:/users/{id} request received with parameters:id = {}", id);
        return userService.getById(id);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto) {
        log.info("POST:/users request received with parameters: userDto = {}", userDto);
        return userService.create(userDto);
    }

    @PatchMapping("/{id}")
    public UserDto update(@RequestBody UserDto userDto, @PathVariable Long id) {
        log.info("PATCH:/users/{id} request received with parameters: userDto = {}, id = {}", userDto, id);
        return userService.update(userDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("DELETE:/users/{id} request received with parameters: id = {}", id);
        userService.delete(id);
    }
}

