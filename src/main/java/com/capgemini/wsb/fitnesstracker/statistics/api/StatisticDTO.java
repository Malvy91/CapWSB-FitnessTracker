package com.capgemini.wsb.fitnesstracker.statistics.api;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StatisticDTO {
    @Nullable
    private final Long id;
    private final User user;
    private final int totalTrainings;
    private final double totalDistance;
    private final int totalCaloriesBurned;

}
