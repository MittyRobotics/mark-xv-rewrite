package org.usfirst.frc.team1351.robot.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

import static org.usfirst.frc.team1351.robot.lift.LiftConfiguration.TALON_IDS;

class Lift {
	private static final FeedbackDevice encoder = FeedbackDevice.QuadEncoder;

	private static WPI_TalonSRX[] talons;
	private static DigitalInput[] limitSwitches;

	public static void init() {
		talons = new WPI_TalonSRX[TALON_IDS.length];
		for (int i = 0; i < TALON_IDS.length; i++) {
			talons[i] = new WPI_TalonSRX(TALON_IDS[i]);
		}
	}

	static void set(ControlMode controlMode, double value) {
		talons[0].set(controlMode, value);
	}

	static void setPIDF(double P, double I, double D) {
		talons[0].config_kP(0, P, 0);
		talons[0].config_kI(0, I, 0);
		talons[0].config_kD(0, D, 0);
		talons[0].config_kF(0, 0, 0);
		talons[0].configSelectedFeedbackSensor(encoder, 0, 1000);
	}
}
