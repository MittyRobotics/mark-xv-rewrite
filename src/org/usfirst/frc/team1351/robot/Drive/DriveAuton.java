package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveAuton extends Drive {
	private static final float[] TICKSPERINCH = {487.5f, 487.5f}; //Ticks per inch values (Low - 0, High - 1) TODO Fix Values
	private static double proportionalConstant = SmartDashboard.getNumber("Drive P: ", 0);
	private static double integralConstant = SmartDashboard.getNumber("Drive I: ", 0);
	private static double derivativeConstant = SmartDashboard.getNumber("Drive D: ", 0);
	private static final int incrementer = 500;
	private static byte gear;

	public static void forward(int distance) {
		double leftSetpoint = distance * TICKSPERINCH[gear];
		double rightSetpoint = distance * TICKSPERINCH[gear];
		leftSetpoint += getLeftEncoder();
		rightSetpoint += getRightEncoder();
		if (leftSetpoint > 0) {
			while (DriverStation.getInstance().isEnabled() && getRightTarget() < rightSetpoint && getLeftTarget() < leftSetpoint) {
				setRight(ControlMode.Position, getRightTarget() + incrementer);
				setLeft(ControlMode.Position, getLeftTarget() + incrementer);
				Timer.delay(0.01);
			}
		}
	}

	public void backwards(int distance) {
		double leftSetpoint = distance * TICKSPERINCH[gear];
		double rightSetpoint = distance * TICKSPERINCH[gear];
		leftSetpoint += getLeftEncoder();
		rightSetpoint += getRightEncoder();
		if (leftSetpoint > 0) {
			while (DriverStation.getInstance().isEnabled() && getRightTarget() > rightSetpoint && getLeftTarget() > leftSetpoint) {
				setRight(ControlMode.Position, getRightTarget() - incrementer);
				setLeft(ControlMode.Position, getLeftTarget() - incrementer);
				Timer.delay(0.01);
			}
		}
	}

	public static void turnLeft(int degrees) {
		//TODO Use a PID Loop to Turn Left {degrees} Degrees Using the Gyro
	}

	public static void turnRight(int degrees) {
		//TODO Use a PID Loop to Turn Right {degrees} Degrees Using the Gyro
	}

	private void initAuton() {
		setRight(ControlMode.Position, 0);
		setLeft(ControlMode.Position, 0);
		setPIDF(proportionalConstant, integralConstant, derivativeConstant);
	}

	public void setGear(byte gear) {
		DriveAuton.gear = gear;
		Drive.changeGear(gear);
	}
}
