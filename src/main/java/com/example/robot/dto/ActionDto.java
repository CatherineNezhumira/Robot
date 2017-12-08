package com.example.robot.dto;

import com.example.robot.engine.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActionDto {
    @NotNull(message = "Action can be only [MOVE, LEFT, RIGHT]")
    private Action action;
}
