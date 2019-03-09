/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class GrabAndLaunchHatchManual extends Command {
  
  private double speed = 0.0;

  public GrabAndLaunchHatchManual(double speed) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.hatchIntake);
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.hatchIntake.setHatchWheelSpeed(this.speed);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  //TODO Make sure this works!!!!!!!!!
  @Override
  public void cancel() {
    super.cancel();
    Robot.hatchIntake.setHatchWheelSpeed(0);
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
