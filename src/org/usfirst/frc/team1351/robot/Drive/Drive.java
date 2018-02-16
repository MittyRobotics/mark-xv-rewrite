package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public abstract class Drive {
	private static final FeedbackDevice ENCODER = FeedbackDevice.QuadEncoder;

	private static TalonSRX[] talons;
	private static DoubleSolenoid driveSolenoid;
	private static ADXRS450_Gyro gyro;

	private static final byte[] LEFTDRIVETALONS = {0, 1};
	private static final byte[] RIGHTDRIVETALONS = {2, 3};

	private static byte gear;

	public static void init() {
//		driveSolenoid = new DoubleSolenoid(0, 1);
		gyro = new ADXRS450_Gyro();


		talons = new TalonSRX[LEFTDRIVETALONS.length + RIGHTDRIVETALONS.length];
		for (int i = 0; i < talons.length; i++) {
			talons[i] = new TalonSRX(i);
		}


		//Sets Left encoder talon and sets the rest as Followers
		talons[LEFTDRIVETALONS[0]].set(ControlMode.PercentOutput, 0);
		for (int i = 1; i < LEFTDRIVETALONS.length; i++) {
			talons[LEFTDRIVETALONS[i]].set(ControlMode.Follower, talons[0].getDeviceID());
		}


		//Sets Right encoder talons and sets the rest as Followers
		talons[RIGHTDRIVETALONS[0]].set(ControlMode.PercentOutput, 0);
		for (int i = 1; i < LEFTDRIVETALONS.length; i++) {
			talons[LEFTDRIVETALONS[i]].set(ControlMode.Follower, talons[0].getDeviceID());
		}


		//Inverts Left Talons
		invertLeftTalon(true);
	}

	static void invertLeftTalon(boolean shouldInvert) {
		for (byte LEFTDRIVETALON : LEFTDRIVETALONS) {
			talons[LEFTDRIVETALON].setInverted(shouldInvert);
		}
	}

	static void setLeft(ControlMode controlMode, double value) {
		talons[LEFTDRIVETALONS[0]].set(controlMode, value);
	}

	static void setRight(ControlMode controlMode, double value) {
		talons[RIGHTDRIVETALONS[0]].set(controlMode, value);
	}

	static void changeGear(byte gear) {
		switch (gear) {
			case 0:
				driveSolenoid.set(DoubleSolenoid.Value.kReverse);
				break;

			case 1:
				driveSolenoid.set(DoubleSolenoid.Value.kForward);
				break;

			default:
				System.out.println("Gear Not Found. Defaulting Low");
				driveSolenoid.set(DoubleSolenoid.Value.kReverse);
				break;
		}
	}

	static void ramp(double seconds) {
		for (TalonSRX talon : talons) {
			talon.configOpenloopRamp(seconds, 0);
		}
	}

	/**
	 * Sets the left and right encoder talons PIDF values and default encoder.
	 *
	 * @param P Proportional Constant
	 * @param I Integral Constant
	 * @param D Derivative Constant
	 */
	static void setPIDF(double P, double I, double D) {
		talons[RIGHTDRIVETALONS[0]].config_kP(0, P, 0);
		talons[RIGHTDRIVETALONS[0]].config_kI(0, I, 0);
		talons[RIGHTDRIVETALONS[0]].config_kD(0, D, 0);
		talons[RIGHTDRIVETALONS[0]].config_kF(0, 0, 0);
		talons[RIGHTDRIVETALONS[0]].configSelectedFeedbackSensor(ENCODER, 0, 1000);


		talons[LEFTDRIVETALONS[0]].config_kP(0, P, 0);
		talons[LEFTDRIVETALONS[0]].config_kI(0, I, 0);
		talons[LEFTDRIVETALONS[0]].config_kD(0, D, 0);
		talons[LEFTDRIVETALONS[0]].config_kF(0, 0, 0);
		talons[LEFTDRIVETALONS[0]].configSelectedFeedbackSensor(ENCODER, 0, 1000);
	}

	static double getGyro() {
		return gyro.getAngle();
	}

	static void resetGyro() {
		gyro.reset();
	}

	static int getLeftEncoder() {
		return talons[LEFTDRIVETALONS[0]].getSelectedSensorPosition(0);
	}

	static int getRightEncoder() {
		return talons[RIGHTDRIVETALONS[0]].getSelectedSensorPosition(0);
	}

	static int getLeftError() {
		return talons[LEFTDRIVETALONS[0]].getClosedLoopError(0);
	}

	static int getRightError() {
		return talons[RIGHTDRIVETALONS[0]].getClosedLoopError(0);
	}

	static int getLeftTarget() {
		return talons[LEFTDRIVETALONS[0]].getClosedLoopTarget(0);
	}

	static int getRightTarget() {
		return talons[RIGHTDRIVETALONS[0]].getClosedLoopTarget(0);
	}

	static PIDOutput getLeftTalonInstance() {
		return (PIDOutput) talons[LEFTDRIVETALONS[0]];
	}

	static PIDOutput getRightTalonInstance() {
		return (PIDOutput) talons[RIGHTDRIVETALONS[0]];
	}

	static PIDSource getGyroInstance() {
		return gyro;
	}

	static void setRightFollower(boolean isFollower) {
		if (isFollower) {
			talons[RIGHTDRIVETALONS[0]].set(ControlMode.Follower, talons[LEFTDRIVETALONS[0]].getDeviceID());
		} else {
			talons[RIGHTDRIVETALONS[0]].set(ControlMode.PercentOutput, 0);
		}
	}
}
