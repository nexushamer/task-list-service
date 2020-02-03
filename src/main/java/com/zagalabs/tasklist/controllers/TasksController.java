package com.zagalabs.tasklist.controllers;

import com.zagalabs.tasklist.models.CreateTaskRequest;
import com.zagalabs.tasklist.models.Message;
import com.zagalabs.tasklist.models.Task;
import com.zagalabs.tasklist.models.UpdateTaskRequest;
import com.zagalabs.tasklist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static com.zagalabs.tasklist.utils.ConstantMessages.*;

@RestController
@RequestMapping(path = "/task")
@Validated
public class TasksController {
    private final TaskService taskService;

    @Autowired
    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @CrossOrigin
    public @ResponseBody Message createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest) {
        boolean theObjectWasCreatedSuccessful = taskService
                .createTask(createTaskRequest.getUserId(), createTaskRequest.getTask());

        if (theObjectWasCreatedSuccessful)
            return new Message(TASK_CREATED_SUCCESSFUL);
        else
            return new Message(TASK_CREATED_FAILED);
    }

    @GetMapping
    @RequestMapping(path = "/userId/{userId}")
    @CrossOrigin
    public @ResponseBody List<Task> retrieveTasksByUserId(@NotBlank(message = USER_ID_IS_REQUIRED) @PathVariable("userId") String userId) {
        return taskService.retrieveTasksByUserId(userId);
    }

    @PatchMapping
    @CrossOrigin
    public @ResponseBody Message updateStateOfTask(@RequestBody @Valid UpdateTaskRequest updateTaskRequest) {
        final Task task = new Task();
        task.setId(updateTaskRequest.getId());
        task.setState(updateTaskRequest.getState());

        boolean theObjectWasUpdated = taskService.updateTask(updateTaskRequest.getUserId(), task);

        if (theObjectWasUpdated)
            return new Message(TASK_UPDATED_SUCCESSFUL);
        else
            return new Message(TASK_UPDATED_FAILED);
    }
}
