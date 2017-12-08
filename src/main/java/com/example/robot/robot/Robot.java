package com.example.robot.robot;

import com.example.robot.dto.PositionDto;
import lombok.Data;

import java.awt.*;

@Data
public class Robot {

    private PositionDto position;
    private static Robot robot = null;

    public static Robot getRobot() {
        if (robot == null) {
            robot = new Robot();
        }
        return robot;
    }

    @Override
    public String toString() {
        Point point = robot.position.getPoint();
        return String.valueOf(point.x) + ", " + point.y + ", " + robot.getPosition().getDirection();
    }

}
