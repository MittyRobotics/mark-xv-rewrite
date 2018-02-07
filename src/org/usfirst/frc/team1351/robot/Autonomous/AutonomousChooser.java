package org.usfirst.frc.team1351.robot.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class AutonomousChooser {
	static void init() {
		SmartDashboard.putNumber("Starting Spot", 0);
		SmartDashboard.putBoolean("Scale?", false);
	}

	static void choose() {
		String matchConfigMessage = DriverStation.getInstance().getGameSpecificMessage().toLowerCase();
		byte startingSpotID = (byte) SmartDashboard.getNumber("Starting Spot", 0);
		boolean targetIsScale = SmartDashboard.putBoolean("Scale?", false);

		byte command;

		command = 0; //TODO Change this to Driver Station Input

		AutonomousRunner.runCommand(command);
	}
}
