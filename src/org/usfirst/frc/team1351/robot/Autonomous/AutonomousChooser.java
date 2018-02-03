package org.usfirst.frc.team1351.robot.Autonomous;

public class AutonomousChooser {
	public static void init() {
		//TODO Initialize Driver Station Inputs
	}

	public static void choose() {
		byte command;

		command = 0; //TODO Change this to Driver Station Input

		AutonomousRunner.runCommand(command);
	}
}
