package org.usfirst.frc.team1351.robot.autonomous;

public class Autonomous {
	public static void init() {
		AutonomousChooser.init();
	}

	public static void run() {
		AutonomousChooser.choose();
	}
}
