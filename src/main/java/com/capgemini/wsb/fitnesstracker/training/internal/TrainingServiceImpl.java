package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> findTrainingsByUser(Long userId) {
        return trainingRepository.findTrainingsByUser(userId);
    }

    @Override
    public List<Training> findCompletedTrainingsAfter(LocalDate date) {
        return trainingRepository.findCompletedTrainingsAfter(date);
    }

    @Override
    public List<Training> findTrainingsByActivity(ActivityType activityType) {
        return trainingRepository.findTrainingsByActivity(activityType);
    }

    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long id, Training training) {
        if (!trainingRepository.existsById(id)) {
            throw new TrainingNotFoundException(id);
        }
        return trainingRepository.save(training);
    }
}
