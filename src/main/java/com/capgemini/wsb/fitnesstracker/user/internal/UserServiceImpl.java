package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * LAB1: Create new user
     * @param user user
     * @return created user
     */
    @Override
    public User createUser(final User user) {
        log.info("New user is creating... {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * get user by id
     * @param userId user id
     * @return user
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * get specified user by email
     * @param email user email
     * @return user
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * get all users
     * @return users list
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * LAB1: get user by their email
     * @param email user email
     * @return user list
     */
    @Override
    public List<User> getUserByTheirEmail(String email) {
        return userRepository.getUserByTheirEmail(email);
    }

    /**
     * LAB1: get user by their bithdate
     * @param bithdate user bithdate
     * @return user list
     */
    @Override
    public List<User> getUserByTheirBithdate(@JsonFormat(pattern = "yyyy-MM-dd") LocalDate bithdate) {
        return userRepository.getUserByTheirBirthday(bithdate);
    }

    /**
     * LAB1: Get users born later than
     * @param time maximum birthdate.
     * @return users list
     */
    @Override
    public List<User> getUsersBornLaterThan(LocalDate time) {
        return userRepository.getUsersBornLaterThan(time);
    }


    /**
     * LAB1: Delete exisiting user
     * @param id user id
     */
    @Override
    public void deleteExisitngUserById(Long id) {
        log.info("User is deleting... {}", id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    /**
     * LAB1: Update existing user
     * @param id user id
     * @param userDto user
     * @return updated user
     */
    @Override
    public User updateExistingUser(Long id, UserDto userDto) {
        log.info("Existing user is updating... {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        User updatedUser = userMapper.updateEntity(user, userDto);

        return userRepository.save(updatedUser);
    }
}