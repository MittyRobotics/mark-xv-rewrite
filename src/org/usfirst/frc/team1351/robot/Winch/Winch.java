package org.usfirst.frc.team1351.robot.Winch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Ultrasonic;

public class Winch {
	private static final FeedbackDevice ENCODER = FeedbackDevice.QuadEncoder;
	private static final int[] WINCHTALONS = {4, 5};
	private static final double WINCHSIZE = 0.5f;

	private static WPI_TalonSRX[] talons;
	private static Ultrasonic ultrasonic;


	public static void init() {
		talons = new WPI_TalonSRX[WINCHTALONS.length];
		for (int i = 0; i < talons.length; i++) {
			talons[i] = new WPI_TalonSRX(WINCHTALONS[i]);
		}

		talons[WINCHTALONS[0]].set(ControlMode.PercentOutput, 0);
		for (int i = 1; i < WINCHTALONS.length; i++) {
			talons[i].set(ControlMode.Follower, talons[0].getDeviceID());
		}

		for (int LEFTDRIVETALON : WINCHTALONS) {
			talons[LEFTDRIVETALON].setInverted(true);
		}

		ultrasonic = new Ultrasonic(0, 1, Ultrasonic.Unit.kInches);
	}

	static void set(ControlMode controlMode, double value) {
		talons[0].set(controlMode, value);
	}

	static double getUltrasonic() {
		return ultrasonic.getRangeInches();
	}

	static double getWinchSize() {
		return WINCHSIZE;
	}
}
