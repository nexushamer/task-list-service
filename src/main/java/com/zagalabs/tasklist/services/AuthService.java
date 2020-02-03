package com.zagalabs.tasklist.services;

public interface AuthService {
    String validateAndCreateSessionForUser(String userId, String password);
}
