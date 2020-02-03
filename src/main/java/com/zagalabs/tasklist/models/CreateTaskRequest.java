package com.zagalabs.tasklist.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static com.zagalabs.tasklist.utils.ConstantMessages.USER_ID_IS_REQUIRED;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateTaskRequest {
    @NotBlank(message = USER_ID_IS_REQUIRED)
    private String userId;
    @Valid
    private Task task;
}
