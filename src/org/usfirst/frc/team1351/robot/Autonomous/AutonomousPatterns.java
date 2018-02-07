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
		DriveAuton.forward(12);
		DriveAuton.turnRight(90);
		DriveAuton.forward(20);
		//TODO Release
	}

	static void middleLeftSwitch() {
		DriveAuton.forward(12);
		DriveAuton.turnLeft(30);
		DriveAuton.forward(115);
		DriveAuton.turnRight(30);
		//TODO Realease
	}

	static void leftRightSwitch() {
		DriveAuton.forward(12);
		DriveAuton.turnRight(37);
		DriveAuton.forward(165);
		DriveAuton.turnLeft(37);
		//TODO Release
	}

	static void middleRightSwitch() {
		DriveAuton.forward(12);
		DriveAuton.turnRight(40);
		DriveAuton.forward(128);
		DriveAuton.turnLeft(40);
		//TODO Release
	}

	static void rightRightSwitch() {
		DriveAuton.forward(141);
		DriveAuton.turnLeft(90);
		DriveAuton.forward(20);
		//TODO Release
	}

	static void rightLeftSwitch() {
		DriveAuton.forward(18);
		DriveAuton.turnLeft(63);
		DriveAuton.forward(187);
		DriveAuton.turnRight(63);
		//TODO Release
	}

	static void rightLeftScale() {
		DriveAuton.forward(221);
		DriveAuton.turnLeft(90);
		DriveAuton.forward(162);
		DriveAuton.turnRight(90);
		DriveAuton.forward(38);
		//TODO Release
	}

	static void rightRightScale() {
		DriveAuton.forward(283);
		DriveAuton.turnLeft(90);
		DriveAuton.forward(1);
		//Todo Release
	}

	static void leftLeftScale() {
		DriveAuton.forward(283);
		DriveAuton.turnRight(90);
		DriveAuton.forward(1);
		//TODO Release
	}

	static void leftRightScale() {
		DriveAuton.forward(221);
		DriveAuton.turnRight(90);
		DriveAuton.forward(162);
		DriveAuton.turnLeft(90);
		DriveAuton.forward(38);
		//TODO Release
	}

	public static void middleLeftScale() {
		//TODO Fill Out middleLeftScale
	}

	public static void middleRightScale() {
		//TODO Fill Out middleRightScale
	}
}
