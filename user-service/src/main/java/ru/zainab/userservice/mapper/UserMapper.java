package ru.zainab.userservice.mapper;

import org.springframework.stereotype.Component;
import ru.zainab.userservice.dto.UserCreateRequest;
import ru.zainab.userservice.dto.UserResponse;
import ru.zainab.userservice.entity.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public User toEntity(UserCreateRequest request, String normalizedEmail, String normalizedNumber, String encodedPassword) {
        return new User(
                request.name(),
                normalizedEmail,
                normalizedNumber,
                encodedPassword
        );
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getStatus(),
                user.getRole(),
                user.getCreatedAt(),
                user.getVersion()

        );
    }
    public List<UserResponse> toResponseList(List<User> users) {
        List<UserResponse> responseList = new ArrayList<>();
        for (User user : users) {
            responseList.add(toResponse(user));
        }
        return responseList;
    }
}
