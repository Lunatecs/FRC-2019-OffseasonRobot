/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.button;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Add your docs here.
 */
public class DoubleJoystickButton extends Trigger {

  JoystickButton first;
  JoystickButton second;

  public DoubleJoystickButton(JoystickButton first, JoystickButton second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public boolean get() {
    if(first.get() && second.get()) {
      return true;
    }
    return false;
  }
}
