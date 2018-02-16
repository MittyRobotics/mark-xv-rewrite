package org.usfirst.frc.team1351.robot.Autonomous;

import org.usfirst.frc.team1351.robot.Drive.DriveAuton;

class AutonomousPatterns {

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
		//TODO Release
	}

	static void middleLeftSwitch() {
		DriveAuton.move(12);
		DriveAuton.turn(30);
		DriveAuton.move(115);
		DriveAuton.turn(-30);
		//TODO Realease
	}

	static void leftRightSwitch() {
		DriveAuton.move(12);
		DriveAuton.turn(-37);
		DriveAuton.move(165);
		DriveAuton.turn(37);
		//TODO Release
	}

	static void middleRightSwitch() {
		DriveAuton.move(12);
		DriveAuton.turn(-40);
		DriveAuton.move(128);
		DriveAuton.turn(40);
		//TODO Release
	}

	static void rightRightSwitch() {
		DriveAuton.move(141);
		DriveAuton.turn(90);
		DriveAuton.move(20);
		//TODO Release
	}

	static void rightLeftSwitch() {
		DriveAuton.move(18);
		DriveAuton.turn(63);
		DriveAuton.move(187);
		DriveAuton.turn(-63);
		//TODO Release
	}

	static void rightLeftScale() {
		DriveAuton.move(221);
		DriveAuton.turn(90);
		DriveAuton.move(162);
		DriveAuton.turn(-90);
		DriveAuton.move(38);
		//TODO Release
	}

	static void rightRightScale() {
		DriveAuton.move(283);
		DriveAuton.turn(90);
		DriveAuton.move(1);
		//Todo Release
	}

	static void leftLeftScale() {
		DriveAuton.move(283);
		DriveAuton.turn(-90);
		DriveAuton.move(1);
		//TODO Release
	}

	static void leftRightScale() {
		DriveAuton.move(221);
		DriveAuton.turn(-90);
		DriveAuton.move(162);
		DriveAuton.turn(90);
		DriveAuton.move(38);
		//TODO Release
	}
}
