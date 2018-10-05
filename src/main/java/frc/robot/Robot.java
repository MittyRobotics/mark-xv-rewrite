package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.autonomous.Autonomous;
import frc.robot.controllers.Controller;
import frc.robot.drive.Drive;
import frc.robot.drive.DriveTeleOp;
import frc.robot.intake.Intake;
import frc.robot.lift.Lift;

import static edu.wpi.first.wpilibj.GenericHID.Hand.kLeft;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

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
		compressor.start();
		final XboxController xboxController = new XboxController(0);
		while (DriverStation.getInstance().isEnabled()) {
			// Left Side Tank drive
			DriveTeleOp.setLeft(xboxController.getY(kLeft));
			DriveTeleOp.setRight(xboxController.getY(kRight));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
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
