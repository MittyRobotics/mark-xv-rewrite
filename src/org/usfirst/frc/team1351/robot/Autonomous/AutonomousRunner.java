package org.usfirst.frc.team1351.robot.Autonomous;

import java.util.HashMap;
import java.util.Map;

public class AutonomousRunner {
	private static Map<Integer, Thread> autonomousPatterns = new HashMap<>();

	public static void init() {
		autonomousPatterns.put(0, new Thread(AutonomousPatterns::leftLeftSwitch));
		autonomousPatterns.put(1, new Thread(AutonomousPatterns::leftRightSwitch));
		autonomousPatterns.put(2, new Thread(AutonomousPatterns::middleLeftSwitch));
		autonomousPatterns.put(3, new Thread(AutonomousPatterns::middleRightSwitch));
		autonomousPatterns.put(4, new Thread(AutonomousPatterns::rightLeftSwitch));
		autonomousPatterns.put(5, new Thread(AutonomousPatterns::rightRightSwitch));
		autonomousPatterns.put(6, new Thread(AutonomousPatterns::leftLeftScale));
		autonomousPatterns.put(7, new Thread(AutonomousPatterns::leftRightScale));
		autonomousPatterns.put(8, new Thread(AutonomousPatterns::middleLeftScale));
		autonomousPatterns.put(9, new Thread(AutonomousPatterns::middleRightScale));
		autonomousPatterns.put(10, new Thread(AutonomousPatterns::rightLeftScale));
		autonomousPatterns.put(11, new Thread(AutonomousPatterns::rightRightScale));
	}

	public static void runCommand(byte commandID) {
		autonomousPatterns.get(commandID).start();
	}
}
