package com.zagalabs.tasklist.services;

import com.zagalabs.tasklist.data.entities.TaskEntity;
import com.zagalabs.tasklist.data.repositories.TaskRepository;
import com.zagalabs.tasklist.enums.TaskStates;
import com.zagalabs.tasklist.exceptions.InvalidDataException;
import com.zagalabs.tasklist.models.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.zagalabs.tasklist.utils.ConstantMessages.DATE_OF_COMPLETION_IS_INVALID;
import static com.zagalabs.tasklist.utils.ConstantMessages.TASK_IS_REQUIRED;
import static com.zagalabs.tasklist.utils.ConstantMessages.USER_ID_IS_REQUIRED;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public boolean createTask(String userId, Task task) {
        if (task == null || userId == null) {
            throw new InvalidDataException(TASK_IS_REQUIRED);
        }

        TaskEntity taskEntity = mapModelToEntity(task);

        taskEntity.setState(TaskStates.PENDING.getState());

        TaskEntity objectCreated = taskRepository.createTask(userId, taskEntity);

        return objectCreated != null;
    }

    @Override
    public boolean updateTask(String userId, Task task) {
        if (StringUtils.isBlank(userId)) {
            throw new InvalidDataException(USER_ID_IS_REQUIRED);
        } else if (task == null) {
            throw new InvalidDataException(TASK_IS_REQUIRED);
        }

        TaskStates taskStates = TaskStates.stateDescriptionToTaskState(task.getState());

        TaskEntity objectUpdated = taskRepository.updateTaskState(userId, task.getId(), taskStates.getState());

        return objectUpdated != null;
    }

    @Override
    public List<Task> retrieveTasksByUserId(String userId) {
        if (isBlank(userId)) {
            throw new InvalidDataException(TASK_IS_REQUIRED);
        }

        final List<TaskEntity> taskEntities = taskRepository.retrieveTasksByUser(userId);

        if (taskEntities == null || taskEntities.isEmpty())
            return new ArrayList<>();

        return taskEntities
                .stream()
                .sorted(Comparator.comparing(TaskEntity::getEstimatedDateOfCompletion))
                .map(taskEntity -> this.mapEntityToModel(taskEntity))
                .collect(Collectors.toList());
    }

    private TaskEntity mapModelToEntity(Task task) {
        TaskEntity taskEntity = new TaskEntity();

        taskEntity.setId(UUID.randomUUID());
        taskEntity.setDescription(task.getDescription());

        Date date;
        try {
            date = parseDateStringToDate(task.getEstimatedDateOfCompletion());
        } catch (ParseException e) {
            throw new InvalidDataException(DATE_OF_COMPLETION_IS_INVALID);
        }

        taskEntity.setEstimatedDateOfCompletion(date);
        taskEntity.setModificationDate(new Date());

        return taskEntity;
    }

    private Date parseDateStringToDate(String estimatedDateOfCompletion) throws ParseException {
        final DateFormat formatDate = new SimpleDateFormat(DATE_FORMAT);

        return formatDate.parse(estimatedDateOfCompletion);
    }

    private Task mapEntityToModel(TaskEntity taskEntity) {
        Task task = new Task();

        task.setId(taskEntity.getId());
        task.setDescription(taskEntity.getDescription());

        final TaskStates state = TaskStates.stateToTaskState(taskEntity.getState());
        final String dateString = parseDateToString(taskEntity.getEstimatedDateOfCompletion());
        task.setState(state.getDescription());

        task.setEstimatedDateOfCompletion(dateString);


        return task;
    }

    private String parseDateToString(Date estimatedDateOfCompletion) {
        final DateFormat formatDate = new SimpleDateFormat(DATE_FORMAT);

        return formatDate.format(estimatedDateOfCompletion);
    }
}
