package frc.robot.autonomous;

import frc.robot.drive.DriveAuton;
import frc.robot.intake.Intake;
import frc.robot.lift.Lift;

/**
 * Class that holds all of the patterns for autonomous.
 * <p>
 * Naming Convention:
 * First Term: Starting Color
 * Second Term: Starting Side
 * Third Term: Target Side
 * Fourth Term: Objective
 * TODO Confirm ALL Patterns
 */
final class AutonomousPatterns {
	static void redLeftLeftSwitch() {
		ready();
		DriveAuton.move(FieldSpecifics.RED_LEFT_LENGTH_TO_MIDDLE_OF_SWITCH - RobotSpecifics.BACK_TO_MIDDLE);
		Lift.setSetpoint(Lift.SWITCH_HEIGHT);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FROM_LEFT_WALL_TO_SWITCH - FieldSpecifics.RED_LENGTH_FROM_LEFT_WALL_TO_CORNER_BEND - RobotSpecifics.SIDE_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
		Intake.release();
	}

	static void redLeftLeftForward(final boolean shouldLift) {
		ready();
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FOR_CROSSING_AUTON_LINE - RobotSpecifics.BACK_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
		if (shouldLift) {
			Lift.setSetpoint(Lift.SCALE_HEIGHT);
		}
	}

	static void redMiddleLeftSwitch() {
		ready();
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(-90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_OF_FIELD_FROM_DRIVER_STATION/2 - (FieldSpecifics.RED_LENGTH_FROM_LEFT_WALL_TO_SWITCH - FieldSpecifics.RED_LENGTH_FROM_LEFT_WALL_TO_CORNER_BEND + FieldSpecifics.RED_WIDTH_FOR_SWITCH/2) + FieldSpecifics.RED_LENGTH_FOR_VAULT_TO_CENTER);
		Lift.setSetpoint(Lift.SWITCH_HEIGHT);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.FRONT_TO_MIDDLE);
		Intake.release();
	}

	static void redMiddleRightSwitch() {
		ready();
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_OF_FIELD_FROM_DRIVER_STATION/2 - (FieldSpecifics.RED_LENGTH_FROM_RIGHT_WALL_TO_SWITCH - FieldSpecifics.RED_LENGTH_FROM_RIGHT_WALL_TO_CORNER_BEND + FieldSpecifics.RED_WIDTH_FOR_SWITCH/2) - FieldSpecifics.RED_LENGTH_FOR_VAULT_TO_CENTER);
		Lift.setSetpoint(Lift.SWITCH_HEIGHT);
		DriveAuton.turn(-90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.FRONT_TO_MIDDLE);
		Intake.release();
	}

	static void redRightRightForward(final boolean shouldLift) {
		ready();
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FOR_CROSSING_AUTON_LINE - RobotSpecifics.BACK_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
		if (shouldLift) {
			Lift.setSetpoint(Lift.SCALE_HEIGHT);
		}
	}

	static void redRightRightSwitch() {
		ready();
		DriveAuton.move(FieldSpecifics.RED_RIGHT_LENGTH_TO_MIDDLE_OF_SWITCH - RobotSpecifics.BACK_TO_MIDDLE);
		Lift.setSetpoint(Lift.SWITCH_HEIGHT);
		DriveAuton.turn(-90);
		DriveAuton.move(FieldSpecifics.RED_LENGTH_FROM_RIGHT_WALL_TO_SWITCH - FieldSpecifics.RED_LENGTH_FROM_RIGHT_WALL_TO_CORNER_BEND - RobotSpecifics.SIDE_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
		Intake.release();
	}

	static void blueLeftLeftSwitch() {
		ready();
		DriveAuton.move(FieldSpecifics.BLUE_LEFT_LENGTH_TO_MIDDLE_OF_SWITCH - RobotSpecifics.BACK_TO_MIDDLE);
		Lift.setSetpoint(Lift.SWITCH_HEIGHT);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FROM_LEFT_WALL_TO_SWITCH - FieldSpecifics.BLUE_LENGTH_FROM_LEFT_WALL_TO_CORNER_BEND - RobotSpecifics.SIDE_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void blueLeftLeftForward(final boolean shouldLift) {
		ready();
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FOR_CROSSING_AUTON_LINE - RobotSpecifics.BACK_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
		if (shouldLift) {
			Lift.setSetpoint(Lift.SCALE_HEIGHT);
		}
	}

	static void blueMiddleLeftSwitch() {
		ready();
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(-90);
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_OF_FIELD_FROM_DRIVER_STATION/2 - (FieldSpecifics.BLUE_LENGTH_FROM_LEFT_WALL_TO_SWITCH - FieldSpecifics.BLUE_LENGTH_FROM_LEFT_WALL_TO_CORNER_BEND + FieldSpecifics.BLUE_WIDTH_FOR_SWITCH/2) + FieldSpecifics.BLUE_LENGTH_FOR_VAULT_TO_CENTER);
		Lift.setSetpoint(Lift.SWITCH_HEIGHT);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void blueMiddleRightSwitch() {
		ready();
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.BACK_TO_MIDDLE);
		DriveAuton.turn(90);
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_OF_FIELD_FROM_DRIVER_STATION/2 - (FieldSpecifics.BLUE_LENGTH_FROM_RIGHT_WALL_TO_SWITCH - FieldSpecifics.BLUE_LENGTH_FROM_RIGHT_WALL_TO_CORNER_BEND + FieldSpecifics.BLUE_WIDTH_FOR_SWITCH/2) - FieldSpecifics.BLUE_LENGTH_FOR_VAULT_TO_CENTER);
		Lift.setSetpoint(Lift.SWITCH_HEIGHT);
		DriveAuton.turn(-90);
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FOR_CROSSING_AUTON_LINE/2 - RobotSpecifics.FRONT_TO_MIDDLE);
	}

	static void blueRightRightForward(final boolean shouldLift) {
		ready();
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FOR_CROSSING_AUTON_LINE - RobotSpecifics.BACK_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
		if (shouldLift) {
			Lift.setSetpoint(Lift.SCALE_HEIGHT);
		}
	}

	static void blueRightRightSwitch() {
		ready();
		DriveAuton.move(FieldSpecifics.BLUE_RIGHT_LENGTH_TO_MIDDLE_OF_SWITCH - RobotSpecifics.BACK_TO_MIDDLE);
		Lift.setSetpoint(Lift.SWITCH_HEIGHT);
		DriveAuton.turn(-90);
		DriveAuton.move(FieldSpecifics.BLUE_LENGTH_FROM_RIGHT_WALL_TO_SWITCH - FieldSpecifics.BLUE_LENGTH_FROM_RIGHT_WALL_TO_CORNER_BEND - RobotSpecifics.SIDE_TO_MIDDLE - RobotSpecifics.FRONT_TO_MIDDLE);
		Intake.release();
	}

	private static void ready() {
		Intake.hold();
		Lift.setSetpoint(0);
		Lift.start();
		Intake.lower();
	}
}
