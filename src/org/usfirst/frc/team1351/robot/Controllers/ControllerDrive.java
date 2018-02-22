package org.usfirst.frc.team1351.robot.Controllers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import org.usfirst.frc.team1351.robot.Drive.DriveTeleOp;

import static edu.wpi.first.wpilibj.GenericHID.Hand.kLeft;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

class ControllerDrive implements Runnable {
	@Override
	public void run() {
		XboxController xboxController = new XboxController(0);

		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isOperatorControl()) {
			//Left Side Tank Drive
			if (xboxController.getY(kLeft) < -0.05 || xboxController.getY(kLeft) > 0.05) {
				double yValue = xboxController.getY(kLeft);
				DriveTeleOp.setLeft(yValue);
			} else {
				DriveTeleOp.setLeft(0);
			}

			//Right Side Tank Drive
			if (xboxController.getY(kRight) < -0.05 || xboxController.getY(kRight) > 0.05) {
				double yValue = xboxController.getY(kLeft);
				DriveTeleOp.setRight(yValue);
			} else {
				DriveTeleOp.setRight(0);
			}

			//Manual Gear Switch
			if (xboxController.getAButton()) {
				DriveTeleOp.setGear(1);
			} else if (xboxController.getBButton()) {
				DriveTeleOp.setGear(0);
			}

			//Manual Gear Hold : Will Override Switch Mode
			if (xboxController.getTriggerAxis(kRight) >= 0.2) {
				DriveTeleOp.setGear(0);
			} else {
				DriveTeleOp.setGear(1);
			}

			//Ramping Toggle On
			if (xboxController.getXButton() && xboxController.getStickButton(kLeft)) {
				DriveTeleOp.setRamp(0.5);
			}

			//Ramping Toggle Off
			if (xboxController.getYButton() && xboxController.getStickButton(kLeft)) {
				DriveTeleOp.setRamp(0);
			}

			//Throttle Thread
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
