package frc.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;

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
