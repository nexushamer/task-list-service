package com.zagalabs.tasklist.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import static com.zagalabs.tasklist.utils.ConstantMessages.PASSW_IS_REQUIRED;
import static com.zagalabs.tasklist.utils.ConstantMessages.USER_ID_IS_REQUIRED;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginUserRequest {
    @NotBlank(message = USER_ID_IS_REQUIRED)
    private String userId;
    @NotBlank(message = PASSW_IS_REQUIRED)
    private String password;
}
