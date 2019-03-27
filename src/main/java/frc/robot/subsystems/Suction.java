/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Suction extends Subsystem {

  private TalonSRX suctionPump = new TalonSRX(RobotMap.SUCTION_TALON_ID);

  public Suction(){

    suctionPump.configFactoryDefault();
    suctionPump.setNeutralMode(NeutralMode.Coast);

  }
  // Man are we going to SUCK this competition

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //Default: get 12 hab points, hopefully.
  }

  public void setSpeed(double speed){

    suctionPump.set(ControlMode.PercentOutput, speed);
    
  }

  public double checkAmperage(){

    return suctionPump.getOutputCurrent();

  }


}
