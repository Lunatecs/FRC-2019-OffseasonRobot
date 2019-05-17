/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.drive.LunatecsDrive;
import frc.robot.commands.drive.DriveWithJoysticks;
import frc.robot.Robot;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveTrain extends Subsystem {

  private LunatecsDrive drive;

  private WPI_VictorSPX leftFront_V = new WPI_VictorSPX(RobotMap.LEFT_FRONT_DRIVE_V_ID);
  public WPI_TalonSRX leftCenter_T = new WPI_TalonSRX(RobotMap.LEFT_CENTER_DRIVE_T_ID);
  private WPI_VictorSPX leftBack_V  = new WPI_VictorSPX(RobotMap.LEFT_BACK_DRIVE_V_ID);

  private WPI_VictorSPX rightFront_V = new WPI_VictorSPX(RobotMap.RIGHT_FRONT_DRIVE_V_ID);
  public WPI_TalonSRX rightCenter_T = new WPI_TalonSRX(RobotMap.RIGHT_CENTER_DRIVE_T_ID);
  private WPI_VictorSPX rightBack_V = new WPI_VictorSPX(RobotMap.RIGHT_BACK_DRIVE_V_ID);

  private static NeutralMode DRIVE_NEUTRAL_MODE = NeutralMode.Brake;

  public DriveTrain(){

    leftFront_V.configFactoryDefault();
    leftCenter_T.configFactoryDefault();
    leftBack_V.configFactoryDefault();

    rightFront_V.configFactoryDefault();
    rightCenter_T.configFactoryDefault();
    rightBack_V.configFactoryDefault();

    
    leftFront_V.setNeutralMode(DRIVE_NEUTRAL_MODE);
    leftCenter_T.setNeutralMode(DRIVE_NEUTRAL_MODE);
    leftBack_V.setNeutralMode(DRIVE_NEUTRAL_MODE);

    rightFront_V.setNeutralMode(DRIVE_NEUTRAL_MODE);
    rightCenter_T.setNeutralMode(DRIVE_NEUTRAL_MODE);
    rightBack_V.setNeutralMode(DRIVE_NEUTRAL_MODE);
    

    leftFront_V.follow(leftCenter_T);
    leftBack_V.follow(leftCenter_T);

    rightFront_V.follow(rightCenter_T);
    rightBack_V.follow(rightCenter_T);

    this.rightCenter_T.configOpenloopRamp(.25, 10);
    this.leftCenter_T.configOpenloopRamp(.25, 10);
    
    drive = new LunatecsDrive(leftCenter_T, rightCenter_T);
  }

  //TODO Deal with rampup
  
  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(speed, rotation, false);
  }

  public void arcadeDriveWithoutEncoders(double speed, double rotation, boolean rampUp) {
    drive.arcadeDriveWithoutEncoders(speed, rotation, rampUp);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    drive.tankDrive(leftSpeed, rightSpeed);
  }

  public int getLeftEncoderRate() {
    //returns pos every 100ms
    return leftCenter_T.getSelectedSensorVelocity(0);
  }
  
  public int getRightEncoderRate() {
    //returns pos every 100ms
    return rightCenter_T.getSelectedSensorVelocity(0);
  }

  public int getLeftEncoder() {
    return leftCenter_T.getSelectedSensorPosition(0);
  }

  public int getRightEncoder() {
    return rightCenter_T.getSelectedSensorPosition(0);
  }

  public void setLeftEncoder(int pos){
    leftCenter_T.setSelectedSensorPosition(pos,0,10);
  }

  public void setRightEncoder(int pos){
    rightCenter_T.setSelectedSensorPosition(pos,0,10);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveWithJoysticks());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
