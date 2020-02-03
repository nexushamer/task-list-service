package com.zagalabs.tasklist.services;

import com.zagalabs.tasklist.data.entities.UserEntity;
import com.zagalabs.tasklist.data.repositories.UsersRepository;
import com.zagalabs.tasklist.exceptions.InvalidDataException;
import com.zagalabs.tasklist.exceptions.RecordNotFoundException;
import com.zagalabs.tasklist.utils.JWTGenerator;
import com.zagalabs.tasklist.utils.PasswordHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.zagalabs.tasklist.utils.ConstantMessages.AUTH_INVALID_DATA;
import static com.zagalabs.tasklist.utils.ConstantMessages.AUTH_INVALID_USER_ID_OR_PASSW;
import static com.zagalabs.tasklist.utils.ConstantMessages.RECORD_NOT_FOUND;

@Service
public class AuthServiceImpl implements AuthService {
    private final UsersRepository usersRepository;
    private final JWTGenerator tokenGenerator;

    @Autowired
    public AuthServiceImpl(UsersRepository usersRepository, JWTGenerator tokenGenerator) {
        this.usersRepository = usersRepository;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public String validateAndCreateSessionForUser(String userId, String password) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(password)) {
            throw new InvalidDataException(AUTH_INVALID_DATA);
        }

        final UserEntity userEntity = usersRepository
                .retrieveUserById(userId)
                .orElseThrow(() -> new RecordNotFoundException(RECORD_NOT_FOUND));

        if (PasswordHandler.isPasswordTheSame(password, userEntity.getPassword())) {
            return tokenGenerator.generateToken(userEntity);
        } else
            throw new InvalidDataException(AUTH_INVALID_USER_ID_OR_PASSW);
    }
}
