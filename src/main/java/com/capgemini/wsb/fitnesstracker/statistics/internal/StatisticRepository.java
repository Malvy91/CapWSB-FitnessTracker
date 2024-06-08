package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

interface StatisticRepository extends JpaRepository<Statistics, Long> {

    /**
     * FInd all statistics belongs to specific user, found by id
     * @param user user is
     * @return list that contains the list of statistics that belongs to selected user
     */
    default List<Statistics> getAllStatisticsByUser(User user) {
        return findAll().stream()
                .filter(statistics -> statistics.getUser().equals(user))
                .toList();
    }

}
