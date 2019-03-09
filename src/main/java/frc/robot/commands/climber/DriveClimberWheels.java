/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.subsystems.Climber;

public class DriveClimberWheels extends Command {
  public DriveClimberWheels() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    requires(Robot.climber);
    requires(Robot.drive);
  }

  private static final double CLIMBERWHEELSPEED = 0.5;
  private static final double DRIVESPEED = 0.5;
  private boolean isFinished = false;
  private static final double DISTANCE_TO_WALL = 20.0;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isFinished = false;

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.ultrasonicSensors.getRange() > DISTANCE_TO_WALL){
      Robot.climber.setDriveSpeed(CLIMBERWHEELSPEED);
      Robot.drive.arcadeDrive(DRIVESPEED, 0);

    } else {
      Robot.climber.setDriveSpeed(0);
      Robot.drive.arcadeDrive(0, 0);
      isFinished = true;
      
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
    Robot.climber.setDriveSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
