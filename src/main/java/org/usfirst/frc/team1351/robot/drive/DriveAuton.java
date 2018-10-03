package org.usfirst.frc.team1351.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * drive autonomous
 *
 * @author looklotsofpeople
 * @version 2018.1.1
 * @since 2018 Build Season
 */
public class DriveAuton {
	private static final double[] TICKS_PER_INCH = {487.5f, 487.5f}; // TODO Fix Values
	private static final double MOVE_THRESHOLD = 10d;
	private static final long MOVE_INCREMENT_DELAY = 10;
	private static final double MOVE_INCREMENT = 6 / (1000 / (double) MOVE_INCREMENT_DELAY);
	private static final double MOVE_TIMEOUT = 15*60;
	private static final double TURN_THRESHOLD = 3f;
	private static final long TURN_INCREMENT_DELAY = 10;
	private static final double TURN_INCREMENT = 6 / (1000 / (double) TURN_INCREMENT_DELAY);
	private static final double TURN_TIMEOUT = 15*60;

	private static ADXRS450_Gyro gyro;

	static void init() {
		SmartDashboard.putNumber("Drive Forward P: ", 0.2);
		SmartDashboard.putNumber("Drive Forward I: ", 0);
		SmartDashboard.putNumber("Drive Forward D: ", 0);
		SmartDashboard.putNumber("Drive Turn P: ", 1);
		SmartDashboard.putNumber("Drive Turn I: ", 0);
		SmartDashboard.putNumber("Drive Turn D: ", 0);

		gyro = new ADXRS450_Gyro();
	}

	public static void move(double distance) {
		updatePDIF();

		Drive.setLeftTalons(ControlMode.Position, Drive.getLeftEncoder());
		Drive.setRightTalons(ControlMode.Position, Drive.getRightEncoder());

		double leftTarget = Drive.getLeftEncoder() + distance * TICKS_PER_INCH[Drive.getGear()];
		double rightTarget = Drive.getRightEncoder() + distance * TICKS_PER_INCH[Drive.getGear()];

		final Timer timer = new Timer();
		timer.start();

		while (leftTarget != Drive.getLeftTarget() || rightTarget != Drive.getRightTarget()) {
			Drive.setLeftTalons(ControlMode.Position, Math.min(Drive.getLeftTarget() + (MOVE_INCREMENT * TICKS_PER_INCH[Drive.getGear()]), leftTarget));
			Drive.setRightTalons(ControlMode.Position, Math.min(Drive.getRightTarget() + (MOVE_INCREMENT * TICKS_PER_INCH[Drive.getGear()]), rightTarget));

			try {
				Thread.sleep(10);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}

		while (Math.abs(Drive.getLeftError()) > MOVE_THRESHOLD || Math.abs(Drive.getRightError()) > MOVE_THRESHOLD) {
			if (timer.get() > MOVE_TIMEOUT) {
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Drive.setLeftTalons(ControlMode.Disabled, 0);
		Drive.setRightTalons(ControlMode.Disabled, 0);
	}

	public static void turn(final double angle) {
		final double setpoint = gyro.getAngle() - angle;

		final PIDController leftPidController = new PIDController(SmartDashboard.getNumber("Drive Turn P: ", 0), SmartDashboard.getNumber("Drive Turn I: ", 0), SmartDashboard.getNumber("Drive Turn D: ", 0), gyro, Drive.getLeftTalon());
		final PIDController rightPidController = new PIDController(SmartDashboard.getNumber("Drive Turn P: ", 0), SmartDashboard.getNumber("Drive Turn I: ", 0), SmartDashboard.getNumber("Drive Turn D: ", 0), gyro, Drive.getRightTalon());

		leftPidController.setOutputRange(-1, 1);
		rightPidController.setOutputRange(-1, 1);

		final Timer timer = new Timer();
		timer.start();

		while (setpoint != leftPidController.getSetpoint() || setpoint != rightPidController.getSetpoint()) {
			leftPidController.setSetpoint(Math.min(leftPidController.getSetpoint() + TURN_INCREMENT, setpoint));
			rightPidController.setSetpoint(Math.min(rightPidController.getSetpoint() + TURN_INCREMENT, setpoint));

			if (!leftPidController.isEnabled()) {
				leftPidController.enable();
			}
			if (!rightPidController.isEnabled()) {
				rightPidController.enable();
			}

			try {
				Thread.sleep(TURN_INCREMENT_DELAY);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}

		while (Math.abs(leftPidController.getError()) > TURN_THRESHOLD || Math.abs(rightPidController.getError()) > TURN_THRESHOLD) {
			if (timer.get() > TURN_TIMEOUT) {
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		leftPidController.free();
		rightPidController.free();
	}

	private static void updatePDIF() {
		Drive.configPIDF(
				SmartDashboard.getNumber("Drive Forward P: ", 0),
				SmartDashboard.getNumber("Drive Forward I: ", 0),
				SmartDashboard.getNumber("Drive Forward D: ", 0)
		);
	}
}
