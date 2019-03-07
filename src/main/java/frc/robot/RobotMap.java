/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
 
  //MOST OF THESE ARE IDs, KEEP THIS IN MIND DURING PROGRAMMING

  public static int LEFT_CENTER_DRIVE_T_ID  = 16;
  public static int LEFT_BACK_DRIVE_V_ID    = 15;
  public static int LEFT_FRONT_DRIVE_V_ID   = 14;

  public static int RIGHT_CENTER_DRIVE_T_ID = 1;
  public static int RIGHT_BACK_DRIVE_V_ID   = 2;
  public static int RIGHT_FRONT_DRIVE_V_ID  = 3;


  public static int INTAKE_TOP_CONTROLLER_T_ID    = 12;
  public static int INTAKE_BOTTOM_CONTROLLER_T_ID = 5;
  public static int INTAKE_WRIST_CONTROLLER_T_ID  = 9;

  public static int ELEVATOR_LEAD_CONTROLLER_T_ID   = 7;
  public static int ELEVATOR_FOLLOW_CONTROLLER_V_ID = 6;

  //right
  public static int CLIMBER_PULL_CONTROLLER_V_ID = 19; //4;
  //left
  public static int CLIMBER_LIFT_CONTROLLER_V_ID  = 13;

  public static int DRIVER_JOYSTICK_USB_ID = 0;
  public static int OPERATOR_JOYSTICK_USB_ID = 1;

  public static int GREEN_BUTTON_ID  = 1;
  public static int RED_BUTTON_ID    = 2;
  public static int BLUE_BUTTON_ID   = 3;
  public static int YELLOW_BUTTON_ID = 4;

  public static int LEFT_BUMPER_ID = 5;
  public static int RIGHT_BUMPER_ID = 6;

  public static int LEFT_TRIGGER_ID = 2;
  public static int RIGHT_TRIGGER_ID = 3;

  //the axes of joysticks on the controllers
  public static int LEFT_JOY_X_ID = 0;
  public static int LEFT_JOY_Y_ID = 1;
  public static int RIGHT_JOY_X_ID = 4;
  public static int RIGHT_JOY_Y_ID = 5;

  public static int LEFT_SELECT_ID = 7;
  public static int RIGHT_SELECT_ID = 8;

  public static int LEFT_JOY_BUTTON_ID = 9;
  public static int RIGHT_JOY_BUTTON_ID = 10;

  public static int ULTRASONIC_PING_ID = 0;
  public static int ULTRASONIC_ECHO_ID = 1;

  public static int POTENTIOMETER_ID = 3;
  public static int POTENTIOMETER_RANGE = 1080;
  public static int POTENTIOMETER_OFFSET = 30;
  //these are the limits for the potentiometer
  public static double POTENTIOMETER_UPPERLIMIT = 565.0;
  public static double POTENTIOMETER_LOWERLIMIT = 385.0;

  public static int LED_PWM_ID = 3;

  /* This is stuff I don't know whether on not we CARE or not,
  BUT this is just to be useful if we have a complete nuclear fallout
  with the LimeLight

  --Input--
  Exposure: 2
  Red Balance: 1545
  Blue Balance: 500

  --Thresholding--
  Hue: 45-62
  Saturation: 0-255
  Value: 149-255
  Erosion Steps: 0
  Dilation Steps: 1

  --Contour Filtering--
  Sort Mode: Closest
  Area: 0.0-100.0
  Fullness: 73.4-100.0
  W/H Ratio: 0.3001-1.1066
  Direction Filter: None
  Target Grouping: Dual Target
  Intersection Filter: Vert-Above
  
  May need some adjustment
  */


}
