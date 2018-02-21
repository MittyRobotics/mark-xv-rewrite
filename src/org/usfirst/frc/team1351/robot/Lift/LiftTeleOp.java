package org.usfirst.frc.team1351.robot.Lift;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class LiftTeleOp {
	public static void set(double value) {
		if (Lift.checkLimitSwitches()) {
			Lift.set(ControlMode.PercentOutput, value);
		}
	}
}
