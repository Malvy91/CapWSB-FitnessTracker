package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * User that contains birthday and id
 * @param id user id
 * @param birthday user birthday
 */
record UserBirthdayInfoDto(Long id, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthday) {
}
