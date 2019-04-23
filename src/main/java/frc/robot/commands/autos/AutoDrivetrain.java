/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class AutoDrivetrain extends PIDCommand {

  private double setPoint = 0.0;
  private boolean isFinished = false;

  public AutoDrivetrain(double setPoint) {
    super("AutoDrivetrain",.0125,0.0,0.0);
    this.getPIDController().setAbsoluteTolerance(1.0);
    requires(Robot.drive);
    this.setPoint = setPoint;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.isFinished=false;
    this.setSetpoint(setPoint);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if(this.getPIDController().onTarget()) {
      this.isFinished = true;
    } else {
    
    }
    SmartDashboard.putData(Robot.drive);
    SmartDashboard.putBoolean("Auto Drive Finished", isFinished);

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

  @Override
  protected double returnPIDInput() {
    return Robot.ultrasonicSensors.getRange();
  }

  @Override
  protected void usePIDOutput(double output) {
    double turnTolerance = 0.5;
    double rotationKp = 0.045;
    double rotation = 0.0;

    double horizontalError = Robot.limelight.getTX();
    //The difference of how close we are to the setpoint according to our current pos.
    double difference = (Robot.ultrasonicSensors.getRange() - this.setPoint);

    //If we are not at the goal yet
    if(Math.abs(difference) > this.setPoint) {
        if(Math.abs(horizontalError) > turnTolerance) {
          rotation = rotationKp * horizontalError;
        }
    }

    if(Math.abs(rotation) > .75) {
      rotation = .75 * (rotation/Math.abs(rotation));
    }

    if(Math.abs(output) > .75) {
      output = .75 * (output/Math.abs(output));
    } 
    SmartDashboard.putNumber("AutoDrivetrain PID", output); 
    Robot.drive.arcadeDriveWithoutEncoders(output, rotation, false);
  }
}
