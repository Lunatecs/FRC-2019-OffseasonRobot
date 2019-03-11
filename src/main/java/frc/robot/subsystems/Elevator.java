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
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.ElevatorWithJoystick;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private VictorSPX follower = new VictorSPX(RobotMap.ELEVATOR_FOLLOW_CONTROLLER_V_ID);
  public TalonSRX leader = new TalonSRX(RobotMap.ELEVATOR_LEAD_CONTROLLER_T_ID);
  private static NeutralMode ELEVATOR_NEUTRAL_MODE = NeutralMode.Brake;

  // this is for going up
  private static double PEAK_FORWARD = 1.0;

  // this is for going down
  private static double PEAK_BACKWARD = -1.0;
 
  private static int TIMEOUT = 10;
  private static double DEADZONE = .1;
  private static final double POSITION_kP = 0.2;
  private static final double POSITION_kI = 0.0;
  //private static final double PERCENT_kP = 0.00007843;
  private static final double PERCENT_kP = 0.00010843;

  private static final int TOP_ELEVATOR_LIMIT = 27200;
  private static final int BOTTOM_ELEVATOR_LIMIT = 1;

  public static final int TOP_ELEVATOR_SET_POINT = -26000;
  public static final int BOTTOM_ELEVATOR_SET_POINT = -1;

  public static final int ROCKET_UPPER_HATCH = -26260;
  public static final int ROCKET_MIDDLE_HATCH = -13500;
  public static final int ROCKET_LOWER_HATCH = -1;


  public static final double TOP_ELEVATOR_MIN_POWER = -0.15;
  public static final double BOTTOM_ELEVATOR_MIN_POWER = 0.1;

  public static final int ROCKET_UPPER_CARGO = -2000;
  //cannot calculate yet bc of brad
  public static final int ROCKET_MIDDLE_CARGO = -21377;
  public static final int ROCKET_LOWER_CARGO = -9272;
  public static final int CARGO_SHIP_CARGO = -17000;
  //cannot calculate yet
  


  public Elevator(){
    leader.configFactoryDefault();
    follower.configFactoryDefault();

    leader.setNeutralMode(ELEVATOR_NEUTRAL_MODE);
    follower.setNeutralMode(ELEVATOR_NEUTRAL_MODE);

    leader.configPeakOutputForward(PEAK_FORWARD,TIMEOUT);
    follower.configPeakOutputForward(PEAK_FORWARD,TIMEOUT);

    leader.configPeakOutputReverse(PEAK_BACKWARD,TIMEOUT);
    follower.configPeakOutputReverse(PEAK_BACKWARD,TIMEOUT);

    leader.configClosedloopRamp(.25, 10);
    leader.configOpenloopRamp(.25, 10);

    leader.setSelectedSensorPosition(0,0,TIMEOUT);
    
   

    leader.config_kP(0, POSITION_kP, TIMEOUT);
    leader.config_kI(0, POSITION_kI, TIMEOUT);
    follower.follow(leader);

  }

  public void setSpeed(double speed) {

    if(Math.abs(speed)> DEADZONE){
      
      if(speed < 0){

        double error = TOP_ELEVATOR_LIMIT + leader.getSelectedSensorPosition(0);

        double speed2 = PERCENT_kP * speed* error;
        
        if(speed2 < speed){
          speed2 = speed;
        }

        if(speed2 > TOP_ELEVATOR_MIN_POWER) {
          speed2 = TOP_ELEVATOR_MIN_POWER;
        } 
        SmartDashboard.putNumber("UP", speed2);

        leader.set(ControlMode.PercentOutput, speed2);

      } else if(speed > 0){

        double error = -leader.getSelectedSensorPosition(0) - BOTTOM_ELEVATOR_LIMIT;

        double speed2 = PERCENT_kP * speed * error;

        if(speed2 > speed){
          speed2 = speed;
        }

        if(speed2 < BOTTOM_ELEVATOR_MIN_POWER) {
          speed2 = BOTTOM_ELEVATOR_MIN_POWER;
        }

        SmartDashboard.putNumber("DOWN", speed2);

        leader.set(ControlMode.PercentOutput, speed2);

      }

    } else {
      stop();
    }
    

 }

  public void checkAndResetEncoder() {
    boolean fwd = false;
    boolean rev = false;
    if(leader.getSensorCollection().isFwdLimitSwitchClosed()) {
      fwd = true;
      this.leader.setSelectedSensorPosition(BOTTOM_ELEVATOR_SET_POINT,0,10);
    } else if(leader.getSensorCollection().isRevLimitSwitchClosed()) {
      rev = true;
      this.leader.setSelectedSensorPosition(TOP_ELEVATOR_SET_POINT,0,10);
    }
    SmartDashboard.putBoolean("Fwd", fwd);
    SmartDashboard.putBoolean("Rev", rev);
  }

  public boolean isRevLimitSwitchClosed() {
    return leader.getSensorCollection().isRevLimitSwitchClosed();
  }

  public boolean isFwdLimitSwitchClosed() {
    return leader.getSensorCollection().isFwdLimitSwitchClosed();
  }

  public void setHeight(int ticks) {
    //Will, I would prefer if we didn't have ticks in the shop, most of us are already sick.
    leader.set(ControlMode.Position, ticks);
  }

  public int getHeight() {
    //Need a (disp)sensor here
    
    return leader.getSelectedSensorPosition(0);
  }

  public void stop() {
    leader.set(ControlMode.PercentOutput,0);
  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ElevatorWithJoystick());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
