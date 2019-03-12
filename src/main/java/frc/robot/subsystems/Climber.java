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
import frc.robot.commands.climber.DefaultClimberCommand;



public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //TODO: set can id
  TalonSRX lift = new TalonSRX(RobotMap.CLIMBER_PUSHDOWN_CONTROLLER_V_ID);
  //TODO: set can id
  VictorSPX drive = new VictorSPX(RobotMap.CLIMBER_DRIVE_CONTROLLER_V_ID);
  //TODO: set can id for forward and reverse
  DoubleSolenoid arms = new DoubleSolenoid(RobotMap.CLARMS_FORWARD_ID, RobotMap.CLARMS_BACKWARD_ID);
  //TODO: set DIO port
  DigitalInput limitSwitch = new DigitalInput(0);
  

  public static final int REQUIRED_DISTANCE = 8000;


  public void setLiftSpeed(double newSpeed){

    lift.set(ControlMode.PercentOutput, newSpeed);

  }

  public void setDriveSpeed(double newSpeed) {
    drive.set(ControlMode.PercentOutput, newSpeed);
  }

  public void lowerArms() {
    arms.set(DoubleSolenoid.Value.kForward);
  }

  public void raiseArms() {
    arms.set(DoubleSolenoid.Value.kReverse);
  }

  public boolean getLimitSwitch() {
    return this.limitSwitch.get();
  }

  public int getEncoderValue() {
    return this.lift.getSelectedSensorPosition(0);
  }

  public double getOutputCurrent() {
    return lift.getOutputCurrent();
  }

  public void resetEncoder() {
    this.lift.setSelectedSensorPosition(0, 0, 10);
  }

  public void stopClimberLift() {
    this.setLiftSpeed(0);
    this.resetEncoder();
  }


  public boolean isPastRequiredDistance() {
    if(Math.abs(getEncoderValue())>Climber.REQUIRED_DISTANCE) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DefaultClimberCommand());
  }
}
