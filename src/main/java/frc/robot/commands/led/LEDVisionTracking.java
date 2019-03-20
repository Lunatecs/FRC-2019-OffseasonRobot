/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.led;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.LED;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Ultrasonic;

public class LEDVisionTracking extends Command {
  
  private Ultrasonic rangeFinder = new Ultrasonic(RobotMap.ULTRASONIC_PING_ID, RobotMap.ULTRASONIC_ECHO_ID);
  private static final double LEDTriggerLimit = 50;
  
  public LEDVisionTracking() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.limelight);
    requires(Robot.led);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    rangeFinder.setAutomaticMode(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double actualDistance = rangeFinder.getRangeInches();
    
    //If in range of ultrasonic sensor and there is a valid target
    if(actualDistance <= LEDTriggerLimit && Robot.limelight.isValidTarget() && !(actualDistance < 0)){
      //Robot.led.setColor(LED.SOLID_GREEN);
    } else {
     // Robot.led.setColor(LED.SOLID_RED);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
