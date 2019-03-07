/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.RobotMap;
import frc.robot.commands.autos.UltrasonicTest;


/**
 * Add your docs here.
 */
public class UltrasonicSensors extends Subsystem {
  private Ultrasonic rangeFinder = new Ultrasonic(RobotMap.ULTRASONIC_PING_ID, RobotMap.ULTRASONIC_ECHO_ID);

  public UltrasonicSensors() {
    rangeFinder.setAutomaticMode(true);
  }

  public double getRange(){
    return rangeFinder.getRangeInches();
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new UltrasonicTest());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
