package org.usfirst.frc.team1351.robot.Winch;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class WinchTeleOp {
	public static void climb() {
		if (Winch.getUltrasonic() > Winch.getWinchSize()) {
			Winch.set(ControlMode.PercentOutput, 1);
		}
	}
}
