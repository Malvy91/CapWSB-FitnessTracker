package com.capgemini.wsb.fitnesstracker.statistics.internal;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticDTO;
import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import org.springframework.stereotype.Component;

@Component
class StatisticMapper {

    /**
     * Maps {@link com.capgemini.wsb.fitnesstracker.statistics.api.Statistics} -> {@link StatisticDTO}
     * @param statictics statistics
     * @return StatisticDTO
     */
    StatisticDTO todto(Statistics statictics) {
        return new StatisticDTO(statictics.getId(),
                todto(statictics.getUser()),
                statictics.getTotalTrainings(),
                statictics.getTotalCaloriesBurned(),
                statictics.getTotalDistance());
    }

    /**
     * Maps {@link StatisticDTO} -> {@link Statistics}
     * @param statisticDTO training
     * @param user user
     * @return statistics
     */
    Statistics toEntity(StatisticDTO statisticDTO, User user) {
        return new Statistics(user,
                statisticDTO.getId(),
                statisticDTO.getUser(),
                statisticDTO.getTotalTrainings(),
                statisticDTO.getTotalDistance(),
                statisticDTO.getTotalCaloriesBurned());
    }

    /**
     * Maps {@link StatisticDTO} -> {@link Statistics}
     * @param statistics Statistics
     * @param statisticDTO StatisticDTO
     * @return update statistics
     */
    static Statistics updateEntity(Statistics statistics, StatisticDTO statisticDTO) {
        statistics.setTotalDistance(statisticDTO.getTotalDistance());
        statistics.setTotalTrainings(statisticDTO.getTotalTrainings());
        statistics.setTotalCaloriesBurned(statisticDTO.getTotalCaloriesBurned());
        return statistics;
    }

    private UserDto todto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }
}