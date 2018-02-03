package org.usfirst.frc.team1351.robot.Threading;

public class ThreadManager {
	private static volatile boolean isTeleOp;

	private static Thread broadcast;

	public static void init() {
		isTeleOp = false;
		broadcast = new Thread(ThreadManager::broadcast);
	}

	public static void updateIsTeleOp(boolean value) {
		isTeleOp = value;
		broadcast();
	}

	private static void broadcast() {

	}
}
