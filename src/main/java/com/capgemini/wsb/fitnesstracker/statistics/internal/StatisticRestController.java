package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticDTO;
import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
class StatisticRestController {

    private final StatisticsServiceImpl statisticsService;
    private final StatisticMapper statisticMapper;

    /**
     * LAB2: Updates existing training
     * @param user user
     * @param statisticDTO training to be updated
     * @return updated existing training
     */
    @PutMapping("/{User}")
    public Statistics updateExistingStatisticByUser(@RequestBody StatisticDTO statisticDTO, @PathVariable User user) {
        return statisticsService.updateExistingStatisticByUser(statisticDTO, user);
    }

}
