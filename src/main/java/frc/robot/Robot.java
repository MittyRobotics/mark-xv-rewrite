package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SampleRobot;
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
		final Thread[] threads = {
				new Thread(Autonomous::init),
				new Thread(Drive::init),
				new Thread(Lift::init),
				new Thread(Intake::init)
		};

		for (final Thread thread : threads) {
			thread.start();
		}

		CameraServer.getInstance().startAutomaticCapture(0);

		for (final Thread thread : threads) {
			try {
				thread.join();
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
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
		new Controller().start();
	}

	@Override
	public final void test() {
		compressor.start();
	}

	@Override
	public final void disabled() {
		compressor.stop();
	}

	private void enable() {
		compressor.start();
	}
}
