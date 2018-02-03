package org.usfirst.frc.team1351.robot.Autonomous;

import java.util.Map;
import java.util.HashMap;

public class AutonomousRunner {
	private static Map<Byte, Thread> autonomousPatterns = new HashMap<>();

	public static void init() {
		//autonomousPatterns.put(0, new Thread(this::forwardTwentyFour));
	}

	public static void runCommand(byte commandID) {
		//autonomousPatterns.get(commandID).start();
	}

	/*
	private static void forwardTwentyFour() {
		AutonDrive.forward(24);
	}
	 */
}
