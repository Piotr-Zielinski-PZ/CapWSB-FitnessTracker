package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;


import java.time.LocalDate;
import java.util.List;

public interface TrainingProvider {

    List<Training> findAllTrainings();

    List<Training> findTrainingsByUser(Long userId);

    List<Training> findCompletedTrainingsAfter(LocalDate date);

    List<Training> findTrainingsByActivity(ActivityType activityType);

    Training createTraining(Training training);

    Training updateTraining(Long id, Training training);

}
