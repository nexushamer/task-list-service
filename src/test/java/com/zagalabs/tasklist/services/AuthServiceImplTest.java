package com.zagalabs.tasklist.services;

import com.zagalabs.tasklist.data.entities.UserEntity;
import com.zagalabs.tasklist.data.repositories.UsersRepository;
import com.zagalabs.tasklist.exceptions.InvalidDataException;
import com.zagalabs.tasklist.utils.JWTGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class AuthServiceImplTest {

    @Test(expected = InvalidDataException.class)
    public void whenCallingValidateAndCreateSessionForUserAndUserIsNullorPasswordIsNull(){
        UsersRepository usersRepository = Mockito.mock(UsersRepository.class);
        JWTGenerator tokenGenerator = Mockito.mock(JWTGenerator.class);

        AuthServiceImpl authService = new AuthServiceImpl(usersRepository, tokenGenerator);
        authService.validateAndCreateSessionForUser(null, null);
    }

    @Test(expected = InvalidDataException.class)
    public void whenCallingValidateAndCreateSessionForUserThePasswordIsInvalid(){
        UsersRepository usersRepository = Mockito.mock(UsersRepository.class);
        JWTGenerator tokenGenerator = Mockito.mock(JWTGenerator.class);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("user@gmail.com");
        userEntity.setPassword("$2a$11$/ZzigPFQPSEZThJGh.ctruMghO0gg0DaAH7yzqfTjyoMkWGCx0mgW");

        Mockito.when(usersRepository.retrieveUserById(Mockito.anyString())).thenReturn(Optional.of(userEntity));

        AuthServiceImpl authService = new AuthServiceImpl(usersRepository, tokenGenerator);
        authService.validateAndCreateSessionForUser("user@gmail.com", "123456");
    }

    @Test(expected = InvalidDataException.class)
    public void whenCallingValidateAndCreateSessionForUserAndAllDataIsValid(){
        UsersRepository usersRepository = Mockito.mock(UsersRepository.class);
        JWTGenerator tokenGenerator = Mockito.mock(JWTGenerator.class);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("usuario2@gmail.com");
        userEntity.setPassword("$2a$11$/ZzigPFQPSEZThJGh.ctruMghO0gg0DaAH7yzqfTjyoMkWGCx0mgW");

        Mockito.when(usersRepository.retrieveUserById(Mockito.anyString())).thenReturn(Optional.of(userEntity));

        AuthServiceImpl authService = new AuthServiceImpl(usersRepository, tokenGenerator);
        final String token = authService.validateAndCreateSessionForUser("usuario2@gmail.com", "123456");
        Assert.assertNotNull(token);
    }

}