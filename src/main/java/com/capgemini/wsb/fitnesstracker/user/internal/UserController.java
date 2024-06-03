package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * get all users
     * @return list of all found users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * LAB1: Get all users with specified data
     * @return A {@link List} of {@link UseInfoListDto}
     */
    @GetMapping("/simple")
    public List<UseInfoListDto> getAllUsersInfoList() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toUserInfoListDto)
                .toList();
    }

    /**
     * LAB1: Find users data
     * @param id user id
     * @return user data
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * LAB1: Get user by email
     * @param email user email
     * @return user
     */
    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User with EMAIL=%s was not found".formatted(email)));
    }

    /**
     * LAB1: get list of users with specified email
     * @param email user email
     * @return list of users
     */
    @GetMapping("/email")
    public List<UserEmailInfoDto> getUserByTheirEmail(@RequestParam String email) {
        return userService.getUserByTheirEmail(email)
                .stream()
                .map(userMapper::toUserIdEmailDto)
                .toList();
    }

    /**
     * LAB1: get list of users with selected birthday
     * @param birthday user birthday
     * @return list of users
     */
    @GetMapping("/bithdate")
    public List<UserBirthdayInfoDto> getUserByTheirBithdate(@RequestParam @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthday) {
        return userService.getUserByTheirBithdate(birthday)
                .stream()
                .map(userMapper::toUserIdBirthdayDto)
                .toList();
    }

    /**
     * LAB1: get list of users that were born later than "time"
     * @param time maximum birthdate.
     * @return users list
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersBornLaterThan(@PathVariable LocalDate time) {
        return userService.getUsersBornLaterThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * LAB1: delete user by id
     * @param id user id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExistingUserById(@PathVariable Long id) {
        userService.deleteExisitngUserById(id);
    }

    /**
     * LAB1: Create new user
     * @param userDto new user information
     * @return new user
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewUser(@RequestBody UserDto userDto) {
        return userService.createUser(userMapper.toEntity(userDto));
    }

    /**
     * LAB1: update existing user
     * @param id user id
     * @param userDto uder info to update
     * @return updated user
     */
    @PutMapping("/{id}")
    public User updateExistingUser(@PathVariable Long id, @RequestBody com.capgemini.wsb.fitnesstracker.user.api.UserDto userDto) {
        return userService.updateExistingUser(id, userDto);
    }
}