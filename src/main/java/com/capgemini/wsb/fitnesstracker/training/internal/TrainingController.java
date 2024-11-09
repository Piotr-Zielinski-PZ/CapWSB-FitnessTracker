package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
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

    @GetMapping("/simple")
    public List<Training> getAllTrainings() {
        return trainingService.findAllTrainings();
    }

    @GetMapping("/user/{userId}")
    public List<Training> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.findTrainingsByUser(userId);
    }

    @GetMapping("/completed")
    public List<Training> getCompletedTrainings(@RequestParam LocalDate date) {
        return trainingService.findCompletedTrainingsAfter(date);
    }

    @GetMapping("/activity/{activityType}")
    public List<Training> getTrainingsByActivity(@PathVariable ActivityType activityType) {
        return trainingService.findTrainingsByActivity(activityType);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Training createTraining(@RequestBody Training training) {
        return trainingService.createTraining(training);
    }

    @PutMapping("/{id}")
    public Training updateTraining(@PathVariable Long id, @RequestBody Training training) {
        return trainingService.updateTraining(id, training);
    }
}