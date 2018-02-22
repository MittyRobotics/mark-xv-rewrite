package org.usfirst.frc.team1351.robot.Controllers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import org.usfirst.frc.team1351.robot.Drive.DriveTeleOp;
import org.usfirst.frc.team1351.robot.Winch.WinchAuton;
import org.usfirst.frc.team1351.robot.Winch.WinchTeleOp;

import static edu.wpi.first.wpilibj.GenericHID.Hand.kLeft;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

class Controller implements Runnable {
	@Override
	public void run() {
		XboxController xboxController = new XboxController(0);

		Joystick[] joysticks = new Joystick[2];
		for (int i = 0; i < joysticks.length; i++) {
			joysticks[i] = new Joystick(i + 1);
		}

		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isOperatorControl()) {
			// DRIVING CONTROLS
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





			// OPERATOR CONTROLS
			if (joysticks[0].getTrigger()) {
				WinchTeleOp.climb();
			}

			if (joysticks[1].getTrigger()) {
				WinchAuton.climb();
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
