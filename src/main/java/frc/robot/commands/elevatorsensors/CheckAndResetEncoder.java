/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevatorsensors;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ElevatorSensors;
import frc.robot.subsystems.LED;

public class CheckAndResetEncoder extends Command {
  public CheckAndResetEncoder() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.elevatorSensors);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.elevatorSensors.checkAndResetEncoder();
    if(Robot.elevator.isFwdLimitSwitchClosed() || Robot.elevator.getHeight() > -3000) {
      Robot.led.removeColor(Robot.led.ELEVATOR_UP_COLOR);
    } else {
      Robot.led.setColor(Robot.led.ELEVATOR_UP_COLOR);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
