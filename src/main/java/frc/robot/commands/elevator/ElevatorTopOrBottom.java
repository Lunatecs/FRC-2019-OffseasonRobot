/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

public class ElevatorTopOrBottom extends Command {

  private boolean top = false;
  private boolean isFinished = false;;

  public ElevatorTopOrBottom(boolean top) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.elevator);
    this.top = top;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.isFinished=false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if(top) {
      if(Robot.elevator.getHeight() == Elevator.TOP_ELEVATOR_SET_POINT) {
        this.isFinished=true;
      } else {
        Robot.elevator.setSpeed(-1.0);
      }
    } else {
      if(Robot.elevator.getHeight() == Elevator.BOTTOM_ELEVATOR_SET_POINT) {
        this.isFinished=true;
      } else {
        Robot.elevator.setSpeed(1.0);
      }
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
