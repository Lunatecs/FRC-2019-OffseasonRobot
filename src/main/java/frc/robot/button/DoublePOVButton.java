/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.button;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Add your docs here.
 */
public class DoublePOVButton extends Trigger {
  JoystickButton first;
  POVButton second;

  public DoublePOVButton(JoystickButton first, POVButton second) {
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
