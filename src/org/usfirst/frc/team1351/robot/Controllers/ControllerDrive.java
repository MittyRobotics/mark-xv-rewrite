package org.usfirst.frc.team1351.robot.Controllers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import org.usfirst.frc.team1351.robot.Drive.DriveTeleOp;

import static edu.wpi.first.wpilibj.GenericHID.Hand.kLeft;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

class ControllerDrive {
	private static final XboxController controller = new XboxController(0);

	static void init() {
		//TODO: No C++ / Talk about the importance of teaching ::
		Thread driverThread = new Thread(ControllerDrive::dThread);
		driverThread.setName("Driver Controller Thread");
		driverThread.setPriority(5); //TODO Set Priority
		driverThread.start();
	}

	private static void dThread() {
		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isOperatorControl()) {
			//Left Side Tank Drive
			if (controller.getY(kLeft) < -0.05 || controller.getY(kLeft) > 0.05) {
				double yValue = controller.getY(kLeft);
				DriveTeleOp.setLeft(yValue);
			} else {
				DriveTeleOp.setLeft(0);
			}

			//Right Side Tank Drive
			if (controller.getY(kRight) < -0.05 || controller.getY(kRight) > 0.05) {
				double yValue = controller.getY(kLeft);
				DriveTeleOp.setRight(yValue);
			} else {
				DriveTeleOp.setRight(0);
			}

			//Manual Gear Switch
			if (controller.getAButton()) {
				DriveTeleOp.setGear((byte) 1);
			} else if (controller.getBButton()) {
				DriveTeleOp.setGear((byte) 0);
			}

			//Manual Gear Hold : Will Override Switch Mode
			if (controller.getTriggerAxis(kRight) >= 0.2) {
				DriveTeleOp.setGear((byte) 0);
			} else {
				DriveTeleOp.setGear((byte) 1);
			}

			//Ramping Toggle On
			if (controller.getXButton() && controller.getStickButton(kLeft)) {
				DriveTeleOp.setRamp(0.5);
			}

			//Ramping Toggle Off
			if (controller.getYButton() && controller.getStickButton(kLeft)) {
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
