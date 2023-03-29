package ru.practicum.server.item;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.server.booking.dto.BookingMapper;
import ru.practicum.server.booking.model.Booking;
import ru.practicum.server.booking.model.BookingStatus;
import ru.practicum.server.booking.repository.BookingRepository;
import ru.practicum.server.exception.NotFoundException;
import ru.practicum.server.item.dto.CommentMapper;
import ru.practicum.server.item.dto.ItemDto;
import ru.practicum.server.item.dto.ItemMapper;
import ru.practicum.server.item.dto.ItemShortDto;
import ru.practicum.server.item.model.Comment;
import ru.practicum.server.item.model.Item;
import ru.practicum.server.item.repository.CommentRepository;
import ru.practicum.server.item.repository.ItemRepository;
import ru.practicum.server.item.service.ItemServiceImpl;
import ru.practicum.server.user.model.User;
import ru.practicum.server.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Transactional
@SpringBootTest(
        properties = "db.name=test",
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ItemServiceImplIntegrationTest {
    private final ItemServiceImpl itemService;

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    private final CommentRepository commentRepository;

    private Long itemId;
    private Long userId;
    private Long commentId;
    private User user;
    private Item item;
    private Comment comment;
    private Booking booking;

    @BeforeEach
    void beforeEach() {
        user = new User(null, "Alex", "alex.b@yandex.ru");
        user = userRepository.save(user);
        userId = user.getId();

        item = new Item(null, "item bag", "description", true, user,
                null);
        item = itemRepository.save(item);
        itemId = item.getId();

        booking = new Booking(null, LocalDateTime.now(), LocalDateTime.now(), item, user, BookingStatus.APPROVED);
        booking = bookingRepository.save(booking);

        comment = new Comment(null, "comment", item, user, LocalDateTime.now());
        comment = commentRepository.save(comment);
        commentId = comment.getId();
    }
}
