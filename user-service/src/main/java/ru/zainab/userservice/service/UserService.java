package ru.zainab.userservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zainab.userservice.dto.ChangePasswordRequest;
import ru.zainab.userservice.dto.UserCreateRequest;
import ru.zainab.userservice.dto.UserResponse;
import ru.zainab.userservice.dto.UserUpdateRequest;
import ru.zainab.userservice.entity.User;
import ru.zainab.userservice.entity.UserRole;
import ru.zainab.userservice.exception.AccessDeniedException;
import ru.zainab.userservice.exception.InvalidPasswordException;
import ru.zainab.userservice.exception.UserAlreadyExistsException;
import ru.zainab.userservice.exception.UserNotFoundException;
import ru.zainab.userservice.mapper.UserMapper;
import ru.zainab.userservice.repository.UserRepository;

import java.util.List;
import java.util.Locale;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final PhoneNumberService phoneNumberService;

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase(Locale.ROOT);
        userRepository.findByEmail(normalizedEmail).ifPresent(user -> {
            throw new UserAlreadyExistsException("User с таким email существует!");
        });
        String encodedPassword = passwordEncoder.encode(request.password());
        String normalizedNumber = phoneNumberService.normalizePhoneNumber(request.phone());
        User user = userMapper.toEntity(request, normalizedEmail, normalizedNumber, encodedPassword);
        User userSaved = userRepository.save(user);
        return userMapper.toResponse(userSaved);

    }

    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User targetUser = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
        checkUserAccess(currentUser, targetUser);
        String normalizedEmail = request.email().trim().toLowerCase(Locale.ROOT);
        if (!targetUser.getEmail().equals(normalizedEmail)) {
            userRepository.findByEmail(normalizedEmail).ifPresent(userSaved -> {
                throw new UserAlreadyExistsException("Email занят!");
            });
        }
        targetUser.setName(request.name());
        targetUser.setEmail(normalizedEmail);
        targetUser.setPhone(phoneNumberService.normalizePhoneNumber(request.phone()));
        return userMapper.toResponse(targetUser);
    }

    private void checkUserAccess(User currentUser, User targetUser ) {
        if (currentUser.getRole() == UserRole.USER
                && !currentUser.getId().equals(targetUser.getId())) {
            throw new AccessDeniedException("Не соответствует id! ");
        }
    }

    @Transactional
    public void changePassword(ChangePasswordRequest passwordRequest) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found!"));
        if (!passwordEncoder.matches(
                passwordRequest.currentPassword(),
                user.getPassword()
        )) {
            throw new InvalidPasswordException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(passwordRequest.newPassword()));

    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        User target = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
        checkUserAccess(currentUser, target);

        return userMapper.toResponse(target);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserByName(String name) {
        User user = userRepository.findByName(name).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
        return userMapper.toResponse(user);
    }

}
