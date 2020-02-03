package com.zagalabs.tasklist.data.repositories;

import com.zagalabs.tasklist.data.entities.TaskEntity;

import java.util.List;
import java.util.UUID;

public interface TaskRepository {
    TaskEntity createTask(String userId ,TaskEntity task);
    List<TaskEntity> retrieveTasksByUser(String userId);
    TaskEntity updateTaskState(String userId, UUID id, int state);
}
