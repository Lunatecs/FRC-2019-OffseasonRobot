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
import frc.robot.commands.led.DefaultLEDCountDown;
import frc.robot.commands.led.LEDVisionTracking;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;


/**
 * Add your docs here.
 */
public class LED extends Subsystem {
  private Spark ledControl = new Spark(RobotMap.LED_PWM_ID);
  
  private static final double SOLID_GREEN = 0.77;
  private static final double SOLID_RED = 0.61;  
  private static final double SOLID_BLUE = 0.87;
  private static final double DEFAULT_COLOR = 0.57;//-0.97;//0.55;
  private static final double REDORANGE_SOLID = 0.63;
  private static final double SOLID_SKYBLUE = 0.83;
  private static final double FIRE_MEDIUM = -0.59;
  private static final double SOLID_WHITE = 0.93;
  private static final double HEARTBEAT_WHITE = -0.21;
  private static final double RAINBOW_GLITTER = -0.89;


  public final PriorityColor SLOW_SPEED = new PriorityColor(HEARTBEAT_WHITE, 20);

  public final PriorityColor ELEVATOR_UP_COLOR = new PriorityColor(SOLID_RED, 10);
  public final PriorityColor ELEVATOR_DOWN_COLOR = new PriorityColor(SOLID_RED, 10);

  public final PriorityColor SUCTION_ACHIEVED = new PriorityColor(SOLID_WHITE, 30);
  public final PriorityColor CLIMBING_INPROGRESS = new PriorityColor(HEARTBEAT_WHITE, 40);
  public final PriorityColor CLIMBING_COMPLETE = new PriorityColor(RAINBOW_GLITTER, 50);

  public final PriorityColor HATCH_INTAKE_COLOR = new PriorityColor(SOLID_GREEN, 30);
  public final PriorityColor HATCH_INTAKING_COLOR = new PriorityColor(FIRE_MEDIUM, 10);
  


  private PriorityQueue<PriorityColor> queue = null;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public LED(){
    queue = new PriorityQueue<PriorityColor>();
    queue.add(new PriorityColor(DEFAULT_COLOR, 0));
    this.activateDefaultColors();
    
  }

  public void setColor(PriorityColor color) {
    if(!queue.contains(color))
      queue.add(color);
    ledControl.set(queue.peek().color);
  }

  public double getColor() {
    return ledControl.get();
  }

  public void removeColor(PriorityColor color) {
    while(queue.contains(color))
      queue.remove(color);
    ledControl.set(queue.peek().color);
  }

  private void activateDefaultColors() {
    ledControl.set(LED.DEFAULT_COLOR);
  }

  public void countDownColorsInQueue() {
    if(queue.size() > 1) {
      Iterator<PriorityColor> it = queue.iterator();
      while(it.hasNext()) {
        PriorityColor color = it.next();
        if(color.loopCount > -10) {
          color.loopCount--;
          if(color.loopCount<0) {
            it.remove();
          }
        }
      }
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new DefaultLEDCountDown());
  }

  public class PriorityColor implements Comparator<PriorityColor>, Comparable<PriorityColor> {

    public double color;
    public int priority;
    public int loopCount = -10;

    public PriorityColor(double color, int priority) {
      this.color = color;
      this.priority = priority;
    }

    public int compare(PriorityColor p1 , PriorityColor p2) {
        if(p1.priority>= p2.priority) {
          return -1;
        } else {
          return 1;
        }
    }

    public int compareTo(PriorityColor p2) {
      return compare(this, p2);
    }
  }

}
