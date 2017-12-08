package com.example.robot;

import com.example.robot.dto.ActionDto;
import com.example.robot.dto.PositionDto;
import com.example.robot.exception.ForbiddenMoveException;
import com.example.robot.exception.MissingRobotException;
import com.example.robot.robot.Robot;
import com.example.robot.robot.RobotPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class RobotService {

    @Autowired
    private Environment env;
    private Robot robot = Robot.getRobot();

    public Robot placeRobot(PositionDto robotPosition) {
        RobotPosition newPosition = new RobotPosition(robotPosition);
        if (validate(newPosition)) {
            robot.setPosition(newPosition);
        } else {
            throw new ForbiddenMoveException(env.getProperty("error.forbidden.place.message"));
        }
        return robot;
    }

    public Robot changeRobotPosition(ActionDto action) {
        checkRobotPlaced();

        RobotPosition newPosition = action.getAction().perform(robot.getPosition());
        if (validate(newPosition)) {
            robot.setPosition(newPosition);
        } else {
            throw new ForbiddenMoveException(env.getProperty("error.forbidden.move.message"));
        }
        return robot;
    }

    public String getReport() {
        return robot.getPosition().toString();
    }

    private void checkRobotPlaced() {
        if (robot.getPosition() == null) {
            throw new MissingRobotException(env.getProperty("error.missing.robot.message"));
        }
    }

    private boolean validate(RobotPosition newPosition) {
        Point point = newPosition.getPoint();
        int tabletopDimension = (env.getProperty("tabletop.dimension", Integer.class));
        return point.x >= 0 && point.x < tabletopDimension && point.y >= 0 && point.y < tabletopDimension;
    }
}
