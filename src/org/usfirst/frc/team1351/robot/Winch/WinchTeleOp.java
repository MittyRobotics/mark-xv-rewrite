package org.usfirst.frc.team1351.robot.Winch;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class WinchTeleOp {
	/**
	 * This is the main climbing function for winch that is operator controlled. This will only work if the winch is greater than one winch length away
	 */
	public static void climb() {
		if (Winch.getUltrasonic() > Winch.getWinchSize()) {
			Winch.set(ControlMode.PercentOutput, 1);
		}
	}
}
