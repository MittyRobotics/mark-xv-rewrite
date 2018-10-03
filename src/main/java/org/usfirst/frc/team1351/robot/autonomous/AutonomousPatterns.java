package org.usfirst.frc.team1351.robot.autonomous;

import org.usfirst.frc.team1351.robot.drive.DriveAuton;
import org.usfirst.frc.team1351.robot.intake.Intake;
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
		Intake.setArmsHardHold();
		DriveAuton.move(FieldSpecifics.RED_LEFT_LENGTH_TO_MIDDLE_OF_SWITCH - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FROM_LEFT_WALL_TO_SWITCH - FieldSpecifics.RED_LENGTH_FROM_LEFT_WALL_TO_CORNER_BEND - RobotSpecifics.SIDE_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void redLeftLeftForward() {
		Intake.setArmsHardHold();
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FOR_CROSSING_AUTON_LINE - RobotSpecifics.BACK_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void redMiddleLeftSwitch() {
		Intake.setArmsHardHold();
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(-90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_OF_FIELD_FROM_DRIVER_STATION/2 - (FieldSpecifics.RED_LENGTH_FROM_LEFT_WALL_TO_SWITCH - FieldSpecifics.RED_LENGTH_FROM_LEFT_WALL_TO_CORNER_BEND + FieldSpecifics.RED_WIDTH_FOR_SWITCH/2) + FieldSpecifics.RED_LENGTH_FOR_VAULT_TO_CENTER);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void redMiddleRightSwitch() {
		Intake.setArmsHardHold();
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_OF_FIELD_FROM_DRIVER_STATION/2 - (FieldSpecifics.RED_LENGTH_FROM_RIGHT_WALL_TO_SWITCH - FieldSpecifics.RED_LENGTH_FROM_RIGHT_WALL_TO_CORNER_BEND + FieldSpecifics.RED_WIDTH_FOR_SWITCH/2) - FieldSpecifics.RED_LENGTH_FOR_VAULT_TO_CENTER);
		DriveAuton.turn(-90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void redRightRightForward() {
		Intake.setArmsHardHold();
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FOR_CROSSING_AUTON_LINE - RobotSpecifics.BACK_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void redRightRightSwitch() {
		Intake.setArmsHardHold();
		DriveAuton.move(FieldSpecifics.RED_RIGHT_LENGTH_TO_MIDDLE_OF_SWITCH - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(-90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FROM_RIGHT_WALL_TO_SWITCH - FieldSpecifics.RED_LENGTH_FROM_RIGHT_WALL_TO_CORNER_BEND - RobotSpecifics.SIDE_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void blueLeftLeftSwitch() {
		Intake.setArmsHardHold();
		DriveAuton.move(FieldSpecifics.BLUE_LEFT_LENGTH_TO_MIDDLE_OF_SWITCH - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FROM_LEFT_WALL_TO_SWITCH - FieldSpecifics.BLUE_LENGTH_FROM_LEFT_WALL_TO_CORNER_BEND - RobotSpecifics.SIDE_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void blueLeftLeftForward() {
		Intake.setArmsHardHold();
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FOR_CROSSING_AUTON_LINE - RobotSpecifics.BACK_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void blueMiddleLeftSwitch() {
		Intake.setArmsHardHold();
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(-90);
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_OF_FIELD_FROM_DRIVER_STATION/2 - (FieldSpecifics.BLUE_LENGTH_FROM_LEFT_WALL_TO_SWITCH - FieldSpecifics.BLUE_LENGTH_FROM_LEFT_WALL_TO_CORNER_BEND + FieldSpecifics.BLUE_WIDTH_FOR_SWITCH/2) + FieldSpecifics.BLUE_LENGTH_FOR_VAULT_TO_CENTER);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void blueMiddleRightSwitch() {
		Intake.setArmsHardHold();
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_OF_FIELD_FROM_DRIVER_STATION/2 - (FieldSpecifics.BLUE_LENGTH_FROM_RIGHT_WALL_TO_SWITCH - FieldSpecifics.BLUE_LENGTH_FROM_RIGHT_WALL_TO_CORNER_BEND + FieldSpecifics.BLUE_WIDTH_FOR_SWITCH/2) - FieldSpecifics.BLUE_LENGTH_FOR_VAULT_TO_CENTER);
		DriveAuton.turn(-90);
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void blueRightRightForward() {
		Intake.setArmsHardHold();
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FOR_CROSSING_AUTON_LINE - RobotSpecifics.BACK_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void blueRightRightSwitch() {
		Intake.setArmsHardHold();
		DriveAuton.move(FieldSpecifics.BLUE_RIGHT_LENGTH_TO_MIDDLE_OF_SWITCH - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(-90);
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FROM_RIGHT_WALL_TO_SWITCH - FieldSpecifics.BLUE_LENGTH_FROM_RIGHT_WALL_TO_CORNER_BEND - RobotSpecifics.SIDE_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
	}
}
