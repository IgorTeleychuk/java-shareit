package ru.practicum.shareit.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();

    private Long id = 1L;

    @Override
    public List<User> findAll() {
        log.info("All users was provided.");
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        log.info("User with ID {} was found.", id);
        return users.get(id) == null ? Optional.empty() : Optional.of(users.get(id));
    }

    @Override
    public User create(User user) {
        user.setId(id);
        id++;
        users.put(user.getId(), user);
        log.info("User with ID {} was created.", user.getId());
        return user;
    }

    @Override
    public User update(User user) {
        users.put(user.getId(), user);
        log.info("User with ID {} was updated.", user.getId());
        return user;
    }

    @Override
    public void delete(Long id) {
        users.remove(id);
        log.info("User with ID {} was remove.", id);
    }
}