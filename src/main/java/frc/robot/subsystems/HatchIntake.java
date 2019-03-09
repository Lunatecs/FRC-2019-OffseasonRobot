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
  //TODO make sure solenoids are pushing/pulling correctly
  private DoubleSolenoid hatchSliderSolenoid = new DoubleSolenoid(0,7);

  //TODO fix CAN value
  private TalonSRX hatchWheel = new TalonSRX(0);
  private NeutralMode WHEELS_BRAKE_MODE = NeutralMode.Brake;

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
    hatchSliderSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void retractHatchIntake(){
    hatchSliderSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void setHatchWheelSpeed(double speed){
    hatchWheel.set(ControlMode.PercentOutput, speed);
  }
}
