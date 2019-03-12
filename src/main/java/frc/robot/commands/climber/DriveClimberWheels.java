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


  private double climbwheelspeed = 0.0;
  private double drivespeed = 0.0;
  private boolean isFinished = false;
  private double distanceToWall = 0.0;

  public DriveClimberWheels(double climbwheelspeed, double drivespeed, double distanceToWall) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.climbwheelspeed = climbwheelspeed;
    this.drivespeed = drivespeed;
    this.distanceToWall = distanceToWall;
    requires(Robot.climber);
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isFinished = false;

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.ultrasonicSensors.getRange() > this.distanceToWall){
      Robot.climber.setDriveSpeed(this.climbwheelspeed);
      Robot.drive.arcadeDrive(this.drivespeed, 0);

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
