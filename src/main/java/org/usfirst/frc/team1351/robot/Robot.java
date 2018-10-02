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
		Autonomous.init();

		// Drive
		Drive.init();
		DriveAuton.init();

		// Lift
		Lift.init();

		// Intake
		Intake.init();
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
	}

	@Override
	public void test() {
		enable();
	}

	@Override
	public void disabled() {
		compressor.stop();
	}

	private void enable() {
		compressor.start();
	}
}
