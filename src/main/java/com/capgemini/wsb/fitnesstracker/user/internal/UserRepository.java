package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * User repository which extends JpaRepository. This repository is managed by Spring Framework and is used for operations
 * on {@link User} user entity.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * LAB1: Get user by email
     * @param email email
     * @return user(s) list
     */
    default List<User> getUserByTheirEmail(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .toList();
    }

    /**
     * LAB1: Get user by birthday
     * @param birthday birthday
     * @return user(s) list
     */
    default List<User> getUserByTheirBirthday(LocalDate birthday) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getBirthdate(),birthday))
                .toList();
    }

    /**
     * LAB1: Get users were born later than (time)
     * @param time maximum birthdate.
     * @return users list
     */
    default List<User> getUsersBornLaterThan(LocalDate time) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(time))
                .toList();
    }
}
