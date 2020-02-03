package com.zagalabs.tasklist.data.repositories;

import com.zagalabs.tasklist.exceptions.InvalidDataException;
import com.zagalabs.tasklist.exceptions.RecordNotFoundException;
import org.junit.Test;

public class UsersRepositoryImplTest {

    @Test(expected = InvalidDataException.class)
    public void retrieveUserWhenUserIdIsNull() {
        UsersRepositoryImpl usersRepository = new UsersRepositoryImpl();
        usersRepository.retrieveUserById(null);
    }

    @Test(expected = InvalidDataException.class)
    public void callingUserExistsWhenUserIdIsNull() {
        UsersRepositoryImpl usersRepository = new UsersRepositoryImpl();
        usersRepository.retrieveUserById(null);
    }

    @Test(expected = RecordNotFoundException.class)
    public void retrieveUserWhenUserDoesNotExists() {
        UsersRepositoryImpl usersRepository = new UsersRepositoryImpl();
        usersRepository.retrieveUserById("12346");
    }

}