package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Optional;

public interface TrainingProvider {

    Optional<User> getTraining(Long trainingId);

}
