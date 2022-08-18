package com.alzakhar.user_service.dto;

import com.alzakhar.user_service.model.UserStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ChangeStatusDto implements Serializable {

    @NotNull
    private Long userId;
    private UserStatus oldStatus;
    @NotNull
    private UserStatus newStatus;
}
