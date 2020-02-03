package com.zagalabs.tasklist.models;

import com.zagalabs.tasklist.utils.validations.StateValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.UUID;

import static com.zagalabs.tasklist.utils.ConstantMessages.USER_ID_IS_REQUIRED;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateTaskRequest {
    @NotBlank(message = USER_ID_IS_REQUIRED)
    private String userId;

    @NotNull
    private UUID id;

    @StateValidator
    private String state;
}
