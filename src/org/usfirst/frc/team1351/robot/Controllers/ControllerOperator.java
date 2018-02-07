package org.usfirst.frc.team1351.robot.Controllers;

import edu.wpi.first.wpilibj.DriverStation;

public class ControllerOperator {
	//TODO Add Controllers

	static void init() {
		Thread operatorThread = new Thread(ControllerOperator::oThread);
		operatorThread.setName("Operator Controller Thread");
		operatorThread.setPriority(5); //TODO Set Priority
		operatorThread.start();
	}

	private static void oThread() {
		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isOperatorControl()) {
			//TODO Add in Controller Options / Commands
		}
	}
}
