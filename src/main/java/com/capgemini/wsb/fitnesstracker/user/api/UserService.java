package com.capgemini.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * LAB1: Create user
     * @param user user
     */
    User createUser(User user);

    /**
     * LAB1: Delete user
     * @param id user id
     */
    void deleteExisitngUserById(Long id);

    /**
     * LAB1: update user information
     * @param id user id
     * @param userDto user to be updated
     * @return updated user
     */
    User updateExistingUser(Long id, UserDto userDto);
}
