package com.example.robot;

import com.example.robot.dto.ActionDto;
import com.example.robot.dto.PositionDto;
import com.example.robot.engine.Action;
import com.example.robot.engine.Direction;
import com.example.robot.exception.ForbiddenMoveException;
import com.example.robot.exception.MissingRobotException;
import com.example.robot.robot.Robot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RobotApplicationTests {
	@Autowired
	private RobotService robotService;

	@Test(expected = MissingRobotException.class)
	public void robotShouldntMoveBeforePlaceTest() {
		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
	}

	@Test(expected = ForbiddenMoveException.class)
	public void robotShouldntPlacedOutOfXAxisTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(-1,0)).direction(Direction.EAST).build());
	}

	@Test(expected = ForbiddenMoveException.class)
	public void robotShouldntPlacedOutOfYAxisTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(3,5)).direction(Direction.SOUTH).build());
	}

	@Test
	public void robotShouldTurnLeftTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(0,0)).direction(Direction.EAST).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.LEFT).build());
		Robot robot = robotService.changeRobotPosition(ActionDto.builder().action(Action.LEFT).build());
		Assert.assertEquals("Robot didn`t turn", Direction.WEST, robot.getPosition().getDirection());

		robot = robotService.changeRobotPosition(ActionDto.builder().action(Action.LEFT).build());
		Assert.assertEquals("Robot didn`t turn", Direction.SOUTH, robot.getPosition().getDirection());
	}

	@Test
	public void robotShouldTurnRightTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(3,0)).direction(Direction.WEST).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.RIGHT).build());
		Robot robot = robotService.changeRobotPosition(ActionDto.builder().action(Action.RIGHT).build());
		Assert.assertEquals("Robot didn`t turn", Direction.EAST, robot.getPosition().getDirection());

		robot = robotService.changeRobotPosition(ActionDto.builder().action(Action.RIGHT).build());
		Assert.assertEquals("Robot didn`t turn", Direction.SOUTH, robot.getPosition().getDirection());
	}

	@Test
	public void robotShouldMoveTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(0,0)).direction(Direction.SOUTH).build());
		Robot robot = robotService.changeRobotPosition(ActionDto.builder().action(Action.LEFT).build());
		Assert.assertEquals("Robot didn`t turn", Direction.EAST, robot.getPosition().getDirection());

		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.LEFT).build());

		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.RIGHT).build());

		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.LEFT).build());

		robot = robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
		Assert.assertEquals("Robot didn`t moved", Direction.NORTH, robot.getPosition().getDirection());

		Assert.assertEquals("Robot didn`t moved", 4, robot.getPosition().getPoint().x);
		Assert.assertEquals("Robot didn`t moved", 4, robot.getPosition().getPoint().y);
	}

	@Test(expected = ForbiddenMoveException.class)
	public void robotShouldntMoveWestOutRangeFromStartTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(0,0)).direction(Direction.WEST).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
	}

	@Test(expected = ForbiddenMoveException.class)
	public void robotShouldntMoveSouthOutRangeFromStartTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(0,0)).direction(Direction.SOUTH).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
	}

	@Test
	public void exceptionShouldntThrowByTurnLeftTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(0,0)).direction(Direction.NORTH).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.LEFT).build());

		robotService.placeRobot(PositionDto.builder().point(new Point(0,4)).direction(Direction.EAST).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.LEFT).build());

		robotService.placeRobot(PositionDto.builder().point(new Point(4,4)).direction(Direction.SOUTH).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.LEFT).build());

		robotService.placeRobot(PositionDto.builder().point(new Point(4,0)).direction(Direction.WEST).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.LEFT).build());
	}

	@Test
	public void exceptionShouldntThrowByTurnRightTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(0,0)).direction(Direction.EAST).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.RIGHT).build());

		robotService.placeRobot(PositionDto.builder().point(new Point(0,4)).direction(Direction.SOUTH).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.RIGHT).build());

		robotService.placeRobot(PositionDto.builder().point(new Point(4,4)).direction(Direction.WEST).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.RIGHT).build());

		robotService.placeRobot(PositionDto.builder().point(new Point(4,0)).direction(Direction.NORTH).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.RIGHT).build());
	}

	@Test(expected = ForbiddenMoveException.class)
	public void robotShouldntMoveOutRangeNorthTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(2,4)).direction(Direction.NORTH).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
	}

	@Test(expected = ForbiddenMoveException.class)
	public void robotShouldntMoveOutRangeWestTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(0,3)).direction(Direction.WEST).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
	}

	@Test(expected = ForbiddenMoveException.class)
	public void robotShouldntMoveOutRangeSouthTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(1,0)).direction(Direction.SOUTH).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
	}

	@Test(expected = ForbiddenMoveException.class)
	public void robotShouldntMoveOutRangeEastTest() {
		robotService.placeRobot(PositionDto.builder().point(new Point(4,4)).direction(Direction.EAST).build());
		robotService.changeRobotPosition(ActionDto.builder().action(Action.MOVE).build());
	}
}
