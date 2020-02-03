package com.zagalabs.tasklist.services;

import com.zagalabs.tasklist.data.repositories.TaskRepository;
import com.zagalabs.tasklist.exceptions.InvalidDataException;
import org.junit.Test;
import org.mockito.Mockito;

public class TaskServiceImplTest {

    @Test(expected = InvalidDataException.class)
    public void whenCallingCreateTaskAndUserIdOrTaskIsNull(){
        TaskRepository taskRepository = Mockito.mock(TaskRepository.class);

        TaskServiceImpl taskService = new TaskServiceImpl(taskRepository);
        taskService.createTask(null, null);

    }

    @Test(expected = InvalidDataException.class)
    public void whenCallingUpdateTaskAndUserIdOrTaskIsNull(){
        TaskRepository taskRepository = Mockito.mock(TaskRepository.class);

        TaskServiceImpl taskService = new TaskServiceImpl(taskRepository);
        taskService.updateTask(null, null);

    }

    @Test(expected = InvalidDataException.class)
    public void whenCallingRetrieveTaskByUserIdAndUserIdIsNull(){
        TaskRepository taskRepository = Mockito.mock(TaskRepository.class);

        TaskServiceImpl taskService = new TaskServiceImpl(taskRepository);
        taskService.retrieveTasksByUserId(null);

    }
}