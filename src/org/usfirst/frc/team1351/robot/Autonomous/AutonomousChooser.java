package org.usfirst.frc.team1351.robot.Autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class AutonomousChooser {
	static void init() {
		SmartDashboard.putNumber("Starting Spot", 0);
		SmartDashboard.putBoolean("Scale?", false);
	}
	//TODO: Change all of this into a simpler method. READABLE!
	static void choose() {
		String matchConfigMessage = DriverStation.getInstance().getGameSpecificMessage().toLowerCase();
		double startingSpotID = SmartDashboard.getNumber("Starting Spot", 0);
		boolean targetIsScale = SmartDashboard.putBoolean("Scale?", false);

		int command;

		if (targetIsScale) {
			command = 6;    // Range: 6 - 11
			if (matchConfigMessage.charAt(1) == 'r') {
				command++;  // Range: 7, 9, 11  ELSE  Range: 6, 8, 10
			}
		} else {
			command = 0;    // Range: 0 - 5
			if (matchConfigMessage.charAt(0) == 'r') {
				command++;  // Range: 1, 3, 5  ELSE  Range: 0, 2, 4
			} else {
				command = command;
			}
		}
		command += 2 * startingSpotID;  // Chooses Either the First, Second, or Third Number in the Range Based on Starting Spot

		AutonomousRunner.runCommand(command);
	}
}
