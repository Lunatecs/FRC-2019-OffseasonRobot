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
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * Add your docs here.
 */
public class LED extends Subsystem {
  private Spark ledControl = new Spark(RobotMap.LED_PWM_ID);
  
  public static final double SOLID_GREEN = 0.77;
  public static final double SOLID_RED = 0.61;  
  public static final double SOLID_BLUE = 0.87;
  public static final double DEFAULT_COLOR = -0.97;//0.55;

  private PriorityQueue<PriorityColor> queue = null;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public LED(){
    queue = new PriorityQueue<PriorityColor>();
    queue.add(new PriorityColor(DEFAULT_COLOR, 0));
    this.activateDefaultColors();
    
  }

  public void setColor(double colorValue, int priority) {
    PriorityColor p = new PriorityColor(colorValue, priority);
    queue.add(p);
    ledControl.set(queue.peek().color);
  }

  public double getColor() {
    return ledControl.get();
  }

  public void removeColor(double color, int priority) {
    queue.remove(new PriorityColor(color, priority));
    ledControl.set(queue.peek().color);
  }

  public void activateDefaultColors() {
    ledControl.set(LED.DEFAULT_COLOR);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
   // setDefaultCommand(new LEDVisionTracking());
  }

  class PriorityColor implements Comparator<PriorityColor> {

    public double color;
    public int priority;

    public PriorityColor(double color, int priority) {
      this.color = color;
      this.priority = priority;
    }


    public int compare(PriorityColor p1 , PriorityColor p2) {
        if(p1.color>p2.color) {
            return 1;
        } else if((p1.color<p2.color)) {
            return -1;
        } 
        return 0;
    }

  }

}
