/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.UltrasonicSensors;

public class AutoForwardDrive extends Command {

  double commandTarget;
  double autokp = 0.1;
  double speed = 0.1;

  boolean isFinished = false;


  public AutoForwardDrive(double target, double speed) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    commandTarget = target;

    this.speed = speed;

    requires(Robot.drive);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    double curDist = Robot.ultrasonicSensors.getRange();

    if(curDist > commandTarget){

      //double diff = curDist - commandTarget;
      //double newSpeed = diff * autokp;

      Robot.drive.arcadeDrive(speed, 0);

    } else {

      this.isFinished = true;
      this.end();

    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    return this.isFinished;

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.arcadeDrive(0, 0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();

  }
}
// (\/)!_!(\/)
//   //===\\