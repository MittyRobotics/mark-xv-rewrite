package org.usfirst.frc.team1351.robot.controllers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import org.usfirst.frc.team1351.robot.drive.DriveTeleOp;

import static edu.wpi.first.wpilibj.GenericHID.Hand.kLeft;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

public class Controller implements Runnable {
	@Override
	public void run() {
		final XboxController xboxController = new XboxController(0);

		final Joystick[] joysticks = new Joystick[2];
		for (int i = 0; i < joysticks.length; i++) {
			joysticks[i] = new Joystick(i + 1);
		}

		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isOperatorControl()) {
			synchronized (this) {
				// DRIVING CONTROLS

				// Left Side Tank drive
				if (xboxController.getY(kLeft) < -0.05 || xboxController.getY(kLeft) > 0.05) {
					DriveTeleOp.setLeft(xboxController.getY(kLeft));
				} else {
					DriveTeleOp.setLeft(0);
				}

				// Right Side Tank drive
				if (xboxController.getY(kRight) < -0.05 || xboxController.getY(kRight) > 0.05) {
					DriveTeleOp.setRight(xboxController.getY(kLeft));
				} else {
					DriveTeleOp.setRight(0);
				}

				// Manual Gear Hold : Will Override Switch Mode
				if (xboxController.getTriggerAxis(kRight) >= 0.2) {
					DriveTeleOp.setGear(0);
				} else {
					DriveTeleOp.setGear(1);
				}

				// Ramping Toggle On
				if (xboxController.getBumper(kRight)) {
					DriveTeleOp.setRamp(0.5);
				}

				// Ramping Toggle Off
				if (xboxController.getBumper(kLeft)) {
					DriveTeleOp.setRamp(0);
				}

				// OPERATOR CONTROLS
				// TODO Stuff


				// THROTTLE THREAD
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
