package org.usfirst.frc.team1351.robot.Autonomous;

class AutonomousChooser {
	static void init() {
		//TODO Initialize Driver Station Inputs
	}

	static void choose() {
		byte command;

		command = 0; //TODO Change this to Driver Station Input

		AutonomousRunner.runCommand(command);
	}
}
