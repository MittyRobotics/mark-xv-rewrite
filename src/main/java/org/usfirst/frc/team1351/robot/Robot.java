package org.usfirst.frc.team1351.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;
import org.usfirst.frc.team1351.robot.autonomous.Autonomous;
import org.usfirst.frc.team1351.robot.controllers.Controller;
import org.usfirst.frc.team1351.robot.drive.Drive;
import org.usfirst.frc.team1351.robot.intake.Intake;
import org.usfirst.frc.team1351.robot.lift.Lift;

@SuppressWarnings("deprecation")
public final class Robot extends SampleRobot {
	private final Compressor compressor;

	public Robot() {
		compressor = new Compressor();
	}

	@Override
	public final void robotInit() {
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
	public final void autonomous() {
		enable();
		Autonomous.run();
	}

	@Override
	public final void operatorControl() {
		enable();
		Lift.start();
		new Controller().start();
	}

	@Override
	public final void test() {
		Lift.setSetpoint(24);
		Lift.start();
		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isTest()) {
			System.out.println(Lift.getSensor());
			try {
				Thread.sleep(100);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public final void disabled() {
		Lift.stop();
		compressor.stop();
	}

	private void enable() {
		compressor.start();
	}
}
