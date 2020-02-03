package com.zagalabs.tasklist.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskEntity {
    private UUID id;
    private int state;
    private String description;
    private Date estimatedDateOfCompletion;
    private Date modificationDate;
}
