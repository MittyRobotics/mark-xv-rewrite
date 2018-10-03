package org.usfirst.frc.team1351.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * drive TeleOp
 *
 * @author looklotsofpeople
 * @version 2018.1.0
 * @since 2018 Build Season
 */
public class DriveTeleOp {
	public static void setLeft(double value) {
		Drive.setLeftTalons(ControlMode.PercentOutput, value);
	}

	public static void setRight(double value) {
		Drive.setLeftTalons(ControlMode.PercentOutput, value);
	}

	public static void setGear(int gear) {
		Drive.setGear(gear);
	}
}
