package org.usfirst.frc.team1351.robot.Controllers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team1351.robot.Winch.WinchAuton;
import org.usfirst.frc.team1351.robot.Winch.WinchTeleOp;

public class ControllerOperator {
	private static Joystick[] joysticks;

	static void init() {
		Thread operatorThread = new Thread(ControllerOperator::oThread);
		operatorThread.setName("Operator Controller Thread");
		operatorThread.setPriority(5); //TODO Set Priority
		operatorThread.start();

		joysticks = new Joystick[2];
		for (int i = 0; i < joysticks.length; i++) {
			joysticks[i] = new Joystick(i + 1);
		}
	}

	private static void oThread() {
		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isOperatorControl()) {
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
