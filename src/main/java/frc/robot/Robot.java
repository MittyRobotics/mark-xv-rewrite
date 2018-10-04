package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.autonomous.Autonomous;
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
		final XboxController xboxController = new XboxController(1);
		while (DriverStation.getInstance().isEnabled()) {
			if (xboxController.getXButton()) {
				Intake.hold();
			} else if (xboxController.getYButton()) {
				Intake.lower();
			} else if (xboxController.getAButton()) {
				Intake.release();
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
