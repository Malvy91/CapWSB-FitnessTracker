package com.capgemini.wsb.fitnesstracker.statistics.api;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import io.micrometer.core.instrument.Statistic;

import java.util.Optional;

public interface StatisticsProvider {

    /**
     * Retrieves a statistics based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param statisticsId id of the statistics to be searched
     * @return An {@link Optional} containing the located Statistics, or {@link Optional#empty()} if not found
     */
    Optional<Statistics> getStatistics(Long statisticsId);

    /**
     * LAST_CHANCE: Updates existing statistics
     * @param user existing user id
     * @param  statisticDTO to be updated
     * @return updated existing training
     */

    Statistics updateExistingStatisticByUser(StatisticDTO statisticDTO, User user);
}
