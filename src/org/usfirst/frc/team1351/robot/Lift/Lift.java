package org.usfirst.frc.team1351.robot.Lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

class Lift {
	private static final int[] LIFT_TALONS = {6, 7};
	private static final int[] LIMIT_SWITCHES = {0, 1, 2};

	private static final FeedbackDevice encoder = FeedbackDevice.QuadEncoder;

	private static WPI_TalonSRX[] talons;
	private static DigitalInput[] limitSwitches;

	/**
	 * Initializes the lift talons and limit switches.
	 */
	//TODO: Set talon[0] as master
	public static void init() {
		talons = new WPI_TalonSRX[LIFT_TALONS.length];
		for (int i = 0; i < LIFT_TALONS.length; i++) {
			talons[i] = new WPI_TalonSRX(LIFT_TALONS[i]);
		}

		limitSwitches = new DigitalInput[LIMIT_SWITCHES.length];
		for (int i = 0; i < LIMIT_SWITCHES.length; i++) {
			limitSwitches[i] = new DigitalInput(i); // TODO Confirm Offset
		}
	}

	/**
	 * Sets the master talon to a control mode and value
	 *
	 * @param controlMode Control Mode that value should be run in
	 * @param value       Value that you want to set motor to
	 */
	static void set(ControlMode controlMode, double value) {
		talons[0].set(controlMode, value);
	}

	/**
	 * TODO: Finish this method
	 * WIP
	 *
	 * @return
	 */
	public static boolean checkLimitSwitches() {
		return true;
	}

	static void setPIDF(double P, double I, double D) {
		talons[0].config_kP(0, P, 0);
		talons[0].config_kI(0, I, 0);
		talons[0].config_kD(0, D, 0);
		talons[0].config_kF(0, 0, 0);
		talons[0].configSelectedFeedbackSensor(encoder, 0, 1000);
	}

	public static WPI_TalonSRX getTalonInstance() {
		return talons[0];
	}
}
