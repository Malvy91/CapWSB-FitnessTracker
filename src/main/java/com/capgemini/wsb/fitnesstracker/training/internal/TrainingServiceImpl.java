package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingDTO;
import com.capgemini.wsb.fitnesstracker.training.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingProvider {

    private static final Logger log = LoggerFactory.getLogger(TrainingServiceImpl.class);
    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final UserProvider userProvider;

    /**
     * EXAMPLE: Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    /**
     * LAB2: Get all trainings.
     * @return list that contains all trainings
     */
    @Override
    public List<Training> getAllAvailableTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * LAB2: Get all trainings that belong to specific user, found by id
     * @param userId user is
     * @return list that contains the list of trainings that belongs to selected user
     */
    @Override
    public List<Training> getAllTrainingsByUserId(long userId) {
        return trainingRepository.getAllTrainingsForUserById(userId);
    }

    /**
     * LAB2: Get all trainings when some activity was done
     * @param activityType activity type
     * @return list that contains the trainings when specific activity was done
     */
    @Override
    public List<Training> getAllTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.getAllTrainingsByActivityType(activityType);
    }

    /**
     * LAB2: Get all trainings that were finished after selected date
     * @param afterTime time deadline
     * @return list  that contains finished trainings
     */
    @Override
    public List<Training> getAllTrainingsThatAreFinished(Date afterTime) {
        return trainingRepository.getAllTrainingsThatAreFinished(afterTime);
    }

    /**
     * LAB2: Creates new training
     * @param trainingDto training that will be created
     * @return created new training
     */
    @Override
    public Training createNewTraining(TrainingDTO trainingDto) {
        log.info("New training is creating... {}", trainingDto);
        if (trainingDto.getId() != null) {
            throw new IllegalArgumentException("Training has already DB ID, update is not permitted!");
        }
        User user = userProvider.getUser(trainingDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(trainingDto.getUserId()));
        return trainingRepository.save(trainingMapper.toEntity(trainingDto, user));
    }

    /**
     * LAB2: Updates existing training
     * @param trainingId existing training id
     * @param trainingDto training to be updated
     * @return updated existing training
     */
    @Override
    public Training updateExistingTraining(TrainingDTO trainingDto, long trainingId) {
        log.info("Existing training is updating... {}", trainingId);
        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new TrainingNotFoundException(trainingId));

        Training updatedUser = trainingMapper.updateEntity(training, trainingDto);

        return trainingRepository.save(updatedUser);
    }
}
