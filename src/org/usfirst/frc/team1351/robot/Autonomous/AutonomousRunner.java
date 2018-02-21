package org.usfirst.frc.team1351.robot.Autonomous;

import java.util.HashMap;
import java.util.Map;

public class AutonomousRunner {
	private static Map<Byte, Thread> autonomousPatterns = new HashMap<>();

	public static void init() {
		autonomousPatterns.put((byte) 0, new Thread(AutonomousPatterns::leftLeftSwitch));
		autonomousPatterns.put((byte) 1, new Thread(AutonomousPatterns::leftRightSwitch));
		autonomousPatterns.put((byte) 2, new Thread(AutonomousPatterns::middleLeftSwitch));
		autonomousPatterns.put((byte) 3, new Thread(AutonomousPatterns::middleRightSwitch));
		autonomousPatterns.put((byte) 4, new Thread(AutonomousPatterns::rightLeftSwitch));
		autonomousPatterns.put((byte) 5, new Thread(AutonomousPatterns::rightRightSwitch));
		autonomousPatterns.put((byte) 6, new Thread(AutonomousPatterns::leftLeftScale));
		autonomousPatterns.put((byte) 7, new Thread(AutonomousPatterns::leftRightScale));
		autonomousPatterns.put((byte) 8, new Thread(AutonomousPatterns::middleLeftScale));
		autonomousPatterns.put((byte) 9, new Thread(AutonomousPatterns::middleRightScale));
		autonomousPatterns.put((byte) 10, new Thread(AutonomousPatterns::rightLeftScale));
		autonomousPatterns.put((byte) 11, new Thread(AutonomousPatterns::rightRightScale));
	}

	public static void runCommand(byte commandID) {
		autonomousPatterns.get(commandID).start();
	}
}
