/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
//Controls both elevator and lift during lift period
public class LiftRobotManual extends Command {

  //Speed should be 0.5
  private double climberSpeed = 0.0;
  private double elevatorSpeed = 0.0;
  private boolean isFinished = false;
  private boolean isClimberFinished = false;
  private boolean isElevatorFinished = false;


  public LiftRobotManual() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.climberSpeed = 0.0;
    this.elevatorSpeed = 0.0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //TODO add deadband?
    this.climberSpeed = Robot.oi.getClimbSpeed();
    this.elevatorSpeed = (Robot.oi.getClimbSpeed() * 0.577);
    
    //------------------Climber------------------
    //TODO check if negative is down and positive is up on climber or swap

    //If toplimit and trying to go up(positive), stop from going up
    if(Robot.climber.getLimitSwitch() && Robot.climber.isPastRequiredDistance()) {

      //TODO needs a minimum lift speed to keep us up(fighting springs)
      Robot.climber.stopClimberLift();
    } else {
      Robot.climber.setLiftSpeed(this.climberSpeed);
    }

    if(!Robot.oi.operatorJoystick.getRawButton(RobotMap.RIGHT_BUMPER_ID)) {
    //------------------Elevator------------------
      Robot.elevator.setSpeed(this.elevatorSpeed);
    }
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
