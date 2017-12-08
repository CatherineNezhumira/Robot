package com.example.robot.robot;

import com.example.robot.dto.PositionDto;
import com.example.robot.engine.Direction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RobotPosition {
    private Point point;
    private Direction direction;

    public RobotPosition(PositionDto positionDto) {
        point = positionDto.getPoint();
        direction = positionDto.getDirection();
    }

    public RobotPosition forwardPosition() {
        Point nextPoint = direction.getForwardPoint(point);
        return new RobotPosition(nextPoint, direction);
    }

    public RobotPosition turnLeft() {
        return new RobotPosition(point, direction.getLeft());
    }

    public RobotPosition turnRight() {
        return new RobotPosition(point, direction.getRight());
    }

    @Override
    public String toString() {
        return String.valueOf(point.x) + ", " + point.y + ", " + direction;
    }
}
