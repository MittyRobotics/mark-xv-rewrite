package org.usfirst.frc.team1351.robot.autonomous;

import org.usfirst.frc.team1351.robot.drive.DriveAuton;
import org.usfirst.frc.team1351.robot.lift.Lift;

/**
 * Class that holds all of the patterns for autonomous.
 * <p>
 * Naming Convention:
 * First Term: Starting Color
 * Second Term: Starting Side
 * Third Term: Target Side
 * Fourth Term: Target Structure
 */
class AutonomousPatterns {
	static void redLeftLeftSwitch() {
		Lift.setSetpoint();
		DriveAuton.move(FieldSpecifics.RED_LENGTH_TO_MIDDLE_OF_SWITCH - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FROM_LEFT_WALL_TO_SWITCH - FieldSpecifics.RED_LENGTH_FROM_LEFT_WALL_TO_CORNER_BEND - RobotSpecifics.SIDE_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void redLeftRightSwitch() {

	}

	static void redMiddleLeftSwitch() {

	}

	static void leftRightSwitch() {

	}

	static void middleRightSwitch() {

	}

	static void rightRightSwitch() {

	}

	static void rightLeftSwitch() {

	}

	static void rightLeftScale() {

	}

	static void rightRightScale() {

	}

	static void leftLeftScale() {

	}

	static void leftRightScale() {

	}

	public static void middleLeftScale() {

	}

	public static void middleRightScale() {

	}
}
