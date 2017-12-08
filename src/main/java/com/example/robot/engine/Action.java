package com.example.robot.engine;

import com.example.robot.robot.RobotPosition;
import lombok.Getter;

import java.util.function.Function;

@Getter
public enum Action {
    MOVE("move", RobotPosition::forwardPosition),
    LEFT("left", RobotPosition::turnLeft),
    RIGHT("right", RobotPosition::turnRight);

    private String actionName;
    private Function<RobotPosition, RobotPosition> perform;

    Action(String actionName, Function<RobotPosition, RobotPosition> perform) {
        this.actionName = actionName;
        this.perform = perform;
    }

    public RobotPosition perform(RobotPosition position) {
        return perform.apply(position);
    }
}