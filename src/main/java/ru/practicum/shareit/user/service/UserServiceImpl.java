package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.practicum.shareit.user.dto.UserMapper.toUserDto;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(toUserDto(user));
        }

        return users;
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user with ID: " + id));

        return toUserDto(user);
    }

    @Override
    public UserDto create(User user) {
        throwIfEmailNotUnique(user);

        return toUserDto(userRepository.create(user));
    }

    @Override
    public UserDto update(User user, Long id) {
        User updatedUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User data cannot be updated. " +
                        "No user with ID: " + id));
        if (user.getEmail() != null) {
            throwIfEmailNotUnique(user);
            updatedUser.setEmail(user.getEmail());
        }
        if (user.getName() != null) {
            updatedUser.setName(user.getName());
        }

        return toUserDto(userRepository.update(updatedUser));
    }

    @Override
    public void delete(Long id) {
        getById(id);
        userRepository.delete(id);
    }

    private void throwIfEmailNotUnique(User user) {
        for (User userCheck : userRepository.findAll()) {
            if (user.getEmail().equals(userCheck.getEmail())) {
                throw new ValidationException("The user with this email is already registered");
            }
        }
    }
}
