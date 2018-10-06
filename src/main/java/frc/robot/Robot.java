package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SampleRobot;
import frc.robot.autonomous.Autonomous;
import frc.robot.autonomous.AutonomousPatterns;
import frc.robot.controllers.Controller;
import frc.robot.drive.Drive;
import frc.robot.intake.Intake;
import frc.robot.lift.Lift;

@SuppressWarnings("deprecation")
public final class Robot extends SampleRobot {
	private final Compressor compressor;

	public Robot() {
		compressor = new Compressor();
	}

	@Override
	public final void robotInit() {
		System.out.println("1.0.0");

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

		// Camera
		final UsbCamera usbCamera = new UsbCamera("Camera", 0);
		CameraServer.getInstance().addCamera(usbCamera);


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
		AutonomousPatterns.redLeftLeftForward(true);
	}

	@Override
	public final void operatorControl() {
		enable();
		Lift.start();
		new Controller().start();
	}

	@Override
	public final void test() {
		compressor.start();
		Lift.configPID();
		Lift.configAmpLimit();
		Lift.start();
		Lift.setSetpoint(24);
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
