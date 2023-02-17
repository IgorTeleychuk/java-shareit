package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.util.Create;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> getAll(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("GET:/items request received");
        return itemService.getAll(userId);
    }

    @GetMapping("/{id}")
    public ItemDto getById(@PathVariable Long id, @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("GET:/items/{id} request received");
        return itemService.getById(id, userId);
    }

    @PostMapping
    public ItemDto create(@RequestHeader("X-Sharer-User-Id") Long userId, @Validated(Create.class) @RequestBody ItemDto itemDto) {
        log.info("POST:/items request received");
        return itemService.create(itemDto, userId);
    }

    @PatchMapping("/{id}")
    public ItemDto update(@RequestBody ItemDto itemDto, @PathVariable Long id,
                          @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("PATCH:/items/{id} request received");
        return itemService.update(itemDto, id, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("DELETE:/items/{id} request received");
        itemService.delete(id);
    }

    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam String text) {
        log.info("GET:/search request received");
        if (!text.isBlank()) {
            return itemService.search(text);
        } else {
            return Collections.emptyList();
        }
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto createComment(@PathVariable Long itemId, @RequestHeader("X-Sharer-User-Id") Long userId,
                                    @Valid @RequestBody CommentDto commentDto) {
        log.info("POST:/items/{itemId}/comment request received");
        return itemService.createComment(itemId, userId, commentDto);
    }
}
