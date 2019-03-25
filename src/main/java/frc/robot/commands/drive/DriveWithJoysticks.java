/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class DriveWithJoysticks extends Command {
  public DriveWithJoysticks() {
    requires(Robot.drive);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    if(Robot.oi.driverJoystick.getRawAxis(RobotMap.LEFT_TRIGGER_ID) > .2) {

//      Robot.drive.arcadeDrive(Robot.oi.getSpeed()*.5, Robot.oi.getRotation()*.7);
      Robot.drive.arcadeDriveWithoutEncoders(Robot.oi.getSpeed()*.5, Robot.oi.getRotation()*.6, false);
      //Robot.drive.tankDrive(Robot.oi.getLeftSpeed()*.5, Robot.oi.getRightspeed()*.6);
    } else  if(Robot.oi.driverJoystick.getRawAxis(RobotMap.RIGHT_TRIGGER_ID) > .2){
      
      Robot.drive.arcadeDriveWithoutEncoders(Robot.oi.getSpeed()*.85, Robot.oi.getRotation()*.85, false);
      //Robot.drive.tankDrive(Robot.oi.getLeftSpeed()*.85, Robot.oi.getRightspeed()*.85);
    } else {
      if(Robot.oi.driverJoystick.getRawButton(RobotMap.RIGHT_BUMPER_ID)) {
        //Robot.drive.arcadeDriveWithoutEncoders(Robot.oi.getSpeed(), Robot.oi.getRotation(), false);
         Robot.drive.tankDrive(Robot.oi.getRightspeed(), Robot.oi.getLeftSpeed());
     
      } else {

        Robot.drive.arcadeDriveWithoutEncoders(Robot.oi.getSpeed(), Robot.oi.getRotation(), true);
      }
      //Robot.drive.tankDrive(Robot.oi.getLeftSpeed(), Robot.oi.getRightspeed());
      //Robot.drive.arcadeDrive((Robot.oi.getSpeed()), Robot.oi.getRotation());
    
    }

    //SmartDashboard.putNumber("LeftEncoder", Robot.drive.getLeftEncoder());
    //SmartDashboard.putNumber("RightEncoder", Robot.drive.getRightEncoder());
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
