package com.zagalabs.tasklist.models;

import com.zagalabs.tasklist.utils.validations.DateFormatValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    private UUID id;

    private String state;

    @NotBlank
    private String description;

    @DateFormatValidator
    private String estimatedDateOfCompletion;
}
