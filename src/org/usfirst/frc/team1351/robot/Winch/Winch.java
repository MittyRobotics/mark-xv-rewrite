package org.usfirst.frc.team1351.robot.Winch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

class Winch {
	private static final FeedbackDevice ENCODER = FeedbackDevice.QuadEncoder;

	private static WPI_TalonSRX[] talons;

	private static final byte[] WINCHTALONS = {4, 5};

	public static void init() {
		talons = new WPI_TalonSRX[WINCHTALONS.length];
		for (int i = 0; i < talons.length; i++) {
			talons[i] = new WPI_TalonSRX(WINCHTALONS[i]);
		}

		talons[WINCHTALONS[0]].set(ControlMode.PercentOutput, 0);
		for (int i = 1; i < WINCHTALONS.length; i++) {
			talons[i].set(ControlMode.Follower, talons[0].getDeviceID());
		}

		for (byte LEFTDRIVETALON : WINCHTALONS) {
			talons[LEFTDRIVETALON].setInverted(true);
		}
	}

	static void set(ControlMode controlMode, double value) {
		talons[0].set(controlMode, value);
	}

	/**
	 * Sets the left and right encoder talons PIDF values and default encoder.
	 *
	 * @param P Proportional Constant
	 * @param I Integral Constant
	 * @param D Derivative Constant
	 */
	static void setPIDF(double P, double I, double D) {
		talons[0].config_kP(0, P, 0);
		talons[0].config_kI(0, I, 0);
		talons[0].config_kD(0, D, 0);
		talons[0].config_kF(0, 0, 0);
		talons[0].configSelectedFeedbackSensor(ENCODER, 0, 1000);
	}

	static int getWinchEncoder() {
		return talons[0].getSelectedSensorPosition(0);
	}

	static int getWinchError() {
		return talons[0].getClosedLoopError(0);
	}

	static int getWinchTarget() {
		return talons[0].getClosedLoopTarget(0);
	}
}
