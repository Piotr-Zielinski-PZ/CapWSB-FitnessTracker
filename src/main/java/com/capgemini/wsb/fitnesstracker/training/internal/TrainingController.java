package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;
    private final UserProvider userProvider;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getAllTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.findAllTrainingsByUserId(userId).stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getTrainingsFinishedAfter(@PathVariable LocalDate afterTime) {
        return trainingService.findTrainingsAfter(afterTime).stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.findTrainingsByActivityType(activityType).stream().map(trainingMapper::toDto).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Training createTraining(@RequestBody NewTrainingDto newTrainingDto) {
        User user = userProvider.getUser(newTrainingDto.userId()).orElseThrow(() -> new IllegalArgumentException("User with ID: " + newTrainingDto.userId() + " not found"));
        return trainingService.createTraining(trainingMapper.toNewEntity(newTrainingDto, user));
    }

    @PutMapping("/{trainingId}")
    public Training updateTraining(@PathVariable Long trainingId, @RequestBody NewTrainingDto newTrainingDto) {
        Training training = trainingService.getTraining(trainingId).orElseThrow(() -> new IllegalArgumentException("Training with ID: " + trainingId + " not found"));
        Training updatedTraining = trainingMapper.toUpdateEntity(newTrainingDto, training);
        return trainingService.updateTraining(updatedTraining);
    }

}