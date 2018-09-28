package org.usfirst.frc.team1351.robot.autonomous;

import org.usfirst.frc.team1351.robot.drive.DriveAuton;

/**
 * Class that holds all of the patterns for autonomous.
 *
 * Naming Convention:
 * First Term: Starting Side
 * Second Term: Target Side
 * Third Term: Target Structure
 */
class AutonomousPatterns {
	// TODO Confirm All Macros

	//Naming Convention
	/*
	First Term: Starting Side
	Second Term: Target Side
	Third Term: Target Structure
	 */

  static void leftLeftSwitch() {
		DriveAuton.move(12);
		DriveAuton.turn(-90);
		DriveAuton.move(20);
		//IntakeAuton.release();
	}

	static void middleLeftSwitch() {
		DriveAuton.move(12);
		DriveAuton.turn(30);
		DriveAuton.move(115);
		DriveAuton.turn(-30);
		//IntakeAuton.release();
	}

	static void leftRightSwitch() {
		DriveAuton.move(12);
		DriveAuton.turn(-37);
		DriveAuton.move(165);
		DriveAuton.turn(37);
		//IntakeAuton.release();
	}

	static void middleRightSwitch() {
		DriveAuton.move(12);
		DriveAuton.turn(-40);
		DriveAuton.move(128);
		DriveAuton.turn(40);
		//IntakeAuton.release();
	}

	static void rightRightSwitch() {
		DriveAuton.move(141);
		DriveAuton.turn(90);
		DriveAuton.move(20);
		//IntakeAuton.release();
	}

	static void rightLeftSwitch() {
		DriveAuton.move(18);
		DriveAuton.turn(63);
		DriveAuton.move(187);
		DriveAuton.turn(-63);
		//IntakeAuton.release();
	}

	static void rightLeftScale() {
		DriveAuton.move(221);
		DriveAuton.turn(90);
		DriveAuton.move(162);
		DriveAuton.turn(-90);
		DriveAuton.move(38);
		//IntakeAuton.release();
	}

	static void rightRightScale() {
		DriveAuton.move(283);
		DriveAuton.turn(90);
		DriveAuton.move(1);
		//IntakeAuton.release();
	}

	static void leftLeftScale() {
		DriveAuton.move(283);
		DriveAuton.turn(-90);
		DriveAuton.move(1);
		//IntakeAuton.release();
	}

	static void leftRightScale() {
		DriveAuton.move(221);
		DriveAuton.turn(-90);
		DriveAuton.move(162);
		DriveAuton.turn(90);
		DriveAuton.move(38);
		//IntakeAuton.release();
	}

	public static void middleLeftScale() {
		//TODO Fill Out middleLeftScale
	}

	public static void middleRightScale() {
		//TODO Fill Out middleRightScale
	}
}
