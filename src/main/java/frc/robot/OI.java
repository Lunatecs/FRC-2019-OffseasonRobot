
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.button.DoubleJoystickButton;
import frc.robot.button.DoublePOVButton;
import frc.robot.button.LoneJoystickButton;
import frc.robot.button.LonePOVButton;
import frc.robot.commands.autos.AutoClimb;
import frc.robot.commands.autos.AutoDrivetrain;
import frc.robot.commands.climber.DriveClimberWheels;
import frc.robot.commands.intake.hatch.GrabAndLaunchHatchManual;
import frc.robot.commands.intake.cargo.DownCargo;
import frc.robot.commands.intake.cargo.UpCargo;
import frc.robot.commands.intake.hatch.ExtendHatch;
import frc.robot.commands.intake.hatch.RetractHatch;
import frc.robot.commands.led.SetLEDColor;
import frc.robot.subsystems.LED;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
  
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  public Joystick driverJoystick = new Joystick(RobotMap.DRIVER_JOYSTICK_USB_ID);
  public Joystick operatorJoystick = new Joystick(RobotMap.OPERATOR_JOYSTICK_USB_ID);
  
  JoystickButton driverRedButton = new JoystickButton(driverJoystick, RobotMap.RED_BUTTON_ID); 
  JoystickButton driverGreenButton = new JoystickButton(driverJoystick, RobotMap.GREEN_BUTTON_ID); 

  JoystickButton greenButton = new JoystickButton(operatorJoystick, RobotMap.GREEN_BUTTON_ID);
  JoystickButton redButton = new JoystickButton(operatorJoystick, RobotMap.RED_BUTTON_ID);
  JoystickButton blueButton = new JoystickButton(operatorJoystick, RobotMap.BLUE_BUTTON_ID);
  JoystickButton yellowButton = new JoystickButton(operatorJoystick, RobotMap.YELLOW_BUTTON_ID);
  JoystickButton rightBumperButton = new JoystickButton(operatorJoystick, RobotMap.RIGHT_BUMPER_ID);
  JoystickButton leftBumperButton = new JoystickButton(operatorJoystick, RobotMap.LEFT_BUMPER_ID);
  JoystickButton leftSelect = new JoystickButton(operatorJoystick, RobotMap.LEFT_SELECT_ID);
  JoystickButton rightSelect = new JoystickButton(this.operatorJoystick, RobotMap.RIGHT_SELECT_ID);

  DoubleJoystickButton leftYellowButton = new DoubleJoystickButton(leftBumperButton, yellowButton);
  DoubleJoystickButton leftRedButton = new DoubleJoystickButton(leftBumperButton, redButton);
  DoubleJoystickButton leftGreenButton = new DoubleJoystickButton(leftBumperButton, greenButton);
  DoubleJoystickButton leftBlueButton = new DoubleJoystickButton(leftBumperButton, blueButton);

  DoubleJoystickButton rightYellowButton = new DoubleJoystickButton(rightBumperButton, yellowButton);
  DoubleJoystickButton rightRedButton = new DoubleJoystickButton(rightBumperButton, redButton);
  DoubleJoystickButton rightGreenButton = new DoubleJoystickButton(rightBumperButton, greenButton);
  DoubleJoystickButton rightBlueButton = new DoubleJoystickButton(rightBumperButton, blueButton);
  
  LoneJoystickButton loneYellowButton = new LoneJoystickButton(yellowButton, leftBumperButton, rightBumperButton);
  LoneJoystickButton loneRedButton = new LoneJoystickButton(redButton, leftBumperButton, rightBumperButton);
  LoneJoystickButton loneGreenButton = new LoneJoystickButton(greenButton, leftBumperButton, rightBumperButton);
  LoneJoystickButton loneBlueButton = new LoneJoystickButton(blueButton, leftBumperButton, rightBumperButton);

  POVButton driverUpPOV = new POVButton(this.driverJoystick, 0);
  POVButton driverDownPOV = new POVButton(this.driverJoystick, 180);

  POVButton upPOV = new POVButton(this.operatorJoystick, 0);
  POVButton rightPOV = new POVButton(this.operatorJoystick, 90);
  POVButton downPOV = new POVButton(this.operatorJoystick, 180);

  DoubleJoystickButton climbButtons = new DoubleJoystickButton(this.rightSelect, this.leftSelect);

  public OI(){
    
    driverRedButton.whileActive(new AutoDrivetrain(60));
    climbButtons.whileActive(new AutoClimb());
    
    //TODO Make sure grab and launch are going the right directions
    loneYellowButton.whileActive(new GrabAndLaunchHatchManual(0.5));
    loneGreenButton.whileActive(new GrabAndLaunchHatchManual(-0.5));
    loneRedButton.whenActive(new ExtendHatch());
    loneBlueButton.whenActive(new RetractHatch());

    upPOV.whenActive(new UpCargo());
    downPOV.whenActive(new DownCargo());

    driverUpPOV.whileActive(new DriveClimberWheels(0.25,0.25, 20.0));
    driverDownPOV.whileActive(new DriveClimberWheels(-0.25,-0.25, 20.0));

  }

  public double getSpeed(){

    return driverJoystick.getRawAxis(RobotMap.LEFT_JOY_Y_ID);
  
  }

  public double getRotation(){

    return driverJoystick.getRawAxis(RobotMap.RIGHT_JOY_X_ID);
  
  }
  
  public double getElevatorSpeed(){

    return this.operatorJoystick.getRawAxis(RobotMap.LEFT_JOY_Y_ID);

  }
  
  public double getClimbSpeed(){

    return this.operatorJoystick.getRawAxis(RobotMap.RIGHT_JOY_Y_ID);

  }

  public double getIntakeCargoSpeed(){
    return this.operatorJoystick.getRawAxis(RobotMap.RIGHT_TRIGGER_ID);
  }

  public double getLaunchCargoSpeed(){
    return this.operatorJoystick.getRawAxis(RobotMap.LEFT_TRIGGER_ID);
  }
}
