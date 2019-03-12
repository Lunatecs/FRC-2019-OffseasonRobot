/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

//TODO MAKE SURE CLIMBER AND ELEVATOR ARE GOING IN THE RIGHT DIRECTION!!!!!!!!!!!!
public class LiftRobotAuto extends Command {

  //Speed should be 0.5
  private double climberSpeed = 0.0;
  private double elevatorSpeed = 0.0;
  private boolean isFinished = false;
  private boolean isClimberFinished = false;
  private boolean isElevatorFinished = false;


  public LiftRobotAuto(double climberSpeed) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.climber);
    this.climberSpeed = climberSpeed;
    this.elevatorSpeed = this.climberSpeed * 0.577;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    this.isFinished = false;
    this.isClimberFinished = false;
    this.isElevatorFinished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //------------------Climber------------------
    if(Robot.climber.getLimitSwitch() && Robot.climber.isPastRequiredDistance()) {
      Robot.climber.stopClimberLift();
      this.isClimberFinished = true;
    } else {
      Robot.climber.setLiftSpeed(this.climberSpeed);
    }

    //------------------Elevator------------------
    if(Robot.elevator.isFwdLimitSwitchClosed()) {
      Robot.elevator.setSpeed(0.0);
      isElevatorFinished = true; 
    } else {
      Robot.elevator.setSpeed(this.elevatorSpeed);
      isElevatorFinished = false;
    }

    //------------------Check------------------
    if(isClimberFinished && isElevatorFinished) {
      isFinished = true;
    } else {
      isFinished = false;
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
