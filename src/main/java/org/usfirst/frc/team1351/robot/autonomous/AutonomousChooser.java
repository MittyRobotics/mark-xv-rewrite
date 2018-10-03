package org.usfirst.frc.team1351.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class AutonomousChooser {
	static void init() {
		SmartDashboard.putBoolean("Blue", false);
		SmartDashboard.putNumber("Starting Spot", 0);
	}

	static void choose() {
		final String matchConfigMessage = DriverStation.getInstance().getGameSpecificMessage().toLowerCase();
		if (SmartDashboard.getBoolean("Blue", false)) {
			switch ((int) SmartDashboard.getNumber("Starting Spot", 0)) {
				case 0:
					switch (matchConfigMessage.charAt(0)) {
						case 'l':
							AutonomousPatterns.redLeftLeftSwitch();
							break;
						case 'r':
							AutonomousPatterns.redLeftLeftForward();
							break;
					}
					break;
				case 1:
					switch (matchConfigMessage.charAt(0)) {
						case 'l':
							AutonomousPatterns.redMiddleLeftSwitch();
							break;
						case 'r':
							AutonomousPatterns.redMiddleRightSwitch();
							break;
					}
					break;
				case 2:
					switch (matchConfigMessage.charAt(0)) {
						case 'l':
							AutonomousPatterns.redRightRightForward();
							break;
						case 'r':
							AutonomousPatterns.redRightRightSwitch();
							break;
					}
					break;
			}
		} else {
			switch ((int) SmartDashboard.getNumber("Starting Spot", 0)) {
				case 0:
					switch (matchConfigMessage.charAt(0)) {
						case 'l':
							AutonomousPatterns.blueLeftLeftSwitch();
							break;
						case 'r':
							AutonomousPatterns.blueLeftLeftForward();
							break;
					}
					break;
				case 1:
					switch (matchConfigMessage.charAt(0)) {
						case 'l':
							AutonomousPatterns.blueMiddleLeftSwitch();
							break;
						case 'r':
							AutonomousPatterns.blueMiddleRightSwitch();
							break;
					}
					break;
				case 2:
					switch (matchConfigMessage.charAt(0)) {
						case 'l':
							AutonomousPatterns.blueRightRightForward();
							break;
						case 'r':
							AutonomousPatterns.blueRightRightSwitch();
							break;
					}
					break;
			}
		}
	}
}
