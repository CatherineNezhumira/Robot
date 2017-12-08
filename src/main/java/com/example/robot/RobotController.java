package com.example.robot;

import com.example.robot.dto.ActionDto;
import com.example.robot.dto.PositionDto;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CommonsLog
@RequestMapping("/robot")
public class RobotController {
    private final RobotService robotService;

    @Autowired
    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @PostMapping
    public ResponseEntity initializeRobot(@Validated @RequestBody PositionDto robotPosition) {
        return new ResponseEntity(robotService.placeRobot(robotPosition), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity changeRobotState(@Validated @RequestBody ActionDto action) {
        return ResponseEntity.ok(robotService.changeRobotPosition(action));
    }

    @GetMapping
    public ResponseEntity getReport() {
        return ResponseEntity.ok(robotService.getReport());
    }

}