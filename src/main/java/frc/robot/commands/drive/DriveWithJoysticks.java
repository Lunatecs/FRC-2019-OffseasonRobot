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

  public static final double rotationKp = 0.04;
  public static final double rotationKd = 0.000;
  public static final double maxRotation = 0.65;
  private double previousError = 0.0;
  

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
    
    double speed = Robot.oi.getSpeed();
    double rotation = Robot.oi.getRotation();

    Robot.led.removeColor(Robot.led.SLOW_SPEED);

    if(Robot.oi.driverJoystick.getRawAxis(RobotMap.LEFT_TRIGGER_ID) > .2) {

      Robot.led.setColor(Robot.led.SLOW_SPEED);
      speed = speed * .5;
      rotation = rotation * .6;
  
    } else  if(Robot.oi.driverJoystick.getRawAxis(RobotMap.RIGHT_TRIGGER_ID) > .2){
      
      speed = speed * .85;
      rotation = rotation * .85;

    } else {
      //Always scale rotation to 85% if not using slow modes
      rotation = rotation * .85;
    }
    
    if(Robot.oi.driverJoystick.getRawButton(RobotMap.RED_BUTTON_ID)) {
      rotation = this.getScaledRotation();
      //if(rotation > maxRotation) {
      //  rotation = maxRotation;
      //}
    }

    if(Robot.oi.driverJoystick.getRawButton(RobotMap.RIGHT_BUMPER_ID)) {
  
      Robot.drive.tankDrive(Robot.oi.getRightspeed(), Robot.oi.getLeftSpeed());     
  
    } else {
  
      Robot.drive.arcadeDriveWithoutEncoders(speed, rotation, false);
  
    }

    SmartDashboard.putNumber("Rotation", rotation);

  }

  public double getScaledRotation() {
    
    double speed = 0.0;
    double error = Robot.limelight.getTX();

    speed = (rotationKp * error) + (rotationKd * (previousError - error));
    
    SmartDashboard.putNumber("Error", error);
    SmartDashboard.putNumber("Change in Error", previousError-error);

    this.previousError = error;
    
    if((error>.25 || error<-.25)&& Math.abs(speed)<.55) {
      speed = Math.abs(speed)/speed * .55;
    }

    return speed;
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
