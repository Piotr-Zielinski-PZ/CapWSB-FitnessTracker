package com.capgemini.wsb.fitnesstracker.user.api;


public interface UserService {

    User createUser(User user);

    void deleteUser(Long userId);

    User updateUser(User user);
}
