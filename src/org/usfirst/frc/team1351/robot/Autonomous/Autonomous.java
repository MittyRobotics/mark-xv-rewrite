package org.usfirst.frc.team1351.robot.Autonomous;

public class Autonomous {
	public static void init() {
		AutonomousChooser.init();
		AutonomousRunner.init();
	}

	public static void run() {
		AutonomousChooser.choose();
	}
}
