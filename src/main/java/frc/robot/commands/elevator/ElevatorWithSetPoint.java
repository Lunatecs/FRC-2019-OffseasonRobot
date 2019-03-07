/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorWithSetPoint extends Command {

  private int setPoint = 0;
  //boolean isFinfish = false;
  boolean isFinished = false;

  public ElevatorWithSetPoint(int setPoint) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.elevator);
    this.setPoint = setPoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    Robot.elevator.setHeight(setPoint);
    this.isFinished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.elevator.getHeight()<setPoint+100 && Robot.elevator.getHeight()>setPoint-100) {
      isFinished = true;
    } 
    SmartDashboard.putString("Elevator Set Postion", Robot.elevator.getHeight()+"");
    SmartDashboard.putBoolean("Elevator Finished", isFinished); 
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
