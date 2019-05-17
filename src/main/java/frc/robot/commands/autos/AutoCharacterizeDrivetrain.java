/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.CircularBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


public class AutoCharacterizeDrivetrain extends Command {

	public static enum TestMode {
		QUASI_STATIC, STEP_VOLTAGE;
	}

	public static enum Direction {
		Forward, Backward;
	}

	private final TestMode mode;
	private final Direction direction;
	private static final double STEPSPEED = 0.5;

	public AutoCharacterizeDrivetrain(TestMode mode, Direction direction, double timeout) {
		requires(Robot.drive);
		this.mode = mode;
    	this.direction = direction;
    	setTimeout(timeout);
	}

	private FileWriter fw;

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.drive.setRightEncoder(0);
		Robot.drive.setLeftEncoder(0);

		String name;
		double scale;
		if (direction.equals(Direction.Forward)) {
			name = "Forward";
			scale = 1;
		} else {
			name = "Backward";
			scale = -1;
		}

		String path = "/U/DriveCharacterization/" + name;

		if (mode.equals(TestMode.QUASI_STATIC)) {
			System.out.println("QUASI STATIC");
			System.out.println(Robot.drive.rightCenter_T.configOpenloopRamp(90, 10).name());
			System.out.println(Robot.drive.leftCenter_T.configOpenloopRamp(90, 10).name());
			path = path + "QuasiStatic.csv";
			// voltageStep = 1 / 24.0 / 100.0 * scale;
			Robot.drive.tankDrive(1 * scale, 1 * scale);
		} else {
			System.out.println("STEP");
			System.out.println(Robot.drive.rightCenter_T.configOpenloopRamp(0, 10).name());
			System.out.println(Robot.drive.leftCenter_T.configOpenloopRamp(0, 10).name());
			path = path + "StepVoltage.csv";
			Robot.drive.tankDrive(STEPSPEED * scale, STEPSPEED * scale);
		}
		try {
			File f = new File(path);
			if (f.exists()) {
				f.delete();
			}
			fw = new FileWriter(f, true);
			fw.write("");
			fw.flush();
			fw.write("LeftVolt, LeftVel, LeftAcc, LeftJerk, RightVolt, RightVel, RightAcc, RightJerk\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Velocity is max speed 
	//acceleration is velocity per unit time
	//jerk is acceleration per unit time

	private int i = 0;
	private int length = 3;
	private final CircularBuffer accTimeBuff = new CircularBuffer(length);
	private final CircularBuffer jerkTimeBuff = new CircularBuffer(length);
	private final CircularBuffer leftVelBuff = new CircularBuffer(length);
	private final CircularBuffer rightVelBuff = new CircularBuffer(length);
	private final CircularBuffer leftAccBuff = new CircularBuffer(length);
	private final CircularBuffer rightAccBuff = new CircularBuffer(length);
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double time = Timer.getFPGATimestamp();
		double leftVel = Robot.drive.getLeftEncoderRate();
		double rightVel = Robot.drive.getRightEncoderRate();
		double leftVolt = Robot.drive.leftCenter_T.getMotorOutputVoltage();
		double rightVolt = Robot.drive.rightCenter_T.getMotorOutputVoltage();
		accTimeBuff.addLast(time);
		leftVelBuff.addLast(leftVel);
		rightVelBuff.addLast(rightVel);
		//ask will !!!!!
		if (i < length - 1) {
			i++;
			return;
		}
		double dt = time - accTimeBuff.removeFirst();
		double leftDv = leftVel - leftVelBuff.removeFirst();
		double rightDv = rightVel - rightVelBuff.removeFirst();
		double leftAcc = leftDv / dt;
		double rightAcc = rightDv / dt;
		jerkTimeBuff.addLast(time);
		leftAccBuff.addLast(leftAcc);
		rightAccBuff.addLast(rightAcc);

		accTimeBuff.addLast(time);
		leftVelBuff.addLast(leftVel);
		rightVelBuff.addLast(rightVel);
		//add delay?
		dt = time - accTimeBuff.removeFirst();
		leftDv = leftVel - leftVelBuff.removeFirst();
		rightDv = rightVel - rightVelBuff.removeFirst();
		leftAcc = leftDv / dt;
		rightAcc = rightDv / dt;

		dt = time - jerkTimeBuff.removeFirst();
		double leftDa = leftAcc - leftAccBuff.removeFirst();
		double rightDa = rightAcc - rightAccBuff.removeFirst();
		double leftJerk = leftDa / dt;
		double rightJerk = rightDa/ dt;


		String result = leftVolt + ", " + leftVel + ", " + leftAcc + ", " + leftJerk + ", " + rightVolt + ", " + rightVel + ", "
				+ rightAcc + ", " + rightJerk + "\n";
		try {
			fw.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
    if (this.isTimedOut()) {
      return true;
    }
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drive.tankDrive(0, 0);
		System.out.println(Robot.drive.rightCenter_T.configOpenloopRamp(.25, 10).name());
		System.out.println(Robot.drive.leftCenter_T.configOpenloopRamp(.25, 10).name());
		try {
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}