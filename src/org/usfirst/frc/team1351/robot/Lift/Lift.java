package org.usfirst.frc.team1351.robot.Lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

class Lift {
	private static int[] LIFTTALONS = {6, 7};
	private static int[] LIMITSWITCHES = {0, 1, 2};

	private static WPI_TalonSRX[] talons;
	private static DigitalInput[] limitSwitches;


	public static void init () {
		talons = new WPI_TalonSRX[LIFTTALONS.length];
		for (int i = 0; i < LIFTTALONS.length; i++) {
			talons[i] = new WPI_TalonSRX(LIFTTALONS[i]);
		}

		limitSwitches = new DigitalInput[LIMITSWITCHES.length];
		for (int i = 0; i < LIMITSWITCHES.length; i++) {
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
