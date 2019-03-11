/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.climber.DriveClimberWheels;
import frc.robot.commands.climber.LiftRobotAuto;
import frc.robot.commands.climber.DropArms;
import frc.robot.commands.climber.LiftClimber;

public class HabThreeClimb extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HabThreeClimb() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    addSequential(new DropArms());
    addSequential(new LiftRobotAuto(0));
    addSequential(new DriveClimberWheels());
    addSequential(new LiftClimber(0));
    addSequential(new AutoDrivetrain(0));

    //Lift Robot auto combines elevator and climber wheels
    //TODO add a speed to the LRA LC

    
    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
