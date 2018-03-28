package org.usfirst.frc.team1351.robot.Winch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Ultrasonic;

public class Winch {
	private static final FeedbackDevice ENCODER = FeedbackDevice.QuadEncoder;
	private static final int[] WINCH_TALONS = {4, 5};
	private static final double WINCH_SIZE = 0.5f;

	private static WPI_TalonSRX[] talons;
	private static Ultrasonic ultrasonic;

	/**
	 * Initializes the winch talons and the Ultrasonic sensor that will be used. Makes the winch talons follow the primary winch talon.
	 */
	public static void init() {
		talons = new WPI_TalonSRX[WINCH_TALONS.length];
		for (int i = 0; i < talons.length; i++) {
			talons[i] = new WPI_TalonSRX(WINCH_TALONS[i]);
		}

		talons[WINCH_TALONS[0]].set(ControlMode.PercentOutput, 0);
		for (int i = 1; i < WINCH_TALONS.length; i++) {
			talons[i].set(ControlMode.Follower, talons[0].getDeviceID());
		}

		for (int LEFTDRIVETALON : WINCH_TALONS) {
			talons[LEFTDRIVETALON].setInverted(true);
		}

		ultrasonic = new Ultrasonic(0, 1, Ultrasonic.Unit.kInches);
	}

	/**
	 * Sets the master winch talon to a set Control Mode and value.
	 * @param controlMode The control mode for the talons
	 * @param value The value the talon should be set to
	 */
	static void set(ControlMode controlMode, double value) {
		talons[0].set(controlMode, value);
	}

	/**
	 * The getter method for the Ultrasonic sensor
	 * @return Ultrasonic sensor
	 */
	static double getUltrasonic() {
		return ultrasonic.getRangeInches();
	}

	/**
	 * The getter method for the parameter of Winch Size.
	 * @return Size of Winch in a double
	 */
	static double getWinchSize() {
		return WINCH_SIZE;
	}
}
