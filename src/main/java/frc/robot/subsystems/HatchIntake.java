/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class HatchIntake extends Subsystem {

  //TODO fix solenoid forward and reverse channels
  private DoubleSolenoid hatchSliderSolenoid = new DoubleSolenoid(RobotMap.SLIDER_FORWARD_ID, RobotMap.SLIDER_BACKWARD_ID);

  //TODO fix CAN value
  private TalonSRX hatchWheel = new TalonSRX(RobotMap.INTAKE_HATCH_CONTROLLER_T_ID);
  private NeutralMode WHEELS_BRAKE_MODE = NeutralMode.Brake;
  private boolean isExtended = false;

  //constructor
  public HatchIntake() {
    hatchWheel.configFactoryDefault();
    hatchWheel.setNeutralMode(WHEELS_BRAKE_MODE);
    hatchWheel.configVoltageCompSaturation(12);
    this.isExtended = false;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void extendHatchIntake(){
    hatchSliderSolenoid.set(DoubleSolenoid.Value.kForward);
    this.isExtended = true;
  }

  public void retractHatchIntake(){
    hatchSliderSolenoid.set(DoubleSolenoid.Value.kReverse);
    this.isExtended = false;
  }

  public void setHatchWheelSpeed(double speed){
    hatchWheel.set(ControlMode.PercentOutput, speed);
  }

  public boolean isExtended() {
    return this.isExtended;
  }
}
