package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    /**
     * Maps {@link User} ->  {@link UserDto}
     * @param user user
     * @return user dto
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Maps {@link UserDto} -> {@link User}
     *
     * @param userDto userDto
     * @return user
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    /**
     * Maps {@link User} -> {@link UseInfoListDto}
     * @param user user
     * @return user with specified data
     */
    UseInfoListDto toUserInfoListDto(User user) {
        return new UseInfoListDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    /**
     * Maps {@link User} -> {@link UserEmailInfoDto}
     * @param user user
     * @return user info: id and email
     */
    UserEmailInfoDto toUserIdEmailDto(User user) {
        return new UserEmailInfoDto(user.getId(), user.getEmail());
    }

    /**
     * Maps {@link User} -> {@link UserBirthdayInfoDto}
     * @param user user
     * @return user info: id and email
     */
    UserBirthdayInfoDto toUserIdBirthdayDto(User user) {
        return new UserBirthdayInfoDto(user.getId(), user.getBirthdate());
    }

    /**
     * Maps {@link UserDto} -> {@link User}
     * @param user user
     * @param userDto userDto
     * @return updated user
     */
    User updateEntity(User user, com.capgemini.wsb.fitnesstracker.user.api.UserDto userDto) {
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setBirthdate(userDto.birthdate());
        user.setEmail(userDto.email());
        return user;
    }
}
