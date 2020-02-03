package com.zagalabs.tasklist.data.repositories;

import com.zagalabs.tasklist.data.entities.UserEntity;

import java.util.Optional;

public interface UsersRepository {
    Optional<UserEntity> retrieveUserById(String userId);
    boolean userExists(String userId);
}
