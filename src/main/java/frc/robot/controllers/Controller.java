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
				DriveTeleOp.setLeft(xboxController.getY(kLeft));
			} else {
				DriveTeleOp.setLeft(0);
			}

			// Right Side Tank drive
			if (xboxController.getY(kRight) < -0.05 || xboxController.getY(kRight) > 0.05) {
				DriveTeleOp.setRight(xboxController.getY(kRight));
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
			if (Math.abs(joysticks[1].getY()) > 0.05) {
				Lift.setSetpoint(Lift.getSetpoint() + (joysticks[1].getY() * 0.1));
			} else if (joysticks[1].getRawButton(3)) {
				Lift.setSetpoint(0);
			} else if (joysticks[1].getRawButton(4)) {
				Lift.setSetpoint(Lift.SWITCH_HEIGHT); //switch height
			} else if (joysticks[1].getRawButton(5)) {
				Lift.setSetpoint(Lift.SCALE_HEIGHT); //scale height
			} else {
				Lift.setSetpoint(Lift.getSetpoint());
			}

			if (joysticks[0].getY() > 0.05) {
				Intake.extake();
			} else if (joysticks[0].getY() < -0.05) {
				Intake.intake();
			} else {
				Intake.halt();
			}

			if (joysticks[1].getTrigger()) {
				if (isIntakeClosed) {
					Intake.release();
					isIntakeClosed = !isIntakeClosed;
				} else {
					Intake.hold();
					isIntakeClosed = !isIntakeClosed;
				}
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

/* TODO
Tank
Right/Left Bumper-Low/High
High Default
Lift - Buttons
Right - Lift Height
Right Trigger - Intake Open/Close Toggle
Middle Rright Joystick Button -
Left Right Joystick Button - Scale
Right Right Joystick - Switch
Left Joystick - Rollers
 */
