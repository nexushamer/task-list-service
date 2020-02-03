package com.zagalabs.tasklist.utils;

public class ConstantMessages {
    private ConstantMessages(){}

    public static final String USER_ID_IS_REQUIRED = "The user id is required";
    public static final String PASSW_IS_REQUIRED = "The password id is required";
    public static final String AUTH_INVALID_DATA = "The password or user id can not be empty";
    public static final String TASK_IS_REQUIRED = "The task can not be empty";
    public static final String TASK_CREATED_SUCCESSFUL = "The task was created successful";
    public static final String TASK_CREATED_FAILED = "There is an error with the creation of the task";
    public static final String TASK_UPDATED_SUCCESSFUL = "The task was created successful";
    public static final String TASK_UPDATED_FAILED = "There is an error with the creation of the task";
    public static final String RECORD_NOT_FOUND = "The record that you are trying to search does not exist";
    public static final String AUTH_INVALID_USER_ID_OR_PASSW = "The user id or password are not correct";
    public static final String DATE_OF_COMPLETION_IS_INVALID = "The estimated date of completion is invalid, check the format yyyy/MM/dd HH:mm:ss";
    public static final String UNAUTHORIZED_REQUEST = "Currently you do not have an active session, request a token with your credentials";
    public static final String UNAUTHORIZED_EXPIRED_TOKEN = "The token expired, request a token with your credentials";

}
