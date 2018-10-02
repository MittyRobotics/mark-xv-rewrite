package org.usfirst.frc.team1351.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class AutonomousChooser {
	static void init() {
		SmartDashboard.putNumber("Starting Spot", 0);
		SmartDashboard.putBoolean("Scale:", false);
	}

	static void choose() {
		String matchConfigMessage = DriverStation.getInstance().getGameSpecificMessage().toLowerCase();
		double startingSpotID = SmartDashboard.getNumber("Starting Spot", 0);
		boolean targetIsSwitch = SmartDashboard.putBoolean("Switch?", false);

		if (targetIsSwitch) {
			if (matchConfigMessage.charAt(0) == 'l') {
				if (startingSpotID == 0) {
					AutonomousPatterns.leftLeftSwitch();
				} else if (startingSpotID == 1) {
					AutonomousPatterns.middleLeftSwitch();
				} else {
					AutonomousPatterns.rightLeftSwitch();
				}
			} else {
				if (startingSpotID == 0) {
					AutonomousPatterns.leftRightSwitch();
				} else if (startingSpotID == 1) {
					AutonomousPatterns.middleLeftSwitch();
				} else {
					AutonomousPatterns.rightLeftSwitch();
				}
			}
		} else {
			if (matchConfigMessage.charAt(1) == 'l') {
				if (startingSpotID == 0) {
					AutonomousPatterns.leftLeftScale();
				} else if (startingSpotID == 1) {
					AutonomousPatterns.middleLeftScale();
				} else {
					AutonomousPatterns.rightLeftScale();
				}
			} else {
				if (startingSpotID == 0) {
					AutonomousPatterns.leftRightScale();
				} else if (startingSpotID == 1) {
					AutonomousPatterns.middleRightScale();
				} else {
					AutonomousPatterns.rightRightScale();
				}
			}
		}
	}
}
