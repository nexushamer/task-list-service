package com.zagalabs.tasklist.services;

import com.zagalabs.tasklist.models.Task;

import java.util.List;

public interface TaskService {
    boolean createTask(String userId, Task task);

    boolean updateTask(String userId, Task task);

    List<Task> retrieveTasksByUserId(String userId);
}
