package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class DriveTeleOp extends Drive {
    public static void setLeft(double value) {
    	Drive.setLeft(ControlMode.PercentOutput, value);
    }

    public static void setRight(double value) {
		Drive.setLeft(ControlMode.PercentOutput, value);
	}

	public static void setGear(byte gear) {
    	changeGear(gear);
	}

	public static void setRamp(double seconds) {
    	ramp(seconds);
	}
}
