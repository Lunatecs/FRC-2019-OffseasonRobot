/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.climber.DefaultPresserSensor;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Add your docs here.
 */
public class PresserSensor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final AnalogInput mAnalogInput = new AnalogInput(RobotMap.PRESSER_ANALOG_ID);

  public double getAirPressurePsi() {
    // taken from the datasheet
    return 250.0 * mAnalogInput.getVoltage() / 5.0 - 25.0;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DefaultPresserSensor());
  }
}
