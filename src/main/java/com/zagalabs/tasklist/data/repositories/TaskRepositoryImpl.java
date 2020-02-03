package com.zagalabs.tasklist.data.repositories;

import com.zagalabs.tasklist.data.entities.TaskEntity;
import com.zagalabs.tasklist.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.zagalabs.tasklist.utils.ConstantMessages.RECORD_NOT_FOUND;

@Component
public class TaskRepositoryImpl implements TaskRepository {
    private final Map<String, List<TaskEntity>> tasks;
    private final UsersRepository usersRepository;

    public TaskRepositoryImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        tasks = new HashMap<>();
    }

    @Override
    public TaskEntity createTask(String userId, TaskEntity task) {
        if (usersRepository.userExists(userId)) {
            task.setId(UUID.randomUUID());
            List<TaskEntity> taskList = tasks.get(userId);
            if (taskList == null) {
                taskList = new ArrayList<>();
                taskList.add(task);

                tasks.put(userId, taskList);
            } else {
                taskList.add(task);
            }

            return task;
        } else
            throw new RecordNotFoundException(RECORD_NOT_FOUND);
    }

    @Override
    public List<TaskEntity> retrieveTasksByUser(String userId) {
        if (usersRepository.userExists(userId)) {
            return tasks
                    .get(userId);
        } else
            throw new RecordNotFoundException(RECORD_NOT_FOUND);
    }

    @Override
    public TaskEntity updateTaskState(String userId, UUID id, int state) {
        if (usersRepository.userExists(userId)) {
            List<TaskEntity> taskList = tasks.get(userId);

            Optional<TaskEntity> optionalTaskEntity = taskList
                    .stream()
                    .filter(task -> task.getId().equals(id))
                    .findFirst();

            if (optionalTaskEntity.isPresent()) {
                TaskEntity objectToUpdate = optionalTaskEntity.get();
                taskList.remove(objectToUpdate);
                objectToUpdate.setState(state);
                taskList.add(objectToUpdate);
                tasks.put(userId, taskList);

                return objectToUpdate;
            }

            return null;
        } else
            throw new RecordNotFoundException(RECORD_NOT_FOUND);
    }
}
