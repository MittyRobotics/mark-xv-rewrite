package org.usfirst.frc.team1351.robot.Controllers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
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
			if (joysticks[0].getRawButton(11)) {
				WinchTeleOp.laden();
			}

			if (joysticks[0].getRawButton(10)) {
				WinchTeleOp.unladen();
			}

			if (joysticks[0].getRawButton(7)) {
				WinchTeleOp.negUnladen();
			}

			if(joysticks[0].getRawButton(6)) {
				WinchTeleOp.fullForward();
			}

			if (joysticks[1].getRawButton(11)) {
				WinchTeleOp.negLaden();
			}

			if (joysticks[1].getRawButton(10)) {
				WinchTeleOp.negUnladen();
			}

			if (joysticks[1].getRawButton(7)) {
				WinchTeleOp.unladen();
			}

			if(joysticks[1].getRawButton(6)) { //hold
				WinchTeleOp.negFullForward();
			}
		}
	}
}
