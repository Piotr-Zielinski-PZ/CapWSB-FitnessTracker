package com.capgemini.wsb.fitnesstracker.training.internal;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;


import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

interface TrainingRepository extends JpaRepository<Training, Long> {

    default List<Training> findTrainingsByUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        return findAll().stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .toList();
    }

    default List<Training> findCompletedTrainingsAfter(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null");
        }
        Date dateAsDate = java.sql.Date.valueOf(date);
        return findAll().stream()
                .filter(training -> training.getEndTime().after(dateAsDate))
                .toList();
    }

    default List<Training> findTrainingsByActivity(ActivityType activityType) {
        if (activityType == null) {
            throw new IllegalArgumentException("Activity type must not be null");
        }
        return findAll().stream()
                .filter(training -> training.getActivityType().equals(activityType))
                .toList();
    }

}
