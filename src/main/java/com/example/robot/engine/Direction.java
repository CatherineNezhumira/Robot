package com.example.robot.engine;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Direction {

    NORTH(1, new Point(0, 1)),
    EAST(2, new Point(1, 0)),
    SOUTH(3, new Point(0, -1)),
    WEST(4, new Point(-1, 0));

    @Autowired
    private static Environment env;
    private int id;
    private Point vector;

    Direction(int id, Point vector) {
        this.id = id;
        this.vector = vector;
    }

    public Direction getLeft() {
        int newDirectionId = id - 1 > 0 ? id - 1 : Direction.values().length;
        return Direction.fromValue(newDirectionId);
    }

    public Direction getRight() {
        int newDirectionId = id + 1 <= Direction.values().length ? id + 1 : 1;
        return Direction.fromValue(newDirectionId);
    }

    public Point getForwardPoint(Point currentPoint) {
        return new Point(currentPoint.x + vector.x, currentPoint.y + vector.y);
    }

    public static Direction fromValue(int value) {
        Optional<Direction> foundDirection = Arrays.stream(values()).filter(direction -> direction.id == value).findAny();
        return Optional.ofNullable(foundDirection.get())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format(env.getProperty("error.direction.type"), value, Arrays.toString(values())))
                );
    }
}
