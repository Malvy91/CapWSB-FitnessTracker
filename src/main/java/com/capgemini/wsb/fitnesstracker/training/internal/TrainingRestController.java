package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingRestController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * LAB2: Get all trainings.
     * @return list that contains all trainings
     */
    @GetMapping
    public List<TrainingDTO> getAllTrainings() {
        return trainingService.getAllAvailableTrainings()
                .stream()
                .map(trainingMapper::todto)
                .toList();
    }

    /**
     * LAB2: Get all trainings that belong to specific user, found by id
     * @param userId user is
     * @return list that contains the list of trainings that belongs to selected user
     */
    @GetMapping("/{userId}")
    public List<TrainingDTO> getAllTrainingsForUserById(@PathVariable long userId) {
        return trainingService.getAllTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::todto)
                .toList();
    }

    /**
     * LAB2: Get all trainings when some activity was done
     * @param activityType activity type
     * @return list that contains the trainings when specific activity was done
     */
    @GetMapping("/activityType")
    public List<TrainingDTO> getAllTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.getAllTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::todto)
                .toList();
    }

    /**
     * LAB2: Get all trainings that were finished after selected date
     * @param afterTime time deadline
     * @return list  that contains finished trainings
     */
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDTO> getAllTrainingsThatAreFinished(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        return trainingService.getAllTrainingsThatAreFinished(afterTime)
                .stream()
                .map(trainingMapper::todto)
                .toList();
    }

    /**
     * LAB2: Creates new training
     * @param trainingDto training that will be created
     * @return created new training
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Training createNewTraining(@RequestBody com.capgemini.wsb.fitnesstracker.training.api.TrainingDTO trainingDto) {
        return trainingService.createNewTraining(trainingDto);
    }

    /**
     * LAB2: Updates existing training
     * @param trainingId existing training id
     * @param trainingDto training to be updated
     * @return updated existing training
     */
    @PutMapping("/{trainingId}")
    public Training updateExistingTraining(@RequestBody com.capgemini.wsb.fitnesstracker.training.api.TrainingDTO trainingDto, @PathVariable long trainingId) {
        return trainingService.updateExistingTraining(trainingDto, trainingId);
    }

    /**
     * LAST_CHANCE: Show trainings that are longer than X km(s)
     * @param distance training distance
     * @return training list
     */
    @GetMapping("/finished/{distance}")
    public List<TrainingDTO>getAllTrainingsAboveXKM(double distance) {
        return trainingService.getAllTrainingsEqualXKM(distance)
                .stream()
                .map(trainingMapper::todto)
                .toList();
    }

}
