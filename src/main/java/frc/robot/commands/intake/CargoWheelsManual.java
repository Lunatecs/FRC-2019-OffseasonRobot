/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Robot;

public class CargoWheelsManual extends Command {

  private boolean applyConstantIntake = false;

  public CargoWheelsManual() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.cargoIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.applyConstantIntake = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double intakeSpeed = Robot.oi.getIntakeCargoSpeed();
    double launchSpeed = Robot.oi.getLaunchCargoSpeed();

    //TODO check if wheels are spinning right direction 
    if(intakeSpeed > 0.2) {
      Robot.cargoIntake.setCargoWheelSpeed(-intakeSpeed);
      this.applyConstantIntake = true;
    } else if(launchSpeed > 0.2) {
      Robot.cargoIntake.setCargoWheelSpeed(launchSpeed);
      this.applyConstantIntake = false;
    } else {
      if(applyConstantIntake) {
        Robot.cargoIntake.setCargoWheelSpeed(-0.25);
      } else {
        Robot.cargoIntake.setCargoWheelSpeed(0);
      }
    }

    if (RobotMap.debug) {
      SmartDashboard.putNumber("Intake", intakeSpeed);
      SmartDashboard.putNumber("Launch", launchSpeed);
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
