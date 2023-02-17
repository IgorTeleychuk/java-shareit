package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static ru.practicum.shareit.user.dto.UserMapper.toUser;
import static ru.practicum.shareit.user.dto.UserMapper.toUserDto;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserMapper::toUserDto).collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found User with Id: " + id));

        return toUserDto(user);
    }

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        User user = toUser(userDto);
        return toUserDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public UserDto update(UserDto userDto, Long id) {
        User updatedUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not possible update User data " +
                        "Not found User with Id: " + id));
        Optional.ofNullable(userDto.getEmail()).ifPresent(updatedUser::setEmail);
        Optional.ofNullable(userDto.getName()).ifPresent(updatedUser::setName);

        return toUserDto(userRepository.save(updatedUser));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

