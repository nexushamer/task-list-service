package com.zagalabs.tasklist;

import com.zagalabs.tasklist.data.repositories.UsersRepository;
import com.zagalabs.tasklist.data.repositories.UsersRepositoryImpl;
import com.zagalabs.tasklist.utils.JWTGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ApplicationConfiguration {
    @Value("classpath:users.json")
    private Resource resourceFile;

    @Bean
    public JWTGenerator getJWTGenerator(){
        return new JWTGenerator();
    }

    @Bean
    public UsersRepository getUserRepository(){
        UsersRepository usersRepository = new UsersRepositoryImpl();
        ((UsersRepositoryImpl) usersRepository).loadDataFromFile(resourceFile);

        return usersRepository;
    }

}
