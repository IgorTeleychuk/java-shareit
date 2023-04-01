package ru.practicum.server.request.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.server.item.dto.ItemDto;
import ru.practicum.server.request.model.ItemRequest;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemRequestMapper {

    public static ItemRequest toItemRequest(ItemRequestDtoShort shortDto) {
        return ItemRequest.builder()
                .description(shortDto.getDescription())
                .created(shortDto.getCreated())
                .build();
    }

    public static ItemRequestDto toDto(ItemRequest itemRequest) {
        return ItemRequestDto.builder()
                .id(itemRequest.getId())
                .description(itemRequest.getDescription())
                .requesterId(itemRequest.getRequester().getId())
                .created(itemRequest.getCreated())
                .build();
    }

    public static ItemRequestDto toItemRequestDto(ItemRequest itemRequest, List<ItemDto> items) {
        return new ItemRequestDto(itemRequest.getId(), itemRequest.getDescription(),
                itemRequest.getRequester().getId(),
                itemRequest.getCreated(), items);
    }
}
