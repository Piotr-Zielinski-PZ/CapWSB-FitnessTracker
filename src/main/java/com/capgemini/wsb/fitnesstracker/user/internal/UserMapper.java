package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;


@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    UserIdDto toIdsDto(User user) {
        return new UserIdDto(
                user.getId(),
                user.getEmail()
        );
    }

    public User toUpdatedEntity(User user, UserUpdateDto updateDto) {
    User updatedUser = new User(
            updateDto.firstName() != null ? updateDto.firstName() : user.getFirstName(),
            updateDto.lastName() != null ? updateDto.lastName() : user.getLastName(),
            updateDto.birthdate() != null ? updateDto.birthdate() : user.getBirthdate(),
            updateDto.email() != null ? updateDto.email() : user.getEmail()
    );
    updatedUser.setId(user.getId());
    return updatedUser;
}

}
