/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class HatchIntake extends Subsystem {
  //TODO fix solenoid forward and reverse channels
  public DoubleSolenoid hatchExtendSolenoid = new DoubleSolenoid(0,0);

  //TODO fix CAN value
  public TalonSRX hatchWheel = new TalonSRX(0);
  public NeutralMode WHEELS_BRAKE_MODE = NeutralMode.Brake;

  //constructor
  public HatchIntake() {
    hatchWheel.configFactoryDefault();
    hatchWheel.setNeutralMode(WHEELS_BRAKE_MODE);
    hatchWheel.configVoltageCompSaturation(12);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void extendHatchIntake(){
    hatchExtendSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void retractHatchIntake(){
    hatchExtendSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void setHatchWheelSpeed(double speed){
    hatchWheel.set(ControlMode.PercentOutput, speed);
  }
}
