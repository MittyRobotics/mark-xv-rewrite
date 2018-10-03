package org.usfirst.frc.team1351.robot.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Lift {
	public static final double SWITCH_HEIGHT = 10; // TODO
	public static final double SCALE_HEIGHT = 100; // TODO

	private static final int TALON_IDS[] = {9, 5};
	private static final int TICKS_PER_INCH = 651;

	private static final FeedbackDevice encoder = FeedbackDevice.QuadEncoder;
	private static WPI_TalonSRX[] talons;
	private static boolean shouldRun = false;
	private static double setpoint = 0;
	private static Thread thread = new Thread(Lift::liftThread);

	public static void init() {
		talons = new WPI_TalonSRX[TALON_IDS.length];
		for (int i = 0; i < TALON_IDS.length; i++) {
			talons[i] = new WPI_TalonSRX(TALON_IDS[i]);
			if (i > 0) {
				talons[i].set(ControlMode.Follower, TALON_IDS[0]);
			}
		}
		talons[0].config_kP(0, 1, 0);
		talons[0].config_kI(0, 0, 0);
		talons[0].config_kD(0, 0, 0);
		talons[0].config_kF(0, 0, 0);
		talons[0].configSelectedFeedbackSensor(encoder, 0, 0);
	}

	public static void setSetpoint(final double setpoint) {
		Lift.setpoint = setpoint;
	}

	public static double getSetpoint(final double setpoint) {
		return setpoint;
	}

	public static void start() {
		shouldRun = true;
		if (!thread.isAlive() && !thread.isInterrupted()) {
			thread.start();
		}
	}

	public static void stop() {
		shouldRun = false;
	}

	private static void liftThread() {
		while (shouldRun) {
			talons[0].set(ControlMode.Position, setpoint * TICKS_PER_INCH);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
