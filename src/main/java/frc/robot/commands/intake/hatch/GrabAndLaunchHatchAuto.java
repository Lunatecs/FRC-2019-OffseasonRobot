/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake.hatch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class GrabAndLaunchHatchAuto extends Command {
  
  private double speed = 0.0;

  public GrabAndLaunchHatchAuto(double speed, double timeout) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.hatchIntake);
    this.speed = speed;
    setTimeout(timeout);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.hatchIntake.setHatchWheelSpeed(this.speed);
    SmartDashboard.putBoolean("AUTO FINISHED", false);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(this.isTimedOut()){
      Robot.hatchIntake.setHatchWheelSpeed(0);
      SmartDashboard.putBoolean("AUTO FINISHED", true);
      return true;
    }
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
