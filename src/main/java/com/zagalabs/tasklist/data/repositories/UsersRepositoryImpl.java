package com.zagalabs.tasklist.data.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zagalabs.tasklist.data.entities.UserEntity;
import com.zagalabs.tasklist.data.entities.UsersList;
import com.zagalabs.tasklist.exceptions.InvalidDataException;
import com.zagalabs.tasklist.exceptions.RecordNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.zagalabs.tasklist.utils.ConstantMessages.RECORD_NOT_FOUND;
import static com.zagalabs.tasklist.utils.ConstantMessages.USER_ID_IS_REQUIRED;

public class UsersRepositoryImpl implements UsersRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersRepositoryImpl.class);
    private final Map<String, UserEntity> users;

    public UsersRepositoryImpl() {
        users = new HashMap<>();
    }

    @Override
    public Optional<UserEntity> retrieveUserById(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new InvalidDataException(USER_ID_IS_REQUIRED);
        }

        if (users.containsKey(userId)) {
            return Optional.of(users.get(userId));
        } else
            throw new RecordNotFoundException(RECORD_NOT_FOUND);
    }

    @Override
    public boolean userExists(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new InvalidDataException(USER_ID_IS_REQUIRED);
        }

        return users.containsKey(userId);
    }

    public void loadDataFromFile(final Resource resourceFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UsersList userList = objectMapper.readValue(resourceFile.getInputStream(), UsersList.class);
            userList.getUsers().forEach(user -> users.put(user.getUserId(), user));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
