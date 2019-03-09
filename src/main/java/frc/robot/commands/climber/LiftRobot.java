/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftRobot extends Command {

  private double speed = 0.0;
  private boolean isFinished = false;

  public LiftRobot(double speed) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.climber);
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.isFinished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.climber.getLimitSwitch() == true) {
      //TODO needs a minimum lift speed to keep us up
      Robot.climber.setLiftSpeed(0); 
      this.isFinished = true;
    } else {
      Robot.climber.setLiftSpeed(1.0);
      this.isFinished = false;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
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
