package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticDTO;
import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class StatisticsServiceImpl implements StatisticsProvider {

    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;
    private final UserProvider userProvider;

    @Override
    public Optional<Statistics> getStatistics(Long statisticsId) {
        return Optional.empty();
    }

    @Override
    public Statistics updateExistingStatisticByUser(StatisticDTO statisticDTO, User user) {
        log.info("Existing statistic is updating... {}", user);
        Statistics statistics = (Statistics) statisticRepository.getAllStatisticsByUser(user);

        Statistics updatedStatistic = StatisticMapper.updateEntity(statistics, statisticDTO);

        return statisticRepository.save(updatedStatistic);
    }
}
