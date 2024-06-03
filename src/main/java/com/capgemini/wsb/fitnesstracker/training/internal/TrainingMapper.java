package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import org.springframework.stereotype.Component;

@Component
class TrainingMapper {

    /**
     * Maps {@link Training} -> {@link TrainingDTO}
     * @param training training
     * @return trainingDTO
     */
    TrainingDTO todto(Training training) {
        return new TrainingDTO(training.getId(),
                todto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

    /**
     * Maps {@link TrainingDTO} -> {@link Training}
     * @param trainingDto training
     * @param user user
     * @return training
     */
    Training toEntity(com.capgemini.wsb.fitnesstracker.training.api.TrainingDTO trainingDto, User user) {
        return new Training(user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed());
    }

    /**
     * Maps {@link TrainingDTO} -> {@link Training}
     * @param training training
     * @param trainingDto trainingDto
     * @return updated training
     */
    Training updateEntity(Training training, com.capgemini.wsb.fitnesstracker.training.api.TrainingDTO trainingDto) {
        training.setStartTime(trainingDto.getStartTime());
        training.setEndTime(trainingDto.getEndTime());
        training.setActivityType(trainingDto.getActivityType());
        training.setDistance(trainingDto.getDistance());
        training.setAverageSpeed(trainingDto.getAverageSpeed());
        return training;
    }

    private UserDto todto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }
}
