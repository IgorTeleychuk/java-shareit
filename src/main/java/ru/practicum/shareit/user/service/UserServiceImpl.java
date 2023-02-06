package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.practicum.shareit.user.dto.UserMapper.toUser;
import static ru.practicum.shareit.user.dto.UserMapper.toUserDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserMapper::toUserDto).collect(toList());
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user with ID: " + id));

        return toUserDto(user);
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = toUser(userDto);
        throwIfEmailNotUnique(user);

        return toUserDto(userRepository.create(user));
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        User user = toUser(userDto);
        User updatedUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User data cannot be updated. " +
                        "No user with ID: " + id));
        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            if (userDto.getName() == null) {
                throwIfEmailNotUnique(user);
            }
            updatedUser.setEmail(user.getEmail());
        }
        if (user.getName() != null && !user.getName().isBlank()) {
            updatedUser.setName(user.getName());
        }

        return toUserDto(updatedUser);
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

