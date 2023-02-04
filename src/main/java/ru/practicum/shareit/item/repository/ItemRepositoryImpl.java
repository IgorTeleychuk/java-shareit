package ru.practicum.shareit.item.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.*;

@Repository
@Slf4j
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Long, Item> items = new HashMap<>();

    private Long id = 1L;

    @Override
    public List<Item> findAll() {
        log.info("All items was provided.");
        return new ArrayList<>(items.values());
    }

    @Override
    public Optional<Item> findById(Long id) {
        log.info("Item with ID {} was found.", id);
        return items.get(id) != null ? Optional.of(items.get(id)) : Optional.empty();
    }

    @Override
    public Item create(Item item) {
        item.setId(id);
        id++;
        items.put(item.getId(), item);
        log.info("Item with ID {} was created.", item.getId());
        return item;
    }

    @Override
    public Item update(Item item) {
        items.put(item.getId(), item);
        log.info("Item with ID {} was updated.", item.getId());
        return item;
    }

    @Override
    public void delete(Long id) {
        items.remove(id);
        log.info("Item with ID {} was remove.", id);
    }
}