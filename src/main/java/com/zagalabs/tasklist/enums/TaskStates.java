package com.zagalabs.tasklist.enums;

public enum TaskStates {
    PENDING(1, "PENDING"),
    DONE(2, "DONE"),
    INVALID(0, "INVALID");

    private int state;
    private String description;

    TaskStates(int state, String description) {
        this.state = state;
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public static TaskStates stateToTaskState(int state) {
        if(state == TaskStates.PENDING.getState())
            return TaskStates.PENDING;
        else if (state == TaskStates.DONE.getState())
            return TaskStates.DONE;
        else
            return TaskStates.INVALID;
    }

    public static TaskStates stateDescriptionToTaskState(String description) {
        if(description.equals(TaskStates.PENDING.getDescription()))
            return TaskStates.PENDING;
        else if (description.equals(TaskStates.DONE.getDescription()))
            return TaskStates.DONE;
        else
            return TaskStates.INVALID;
    }
}
