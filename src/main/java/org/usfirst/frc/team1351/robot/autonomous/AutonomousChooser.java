package org.usfirst.frc.team1351.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

final class AutonomousChooser {
	static void init() {
		SmartDashboard.putBoolean("Blue", false);
		SmartDashboard.putNumber("Starting Spot", 0);
	}

	static void choose() {
		final String matchConfigMessage = DriverStation.getInstance().getGameSpecificMessage().toLowerCase();
		if (SmartDashboard.getBoolean("Blue", false)) {
			switch ((int) SmartDashboard.getNumber("Starting Spot", 0)) {
				case 0:
					if (matchConfigMessage.charAt(0) == 'l') {
						AutonomousPatterns.redLeftLeftSwitch();
					} else {
						AutonomousPatterns.redLeftLeftForward(matchConfigMessage.charAt(1) == 'l');
					}
					break;
				case 1:
					if (matchConfigMessage.charAt(0) == 'l') {
						AutonomousPatterns.redMiddleLeftSwitch();
					} else {
						AutonomousPatterns.redMiddleRightSwitch();
					}
					break;
				case 2:
					if (matchConfigMessage.charAt(0) == 'l') {
						AutonomousPatterns.redRightRightForward(matchConfigMessage.charAt(1) == 'r');
					} else {
						AutonomousPatterns.redRightRightSwitch();
					}
					break;
			}
		} else {
			switch ((int) SmartDashboard.getNumber("Starting Spot", 0)) {
				case 0:
					if (matchConfigMessage.charAt(0) == 'l') {
						AutonomousPatterns.blueLeftLeftSwitch();
					} else {
						AutonomousPatterns.blueLeftLeftForward(matchConfigMessage.charAt(1) == 'l');
					}
					break;
				case 1:
					if (matchConfigMessage.charAt(0) == 'l') {
						AutonomousPatterns.blueMiddleLeftSwitch();
					} else {
						AutonomousPatterns.blueMiddleRightSwitch();
					}
					break;
				case 2:
					if (matchConfigMessage.charAt(0) == 'l') {
						AutonomousPatterns.blueRightRightForward(matchConfigMessage.charAt(1) == 'r');
					} else {
						AutonomousPatterns.blueRightRightSwitch();
					}
					break;
			}
		}
	}
}
