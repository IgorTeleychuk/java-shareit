package ru.practicum.server.request.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.server.request.dto.ItemRequestDto;
import ru.practicum.server.request.dto.ItemRequestDtoShort;
import ru.practicum.server.request.service.ItemRequestService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/requests")
public class ItemRequestController {

    private final ItemRequestService itemRequestService;

    @GetMapping("/{requestId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemRequestDto getById(@RequestHeader("X-Sharer-User-Id") Long userId,
                                  @PathVariable Long requestId) {
        log.info("GET /requests/ request received with parameters: requestId = {}.", requestId);
        return itemRequestService.getById(userId, requestId);
    }

    @GetMapping("/all")
    public List<ItemRequestDto> getAll(@RequestHeader("X-Sharer-User-Id") Long userId,
                                       @RequestParam(defaultValue = "0") Integer from,
                                       @RequestParam(defaultValue = "10") Integer size) {
        log.info("GET /all request received with parameters: from = {}, size = {}", from, size);
        return itemRequestService.getAll(userId, from, size);
    }

    @GetMapping
    public List<ItemRequestDto> getAllByRequester(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                  @RequestParam(defaultValue = "0") Integer from,
                                                  @RequestParam(defaultValue = "10") Integer size) {
        log.info("GET /requests request received with parameters: from = {}, size = {}", from, size);
        return itemRequestService.getAllByRequester(userId, from, size);
    }

    @PostMapping
    public ItemRequestDto create(@RequestHeader("X-Sharer-User-Id") Long userId,
                                      @RequestBody ItemRequestDtoShort itemRequestDto) {
        log.info("POST /requests received with parameters: itemRequestDto = {}", itemRequestDto);
        return itemRequestService.create(userId, itemRequestDto);
    }
}
