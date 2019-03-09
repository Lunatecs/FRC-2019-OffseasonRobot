/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap; 
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.commands.led.LEDVisionTracking;

/**
 * Add your docs here.
 */
public class LED extends Subsystem {
  private Spark ledControl = new Spark(RobotMap.LED_PWM_ID);
  
  public static final double SOLID_GREEN = 0.77;
  public static final double SOLID_RED = 0.61;  
  public static final double SOLID_BLUE = 0.87;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  public void setColor(double colorValue) {
    ledControl.set(colorValue);
  }

  public double getColor() {
    return ledControl.get();
  }

  public void activatedDefualtColors() {
    ledControl.set(LED.SOLID_BLUE);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
   // setDefaultCommand(new LEDVisionTracking());
  }
}
