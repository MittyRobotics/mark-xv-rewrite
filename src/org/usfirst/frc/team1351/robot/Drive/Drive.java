package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public class Drive {
	private static final FeedbackDevice ENCODER = FeedbackDevice.QuadEncoder;

	private static WPI_TalonSRX[] talons;
	private static DoubleSolenoid driveSolenoid;
	private static ADXRS450_Gyro gyro;

	private static final int[] LEFT_DRIVE_TALONS = {0, 1};
	private static final int[] RIGHT_DRIVE_TALONS = {2, 3};

	private static int gear;

	public static void init() {
//		driveSolenoid = new DoubleSolenoid(0, 1);
		gyro = new ADXRS450_Gyro();


		talons = new WPI_TalonSRX[LEFT_DRIVE_TALONS.length + RIGHT_DRIVE_TALONS.length];
		for (int i = 0; i < talons.length; i++) {
			talons[i] = new WPI_TalonSRX(i);
		}


		//Sets Left encoder talon and sets the rest as Followers
		talons[LEFT_DRIVE_TALONS[0]].set(ControlMode.PercentOutput, 0);
		for (int i = 1; i < LEFT_DRIVE_TALONS.length; i++) {
			talons[LEFT_DRIVE_TALONS[i]].set(ControlMode.Follower, talons[0].getDeviceID());
		}


		//Sets Right encoder talons and sets the rest as Followers
		talons[RIGHT_DRIVE_TALONS[0]].set(ControlMode.PercentOutput, 0);
		for (int i = 1; i < LEFT_DRIVE_TALONS.length; i++) {
			talons[LEFT_DRIVE_TALONS[i]].set(ControlMode.Follower, talons[0].getDeviceID());
		}


		//Inverts Left Talons
		invertLeftTalon(true);
	}

	static void invertLeftTalon(boolean shouldInvert) {
		for (int LEFTDRIVETALON : LEFT_DRIVE_TALONS) {
			talons[LEFTDRIVETALON].setInverted(shouldInvert);
		}
	}

	static void setLeftDriveEncoderTalon(ControlMode controlMode, double value) {
		talons[LEFT_DRIVE_TALONS[0]].set(controlMode, value);
	}

	static void setRightDriveEncoderTalon(ControlMode controlMode, double value) {
		talons[RIGHT_DRIVE_TALONS[0]].set(controlMode, value);
	}

	static void changeGear(int gear) {
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
		for (WPI_TalonSRX talon : talons) {
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
		talons[RIGHT_DRIVE_TALONS[0]].config_kP(0, P, 0);
		talons[RIGHT_DRIVE_TALONS[0]].config_kI(0, I, 0);
		talons[RIGHT_DRIVE_TALONS[0]].config_kD(0, D, 0);
		talons[RIGHT_DRIVE_TALONS[0]].config_kF(0, 0, 0);
		talons[RIGHT_DRIVE_TALONS[0]].configSelectedFeedbackSensor(ENCODER, 0, 1000);


		talons[LEFT_DRIVE_TALONS[0]].config_kP(0, P, 0);
		talons[LEFT_DRIVE_TALONS[0]].config_kI(0, I, 0);
		talons[LEFT_DRIVE_TALONS[0]].config_kD(0, D, 0);
		talons[LEFT_DRIVE_TALONS[0]].config_kF(0, 0, 0);
		talons[LEFT_DRIVE_TALONS[0]].configSelectedFeedbackSensor(ENCODER, 0, 1000);
	}

	static double getGyro() {
		return gyro.getAngle();
	}

	static void resetGyro() {
		gyro.reset();
	}

	static int getLeftEncoder() {
		return talons[LEFT_DRIVE_TALONS[0]].getSelectedSensorPosition(0);
	}

	static int getRightEncoder() {
		return talons[RIGHT_DRIVE_TALONS[0]].getSelectedSensorPosition(0);
	}

	static int getLeftError() {
		return talons[LEFT_DRIVE_TALONS[0]].getClosedLoopError(0);
	}

	static int getRightError() {
		return talons[RIGHT_DRIVE_TALONS[0]].getClosedLoopError(0);
	}

	static int getLeftTarget() {
		return talons[LEFT_DRIVE_TALONS[0]].getClosedLoopTarget(0);
	}

	static int getRightTarget() {
		return talons[RIGHT_DRIVE_TALONS[0]].getClosedLoopTarget(0);
	}

	static PIDOutput getLeftTalonInstance() {
		return (PIDOutput) talons[LEFT_DRIVE_TALONS[0]];
	}

	static PIDOutput getRightTalonInstance() {
		return (PIDOutput) talons[RIGHT_DRIVE_TALONS[0]];
	}

	static PIDSource getGyroInstance() {
		return gyro;
	}

	static void setRightFollower(boolean isFollower) {
		if (isFollower) {
			talons[RIGHT_DRIVE_TALONS[0]].set(ControlMode.Follower, talons[LEFT_DRIVE_TALONS[0]].getDeviceID());
		} else {
			talons[RIGHT_DRIVE_TALONS[0]].set(ControlMode.PercentOutput, 0);
		}
	}
}
