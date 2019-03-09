/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.intake.cargo.CargoWheelsManual;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class CargoIntake extends Subsystem {
  
  //TODO fix solenoid forward and reverse channels
  public DoubleSolenoid cargoWristSolenoid = new DoubleSolenoid(0, 0);

  //TODO fix CAN value
  public TalonSRX cargoWheel = new TalonSRX(0);
  public NeutralMode WHEELS_BRAKE_MODE = NeutralMode.Brake;

  //constructor
  public CargoIntake(){

    cargoWheel.configFactoryDefault();
    cargoWheel.setNeutralMode(WHEELS_BRAKE_MODE);
    cargoWheel.configVoltageCompSaturation(12);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new CargoWheelsManual());
  }
  //TODO correct forward and reverse of solenoids to the right actions
  public void raiseCargoIntake(){
    cargoWristSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void lowerCargoIntake(){
    cargoWristSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void setCargoWheelSpeed(double speed){
    cargoWheel.set(ControlMode.PercentOutput, speed);
  }  
}
