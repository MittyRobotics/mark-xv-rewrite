package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.usfirst.frc.team1351.robot.ErrorChecker.ErrorChecker;
import org.usfirst.frc.team1351.robot.Logger.Logger;

/**
 * Drive Code
 * @author looklotsofpeople
 * @since 2018 Build Season
 * @version 2018.1.0
 */
public class Drive {
	private static final int[] LEFT_DRIVE_TALONS = {0, 1};
	private static final int[] RIGHT_DRIVE_TALONS = {2, 3};

	private static WPI_TalonSRX[] leftTalons;
	private static WPI_TalonSRX[] rightTalons;
	private static DoubleSolenoid driveSolenoid;
	private static ADXRS450_Gyro gyro;

	private static int gear;

	/**
	 * Initializes the Gyro and Sets Non-Primary Talons as Follower
	 * Should Only Be Run Once, Although Will Not Be Fatal if Run More than Once
	 */
	public static void init() {

		// Hardware Initialization

		if (driveSolenoid == null) {
			driveSolenoid = new DoubleSolenoid(0, 1);
		}
		if (gyro == null) {
			gyro = new ADXRS450_Gyro();
		}
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


		// Setups Talon Following Modes

		// Sets Non-Primary Left Talons as Followers
		if (LEFT_DRIVE_TALONS.length > 1) {
			for (int i = 1; i < LEFT_DRIVE_TALONS.length; i++) {
				leftTalons[LEFT_DRIVE_TALONS[i]].set(ControlMode.Follower, rightTalons[0].getDeviceID());
			}
		}

		// Sets Non-Primary Right Talons as Followers
		if (RIGHT_DRIVE_TALONS.length > 1) {
			for (int i = 1; i < LEFT_DRIVE_TALONS.length; i++) {
				rightTalons[LEFT_DRIVE_TALONS[i]].set(ControlMode.Follower, leftTalons[0].getDeviceID());
			}
		}

		// Inverts Left Talons
		invertLeftTalons(true);
	}

	/**
	 * Inverts the Left Drive Talons
	 * @param shouldInvert (True - Inverts, False - Removes Invert)
	 */
	static void invertLeftTalons(boolean shouldInvert) {
		for (WPI_TalonSRX currentTalon : leftTalons) { // TODO See if All Talons Need to be Inverted or Just the Primary Talon
			currentTalon.setInverted(shouldInvert);
		}
	}

	/**
	 * Inverts the Right Drive Talons
	 * @param shouldInvert (True - Inverts, False - Removes Invert)
	 */
	static void invertRightTalons(boolean shouldInvert) {
		for (WPI_TalonSRX talon : rightTalons) { // TODO See if All Talons Need to be Inverted or Just the Primary Talon
			talon.setInverted(shouldInvert);
		}
	}

	/**
	 * Sets the Left side Master talon to a specified control mode and value
	 * @param controlMode Control Mode of talon
	 * @param value The value that the talon should be set to
	 */
	static void setLeftTalons(ControlMode controlMode, double value) {
		leftTalons[0].set(controlMode, value);
	}

	/**
	 * Sets the Right side Master talon to a specified control mode and value
	 * @param controlMode Control Mode of talon
	 * @param value The value that the talon should be set to
	 */
	static void setRightTalons(ControlMode controlMode, double value) {
		rightTalons[0].set(controlMode, value);
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
				Logger.log("Gear {" + gear + "} Not Found. Defaulting Low.", Logger.Scope.BOTH);
				driveSolenoid.set(DoubleSolenoid.Value.kReverse);
				break;
		}
	}

	/**
	 * Determines how long it take to arrive at a specific speed
	 * @param seconds The amount of time it would take to ramp to full speed
	 */
	static void ramp(double seconds) {
		for (WPI_TalonSRX talon : leftTalons) {
			talon.configOpenloopRamp(seconds, 0);
		}

		for (WPI_TalonSRX talon : rightTalons) {
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

		ErrorChecker.handledConfig_kP(rightTalons[0], 0, P, 0, "Drive");
		ErrorChecker.handledConfig_kI(rightTalons[0], 0, I, 0, "Drive");
		ErrorChecker.handledConfig_kD(rightTalons[0], 0, D, 0, "Drive");
		ErrorChecker.handledConfig_kF(rightTalons[0], 0, 0, 0, "Drive");
		ErrorChecker.handledConfigSelectedFeedbackSensor(rightTalons[0], FeedbackDevice.QuadEncoder, 0, 1000, "Drive");

		ErrorChecker.handledConfig_kP(leftTalons[0], 0, P, 0, "Drive");
		ErrorChecker.handledConfig_kI(leftTalons[0], 0, I, 0, "Drive");
		ErrorChecker.handledConfig_kD(leftTalons[0], 0, D, 0, "Drive");
		ErrorChecker.handledConfig_kF(leftTalons[0], 0, 0, 0, "Drive");
		ErrorChecker.handledConfigSelectedFeedbackSensor(leftTalons[0], FeedbackDevice.QuadEncoder, 0, 1000, "Drive");
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
		return leftTalons[0].getSelectedSensorPosition(0);
	}

	/**
	 * Getter method for the Right Encoder
	 * @return Right Master Drive Encoder
	 */
	static int getRightEncoder() {
		return rightTalons[0].getSelectedSensorPosition(0);
	}

	/**
	 * Getter method for the Left Encoder Error
	 * @return Left Drive Error
	 */
	static int getLeftError() {
		return leftTalons[0].getClosedLoopError(0);
	}

	/**
	 * Getter method for the Right Encoder Error
	 * @return Right Drive Error
	 */
	static int getRightError() {
		return rightTalons[0].getClosedLoopError(0);
	}

	/**
	 * Getter method for the Left Drive Setpoint
	 * @return Left Drive Setpoint
	 */
	static int getLeftTarget() {
		return leftTalons[0].getClosedLoopTarget(0);
	}

	/**
	 * Getter method for the Right Drive Setpoint
	 * @return Right Drive Setpoint
	 */
	static int getRightTarget() {
		return rightTalons[0].getClosedLoopTarget(0);
	}

	@Deprecated
	static WPI_TalonSRX getLeftTalonInstance() {
		return leftTalons[0];
	}

	@Deprecated
	static WPI_TalonSRX getRightTalonInstance() {
		return rightTalons[0];
	}

	@Deprecated
	static ADXRS450_Gyro getGyroInstance() {
		return gyro;
	}

	/**
	 * Sets the Right Drive Talons to follow the Left Master Drive Talon
	 * @param isFollower Should Right be Follower
	 */
	static void setRightFollower(boolean isFollower) {
		if (isFollower) {
			rightTalons[0].set(ControlMode.Follower, leftTalons[0].getDeviceID());
		} else {
			rightTalons[0].set(ControlMode.Follower, rightTalons[0].getDeviceID());
		}
	}
}
