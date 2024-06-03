package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for querying {@link User} entities through the API.
 */
public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link List} containing all users
     */
    List<User> findAllUsers();

    /**
     * LAB1: get user by email
     * @param email user email
     * @return users lists
     * */
    List<User> getUserByTheirEmail(String email);

    /**
     * LAB1: get user by bithdate
     * @param bithdate user bithdate
     * @return users lists
     * */
    List<User> getUserByTheirBithdate(LocalDate bithdate);

    /**
     * LAB1: Get users born later than "time"
     * @param time limit of birthdate.
     * @return users list
     */
    List<User> getUsersBornLaterThan(LocalDate time);
}
