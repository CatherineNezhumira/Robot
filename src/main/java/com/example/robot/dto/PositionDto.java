package com.example.robot.dto;

import com.example.robot.engine.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.awt.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {
    @NotNull
    private Point point;
    @NotNull(message = "Direction can be only [NORTH, EAST, SOUTH, WEST]")
    private Direction direction;


}
