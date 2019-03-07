/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.climber.DefaultClimberCommand;



public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  VictorSPX lift = new VictorSPX(RobotMap.CLIMBER_LIFT_CONTROLLER_V_ID);
  VictorSPX pull = new VictorSPX(RobotMap.CLIMBER_PULL_CONTROLLER_V_ID);


  public void setLiftSpeed(double newSpeed){

    lift.set(ControlMode.PercentOutput, newSpeed);

  }

  public void setPullSpeed(double newSpeed){

    lift.set(ControlMode.PercentOutput, newSpeed);

  }

  public double getLiftOutputCurrent(){

    return lift.getMotorOutputVoltage();

  }

  public double getPullOutputCurrent(){

    return pull.getMotorOutputVoltage();

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DefaultClimberCommand());
  }
}
