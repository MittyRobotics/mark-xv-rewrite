package frc.robot.controllers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.drive.DriveTeleOp;
import frc.robot.intake.Intake;
import frc.robot.lift.Lift;

import static edu.wpi.first.wpilibj.GenericHID.Hand.kLeft;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

public final class Controller extends Thread {
	@Override
	public void run() {
		final XboxController xboxController = new XboxController(0);

		final Joystick[] joysticks = new Joystick[2];
		for (int i = 0; i < joysticks.length; i++) {
			joysticks[i] = new Joystick(i + 1);
		}

		boolean isIntakeClosed = true;
		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isOperatorControl()) {
			// DRIVING CONTROLS

			// Left Side Tank drive
			if (xboxController.getY(kLeft) < -0.05 || xboxController.getY(kLeft) > 0.05) {
				DriveTeleOp.setLeft(-xboxController.getY(kLeft));
			} else {
				DriveTeleOp.setLeft(0);
			}

			// Right Side Tank drive
			if (xboxController.getY(kRight) < -0.05 || xboxController.getY(kRight) > 0.05) {
				DriveTeleOp.setRight(-xboxController.getY(kRight));
			} else {
				DriveTeleOp.setRight(0);
			}

			// Ramping Toggle
			if (xboxController.getBumper(kRight)) {
				DriveTeleOp.setGear(0);
			} else if (xboxController.getBumper(kLeft)) {
				DriveTeleOp.setGear(1);
			}


			// OPERATOR CONTROLS

			// Lift
			if (Math.abs(joysticks[1].getY()) > 0.1) {
				Lift.set(-joysticks[1].getY());
			} else {
				Lift.set(0);
			}


			// Intake
			if (joysticks[0].getRawButton(3)) {
				Intake.raise();
			} else if (joysticks[0].getRawButton(2)) {
				Intake.lower();
			}

			// Intake Rollers
			if (joysticks[0].getY() > 0.05) {
				Intake.extake();
			} else if (joysticks[0].getY() < -0.05) {
				Intake.intake();
			} else {
				Intake.halt();
			}

			// Intake Arms
			if (joysticks[1].getTrigger()) {
				Intake.toggle();
			} else if (joysticks[0].getTrigger()) {
				Intake.soft();
			}


			// THROTTLE THREAD
			try {
				sleep(10);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
