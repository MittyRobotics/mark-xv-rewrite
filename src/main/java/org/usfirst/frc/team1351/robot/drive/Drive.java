package org.usfirst.frc.team1351.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Drive {
	private static final int[] LEFT_DRIVE_TALONS = {0, 1};
	private static final int[] RIGHT_DRIVE_TALONS = {2, 3};
	private static final int[] PDIF = {1, 0, 0, 0};

	private static WPI_TalonSRX[] leftTalons;
	private static WPI_TalonSRX[] rightTalons;
	private static DoubleSolenoid shifter;

	public static void init() {
		if (leftTalons == null) {
			leftTalons = new WPI_TalonSRX[LEFT_DRIVE_TALONS.length];
			for (int i = 0; i < leftTalons.length; i++) {
				leftTalons[i] = new WPI_TalonSRX(LEFT_DRIVE_TALONS[i]);
			}
		}
		if (rightTalons == null) {
			rightTalons = new WPI_TalonSRX[RIGHT_DRIVE_TALONS.length];
			for (int i = 0; i < rightTalons.length; i++) {
				rightTalons[i] = new WPI_TalonSRX(RIGHT_DRIVE_TALONS[i]);
			}
		}
		if (shifter == null) {
			shifter = new DoubleSolenoid(6, 7);
		}

		for (int i = 1; i < leftTalons.length; i++) {
			leftTalons[0].set(ControlMode.Follower, leftTalons[0].getDeviceID());
		}
		for (int i = 1; i < rightTalons.length; i++) {
			rightTalons[i].set(ControlMode.Follower, rightTalons[0].getDeviceID());
		}

		reset();

		switch (PDIF.length) {
			//noinspection ConstantConditions
			case 0:
				configPIDF(0, 0, 0);
				break;
			//noinspection ConstantConditions
			case 1:
				configPIDF(PDIF[0], 0, 0);
				break;
			//noinspection ConstantConditions
			case 2:
				configPIDF(PDIF[0], PDIF[1], 0);
				break;
			//noinspection ConstantConditions
			case 3:
				configPIDF(PDIF[0], PDIF[1], PDIF[2]);
				break;
			//noinspection ConstantConditions
			case 4:
				configPIDF(PDIF[0], PDIF[1], PDIF[2], PDIF[3]);
				break;
		}

		DriveAuton.init();
	}

	private static void reset() {
		// TODO Put Anything That Needs to Be Reset
		for (final WPI_TalonSRX talon : leftTalons) {
			talon.configClosedloopRamp(0, 0);
		}
		for (final WPI_TalonSRX talon : rightTalons) {
			talon.configClosedloopRamp(0, 0);
		}
	}

	static void configPIDF(double P, double I, double D, double... F) {
		leftTalons[0].config_kP(0, P, 0);
		leftTalons[0].config_kI(0, I, 0);
		leftTalons[0].config_kD(0, D, 0);
		if (F.length != 0) {
			leftTalons[0].config_kF(0, F[0], 0);
		} else {
			leftTalons[0].config_kF(0, 0, 0);
		}
		leftTalons[0].configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		rightTalons[0].config_kP(0, P, 0);
		rightTalons[0].config_kI(0, I, 0);
		rightTalons[0].config_kD(0, D, 0);
		if (F.length != 0) {
			rightTalons[0].config_kF(0, F[0], 0);
		} else {
			rightTalons[0].config_kF(0, 0, 0);
		}
		rightTalons[0].configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
	}

	@SuppressWarnings("SameParameterValue")
	static void setLeftTalons(ControlMode controlMode, double value) {
		leftTalons[0].set(controlMode, value);
	}

	@SuppressWarnings("SameParameterValue")
	static void setRightTalons(ControlMode controlMode, double value) {
		rightTalons[0].set(controlMode, value);
	}

	@SuppressWarnings("SameParameterValue")
	static void invertLeftTalons(boolean shouldInvert) {
		for (WPI_TalonSRX currentTalon : leftTalons) {
			currentTalon.setInverted(shouldInvert);
		}
	}

	@SuppressWarnings("SameParameterValue")
	static void invertRightTalons(boolean shouldInvert) {
		for (WPI_TalonSRX talon : rightTalons) {
			talon.setInverted(shouldInvert);
		}
	}

	static void ramp(double seconds) {
		for (final WPI_TalonSRX talon : leftTalons) {
			talon.configOpenloopRamp(seconds, 0);
		}

		for (final WPI_TalonSRX talon : rightTalons) {
			talon.configOpenloopRamp(seconds, 0);
		}
	}

	static int getLeftEncoder() {
		return leftTalons[0].getSelectedSensorPosition(0);
	}

	static int getRightEncoder() {
		return rightTalons[0].getSelectedSensorPosition(0);
	}

	static int getLeftError() {
		return leftTalons[0].getClosedLoopError(0);
	}

	static int getRightError() {
		return rightTalons[0].getClosedLoopError(0);
	}

	static int getLeftTarget() {
		return leftTalons[0].getClosedLoopTarget(0);
	}

	static int getRightTarget() {
		return rightTalons[0].getClosedLoopTarget(0);
	}

	static int getGear() {
		if (shifter.get() == Value.kForward) {
			return 1;
		} else {
			return 0;
		}
	}

	static void setGear(int gear) {
		switch (gear) {
			case 0:
				shifter.set(DoubleSolenoid.Value.kReverse);
				break;
			case 1:
				shifter.set(DoubleSolenoid.Value.kForward);
				break;
		}
	}
}
