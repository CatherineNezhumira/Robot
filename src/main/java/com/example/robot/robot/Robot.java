package com.example.robot.robot;

import lombok.Data;

@Data
public class Robot {

    private RobotPosition position;
    private static Robot robot = null;

    public static Robot getRobot() {
        if (robot == null) {
            robot = new Robot();
        }
        return robot;
    }

}
