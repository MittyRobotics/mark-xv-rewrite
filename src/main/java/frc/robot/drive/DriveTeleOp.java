package frc.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;

public final class DriveTeleOp {
	public static void setLeft(final double value) {
		Drive.setLeftTalons(ControlMode.PercentOutput, value);
	}

	public static void setRight(final double value) {
		Drive.setRightTalons(ControlMode.PercentOutput, value);
	}

	public static void setGear(final int gear) {
		Drive.setGear(gear);
	}
}
