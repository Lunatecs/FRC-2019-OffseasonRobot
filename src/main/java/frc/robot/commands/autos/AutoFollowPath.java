/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj.command.Command;

import java.io.IOException;

import edu.wpi.first.wpilibj.Notifier;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class AutoFollowPath extends Command {

  private static final int TICKS_PER_REV = 4096;
  private static final double WHEEL_DIAMETER = 4.0;
  private static final double MAX_VELOCITY = 10;
  private String pathName = "RedRocketRight";

  private EncoderFollower leftFollower;
  private EncoderFollower rightFollower;
  private Trajectory leftTrajectory;
  private Trajectory rightTrajectory;

  private Notifier followerNotifier;

  public AutoFollowPath(String pathName) {
    this.pathName = pathName;
    requires(Robot.drive);
  }
  //different execute method, but controlled by a timed notifier
  private void followPath() {
    if (leftFollower.isFinished() || rightFollower.isFinished()) {
      followerNotifier.stop();
    } else {
      double leftSpeed = leftFollower.calculate(Robot.drive.getLeftEncoder());
      double rightSpeed = rightFollower.calculate(Robot.drive.getRightEncoder());
      //TODO get angle from gyro correctly
      double heading = Robot.drive.getAngle();
      double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
      double headingDifference = Pathfinder.boundHalfDegrees(desiredHeading - heading);
      double turn = 0.8 * (-1.0/80.0) * headingDifference;
      Robot.drive.tankDrive(leftSpeed + turn, rightSpeed - turn);
    }
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    try {
      leftTrajectory = PathfinderFRC.getTrajectory(this.pathName + ".left");
      rightTrajectory = PathfinderFRC.getTrajectory(this.pathName + ".right");
    } catch (IOException e) {
      e.printStackTrace();
    }

    leftFollower = new EncoderFollower(leftTrajectory);
    rightFollower = new EncoderFollower(rightTrajectory);

    leftFollower.configureEncoder(Robot.drive.getLeftEncoder(), TICKS_PER_REV, WHEEL_DIAMETER);
    leftFollower.configurePIDVA(1.0, 0.0, 0.0, 1/MAX_VELOCITY, 0.0);

    rightFollower.configureEncoder((Robot.drive.getRightEncoder()), TICKS_PER_REV, WHEEL_DIAMETER);
    rightFollower.configurePIDVA(1.0, 0.0, 0.0, 1/MAX_VELOCITY, 0.0);

    followerNotifier = new Notifier(this::followPath);
    followerNotifier.startPeriodic(leftTrajectory.get(0).dt);
  
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    followerNotifier.stop();
    Robot.drive.tankDrive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
