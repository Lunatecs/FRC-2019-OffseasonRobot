/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;



public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public boolean isLowerd = false;
  DoubleSolenoid lift = new DoubleSolenoid(RobotMap.CLIMBER_LIFT_FORWARD_ID, RobotMap.CLIMBER_LIFT_BACKWARD_ID);
  public static final int REQUIRED_DISTANCE = 8000;


  public void extendLift() {
    lift.set(DoubleSolenoid.Value.kReverse);
    isLowerd = true;
  }

  public void retractLift() {
    lift.set(DoubleSolenoid.Value.kForward);
    isLowerd = false;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand(new LiftRobotManual());
  }
}
