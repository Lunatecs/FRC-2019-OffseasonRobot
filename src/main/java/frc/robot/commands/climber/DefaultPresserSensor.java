/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DefaultPresserSensor extends Command {
  public DefaultPresserSensor() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.presserSensor);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("Presser", Robot.presserSensor.getAirPressurePsi());

    if(Robot.presserSensor.getAirPressurePsi() < -2.0 ) {
      Robot.led.setColor(Robot.led.SUCTION_ACHIEVED);
    }

    if(Robot.presserSensor.getAirPressurePsi() < -6.0) {
      Robot.led.setColor(Robot.led.CLIMBING_COMPLETE);
    }

    if(Robot.presserSensor.getAirPressurePsi() > -4.0) {
      Robot.led.removeColor(Robot.led.CLIMBING_COMPLETE);
    }

    if(Robot.presserSensor.getAirPressurePsi() > -2.0) {     
      Robot.led.removeColor(Robot.led.SUCTION_ACHIEVED);
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
