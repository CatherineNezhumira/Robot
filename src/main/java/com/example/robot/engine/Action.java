package com.example.robot.engine;

import com.example.robot.dto.PositionDto;
import lombok.Getter;

import java.awt.*;
import java.util.function.Function;

@Getter
public enum Action {
    MOVE("move", previousPosition -> {
        Point nextPoint = previousPosition.getDirection().getForwardPoint(previousPosition.getPoint());
        return new PositionDto(nextPoint, previousPosition.getDirection());
    }),
    LEFT("left", previousPosition -> new PositionDto(previousPosition.getPoint(), previousPosition.getDirection().getLeft())),
    RIGHT("right", previousPosition -> new PositionDto(previousPosition.getPoint(), previousPosition.getDirection().getRight()));

    private String actionName;
    private Function<PositionDto, PositionDto> perform;

    Action(String actionName, Function<PositionDto, PositionDto> perform) {
        this.actionName = actionName;
        this.perform = perform;
    }

    public PositionDto perform(PositionDto position) {
        return perform.apply(position);
    }
}