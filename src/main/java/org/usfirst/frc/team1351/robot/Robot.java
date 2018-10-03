package org.usfirst.frc.team1351.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SampleRobot;
import org.usfirst.frc.team1351.robot.autonomous.Autonomous;
import org.usfirst.frc.team1351.robot.controllers.Controller;
import org.usfirst.frc.team1351.robot.drive.Drive;
import org.usfirst.frc.team1351.robot.drive.DriveAuton;
import org.usfirst.frc.team1351.robot.intake.Intake;
import org.usfirst.frc.team1351.robot.lift.Lift;

public class Robot extends SampleRobot {
	final private Compressor compressor;

	public Robot() {
		compressor = new Compressor();
	}

	@Override
	public void robotInit() {
		// Autonomous
		final Thread autonomousThread = new Thread(Autonomous::init);
		autonomousThread.start();

		// Drive
		final Thread driveThread = new Thread(Drive::init);
		driveThread.start();

		// Lift
		final Thread liftThread = new Thread(Lift::init);
		liftThread.start();

		// Intake
		final Thread intakeThread = new Thread(Intake::init);
		intakeThread.start();


		// Waits for Finish
		try {
			autonomousThread.join();
			driveThread.join();
			liftThread.join();
			intakeThread.join();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void autonomous() {
		enable();
		Autonomous.run();
	}

	@Override
	public void operatorControl() {
		enable();
		new Controller().run();
		Lift.start();
	}

	@Override
	public void test() {
		enable();
	}

	@Override
	public void disabled() {
		Lift.stop();
		compressor.stop();
	}

	private void enable() {
		compressor.start();
	}
}
