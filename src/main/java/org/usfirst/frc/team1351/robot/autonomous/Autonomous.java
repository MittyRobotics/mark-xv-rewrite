package org.usfirst.frc.team1351.robot.autonomous;

public final class Autonomous {
	public static void init() {
		AutonomousChooser.init();
	}

	public static void run() {
		AutonomousChooser.choose();
	}
}
