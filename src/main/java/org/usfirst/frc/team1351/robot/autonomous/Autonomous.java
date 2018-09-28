package org.usfirst.frc.team1351.robot.autonomous;

/**
 * This class is meant for communicating with the other autonomous classes.
 */
public class Autonomous {
	public static void init() {
		AutonomousChooser.init();
	}

	public static void run() {
		AutonomousChooser.choose();
	}
}
