package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import org.usfirst.frc.team1351.robot.ErrorChecker.ErrorChecker;

public class Drive {
	private static final FeedbackDevice ENCODER = FeedbackDevice.QuadEncoder;

	private static WPI_TalonSRX[] talons;
	private static DoubleSolenoid driveSolenoid;
	private static ADXRS450_Gyro gyro;

	private static final int[] LEFT_DRIVE_TALONS = {0, 1};
	private static final int[] RIGHT_DRIVE_TALONS = {2, 3};

	private static int gear;

	/**
	 * Initializes the Gyro and initializes both left and right drive talons as Masters/Slaves
	 */
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

	/**
	 * Inverts the Left Drive Talons
	 * @param shouldInvert (True - Inverts, False - Removes Invert)
	 */
	static void invertLeftTalon(boolean shouldInvert) {
		for (int LEFTDRIVETALON : LEFT_DRIVE_TALONS) {
			talons[LEFTDRIVETALON].setInverted(shouldInvert);
		}
	}

	/**
	 * Sets the Left side Master talon to a specified control mode and value
	 * @param controlMode Control Mode of talon
	 * @param value The value that the talon should be set to
	 */
	static void setLeftDriveEncoderTalon(ControlMode controlMode, double value) {
		talons[LEFT_DRIVE_TALONS[0]].set(controlMode, value);
	}

	/**
	 * Sets the Right side Master talon to a specified control mode and value
	 * @param controlMode Control Mode of talon
	 * @param value The value that the talon should be set to
	 */
	static void setRightDriveEncoderTalon(ControlMode controlMode, double value) {
		talons[RIGHT_DRIVE_TALONS[0]].set(controlMode, value);
	}

	/**
	 * Changes the gear that the robot is in
	 * @param gear Gear to change into ( 1 - High Gear, 0 - Low Gear)
	 */
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

	/**
	 * Determines how long it take to arrive at a specific speed
	 * @param seconds The amount of time it would take to ramp to full speed
	 */
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
		ErrorChecker.handledConfig_kP(talons[RIGHT_DRIVE_TALONS[0]], 0, P, 0, "Drive");
		ErrorChecker.handledConfig_kI(talons[RIGHT_DRIVE_TALONS[0]], 0, I, 0, "Drive");
		ErrorChecker.handledConfig_kD(talons[RIGHT_DRIVE_TALONS[0]], 0, D, 0, "Drive");
		ErrorChecker.handledConfig_kF(talons[RIGHT_DRIVE_TALONS[0]], 0, 0, 0, "Drive");
		ErrorChecker.handledConfigSelectedFeedbackSensor(talons[RIGHT_DRIVE_TALONS[0]], ENCODER, 0, 1000, "Drive");

		ErrorChecker.handledConfig_kP(talons[LEFT_DRIVE_TALONS[0]], 0, P, 0, "Drive");
		ErrorChecker.handledConfig_kI(talons[LEFT_DRIVE_TALONS[0]], 0, I, 0, "Drive");
		ErrorChecker.handledConfig_kD(talons[LEFT_DRIVE_TALONS[0]], 0, D, 0, "Drive");
		ErrorChecker.handledConfig_kF(talons[LEFT_DRIVE_TALONS[0]], 0, 0, 0, "Drive");
		ErrorChecker.handledConfigSelectedFeedbackSensor(talons[RIGHT_DRIVE_TALONS[0]], ENCODER, 0, 1000, "Drive");
	}

	/**
	 * Getter statement for the Gyro
	 * @return Gyro
	 */
	static double getGyro() {
		return gyro.getAngle();
	}

	/**
	 * Resets the Gyro
	 */
	static void resetGyro() {
		gyro.reset();
	}

	/**
	 * Getter method for the Left Encoder
	 * @return Left Master Drive Talon Encoder
	 */
	static int getLeftEncoder() {
		return talons[LEFT_DRIVE_TALONS[0]].getSelectedSensorPosition(0);
	}

	/**
	 * Getter method for the Right Encoder
	 * @return Right Master Drive Encoder
	 */
	static int getRightEncoder() {
		return talons[RIGHT_DRIVE_TALONS[0]].getSelectedSensorPosition(0);
	}

	/**
	 * Getter method for the Left Encoder Error
	 * @return Left Drive Error
	 */
	static int getLeftError() {
		return talons[LEFT_DRIVE_TALONS[0]].getClosedLoopError(0);
	}

	/**
	 * Getter method for the Right Encoder Error
	 * @return Right Drive Error
	 */
	static int getRightError() {
		return talons[RIGHT_DRIVE_TALONS[0]].getClosedLoopError(0);
	}

	/**
	 * Getter method for the Left Drive Setpoint
	 * @return Left Drive Setpoint
	 */
	static int getLeftTarget() {
		return talons[LEFT_DRIVE_TALONS[0]].getClosedLoopTarget(0);
	}

	/**
	 * Getter method for the Right Drive Setpoint
	 * @return Right Drive Setpoint
	 */
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

	/**
	 * Sets the Right Drive Talons to follow the Left Master Drive Talon
	 * @param isFollower
	 */
	static void setRightFollower(boolean isFollower) {
		if (isFollower) {
			talons[RIGHT_DRIVE_TALONS[0]].set(ControlMode.Follower, talons[LEFT_DRIVE_TALONS[0]].getDeviceID());
		} else {
			talons[RIGHT_DRIVE_TALONS[0]].set(ControlMode.PercentOutput, 0);
		}
	}
}
