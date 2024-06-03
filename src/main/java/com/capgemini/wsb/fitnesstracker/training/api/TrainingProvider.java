package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * EXAMPLE: Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * LAB2: Get all trainings.
     * @return list that contains all trainings
     */
    List<Training> getAllAvailableTrainings();

    /**
     * LAB2: Get all trainings that belong to specific user, found by id
     * @param userId user is
     * @return list that contains the list of trainings that belongs to selected user
     */
    List<Training> getAllTrainingsByUserId(long userId);

    /**
     * LAB2: Get all trainings when some activity was done
     * @param activityType activity type
     * @return list that contains the trainings when specific activity was done
     */
    List<Training> getAllTrainingsByActivityType(ActivityType activityType);

    /**
     * LAB2: Get all trainings that were finished after selected date
     * @param afterTime time deadline
     * @return list  that contains finished trainings
     */
    List<Training> getAllTrainingsThatAreFinished(Date afterTime);

    /**
     * LAB2: Creates new training
     * @param training training that will be created
     * @return created new training
     */
    Training createNewTraining(TrainingDTO training);

    /**
     * LAB2: Updates existing training
     * @param trainingId existing training id
     * @param trainingDto training to be updated
     * @return updated existing training
     */
    Training updateExistingTraining(TrainingDTO trainingDto, long trainingId);

}
