package org.usfirst.frc.team1351.robot.Threading;

public class ThreadHandler {
	private static volatile boolean isEnabled;
	private static volatile boolean isTeleOp;

	private static Thread broadcast;

	public static void init() {
		isTeleOp = false;
		isEnabled = false;
	}

	public static void updateIsTeleOp(boolean value) {
		isTeleOp = value;
	}

	public static boolean getEnabled() {
		return isEnabled;
	}

	public static boolean getTeleOp() {
		return isTeleOp;
	}

	public static void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	public static void setTeleOp(boolean teleOp) {
		isTeleOp = teleOp;
	}
}
