package org.usfirst.frc.team1351.robot.Lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

class Lift {
	private static final int[] LIFT_TALONS = {6, 7};
	private static final int[] LIMIT_SWITCHES = {0, 1, 2};

	private static WPI_TalonSRX[] talons;
	private static DigitalInput[] limitSwitches;


	public static void init () {
		talons = new WPI_TalonSRX[LIFT_TALONS.length];
		for (int i = 0; i < LIFT_TALONS.length; i++) {
			talons[i] = new WPI_TalonSRX(LIFT_TALONS[i]);
		}

		limitSwitches = new DigitalInput[LIMIT_SWITCHES.length];
		for (int i = 0; i < LIMIT_SWITCHES.length; i++) {
			limitSwitches[i] = new DigitalInput(i); // TODO Confirm Offset
		}
	}

	static void set(ControlMode controlMode, double value) {
		talons[0].set(controlMode, value);
	}

	public static boolean checkLimitSwitches() {
		return true;
	}
}
