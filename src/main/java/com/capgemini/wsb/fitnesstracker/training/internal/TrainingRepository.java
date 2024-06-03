package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * LAB2: Get all trainings.
     * @return list that contains all trainings
     */
    default List<Training> getAllTrainings() {
        return findAll().stream().toList();
    }

    /**
     * LAB2: Get all trainings that belong to specific user, found by id
     * @param userId user is
     * @return list that contains the list of trainings that belongs to selected user
     */
    default List<Training> getAllTrainingsForUserById(long userId) {
        return findAll().stream()
                .filter(training -> training.getUser().getId() == userId)
                .toList();
    }

    /**
     * LAB2: Get all trainings when some activity was done
     * @param activityType activity type
     * @return list that contains the trainings when specific activity was done
     */
    default List<Training> getAllTrainingsByActivityType(ActivityType activityType) {
        return findAll().stream()
                .filter(training -> training.getActivityType() == activityType)
                .toList();
    }

    /**
     * LAB2: Get all trainings that were finished after selected date
     * @param afterTime time deadline
     * @return list  that contains finished trainings
     */
    default List<Training> getAllTrainingsThatAreFinished(Date afterTime) {
        return findAll().stream()
                .filter(training -> training.getEndTime().compareTo(afterTime) > 0)
                .toList();
    }
}
