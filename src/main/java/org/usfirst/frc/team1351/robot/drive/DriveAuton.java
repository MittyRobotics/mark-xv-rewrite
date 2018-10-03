package org.usfirst.frc.team1351.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * drive autonomous
 * @author looklotsofpeople
 * @since 2018 Build Season
 * @version 2018.1.1
 */
public class DriveAuton {
	private static final double[] TICKS_PER_INCH = {487.5f, 487.5f}; // TODO Fix Values
	private static final double MOVE_THRESHOLD = 10f;
	private static final double MOVE_INCREMENT_DELAY = 10;
	private static final double MOVE_INCREMENT = 6 / (1000 / MOVE_INCREMENT_DELAY);
	private static final double TURN_THRESHOLD = 3f;

	private static ADXRS450_Gyro gyro;

	static void init() {
		SmartDashboard.putNumber("Drive Forward P: ", 0);
		SmartDashboard.putNumber("Drive Forward I: ", 0);
		SmartDashboard.putNumber("Drive Forward D: ", 0);

		SmartDashboard.putNumber("Drive Turn P: ", 0);
		SmartDashboard.putNumber("Drive Turn I: ", 0);
		SmartDashboard.putNumber("Drive Turn D: ", 0);
	}

	public static void move(double distance) {
		updatePDIF(true);

		Drive.setLeftTalons(ControlMode.Position, Drive.getLeftEncoder());
		Drive.setRightTalons(ControlMode.Position, Drive.getRightEncoder());

		double leftTarget = Drive.getLeftEncoder() + distance * TICKS_PER_INCH[Drive.getGear()];
		double rightTarget = Drive.getRightEncoder() + distance * TICKS_PER_INCH[Drive.getGear()];

		while (leftTarget != Drive.getLeftTarget() || rightTarget != Drive.getRightTarget()) {
			Drive.setLeftTalons(ControlMode.Position, Math.min(Drive.getLeftTarget() + (MOVE_INCREMENT * TICKS_PER_INCH[Drive.getGear()]), leftTarget));
			Drive.setRightTalons(ControlMode.Position, Math.min(Drive.getRightTarget() + (MOVE_INCREMENT * TICKS_PER_INCH[Drive.getGear()]), rightTarget));

			try {
				Thread.sleep(10);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}

		while ((Math.abs(Drive.getLeftError()) < MOVE_THRESHOLD && Math.abs(Drive.getRightError()) < MOVE_THRESHOLD) && true /* Timer Check */) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Drive.setLeftTalons(ControlMode.Disabled, 0);
		Drive.setRightTalons(ControlMode.Disabled, 0);
	}

	public static void turn(double degrees) {
		updatePDIF(false);

	}

	private static void updatePDIF(boolean forward) {
		if (forward) {
			Drive.configPIDF(
					SmartDashboard.getNumber("Drive Forward P: ", 0),
					SmartDashboard.getNumber("Drive Forward I: ", 0),
					SmartDashboard.getNumber("Drive Forward D: ", 0)
			);
		} else {
			Drive.configPIDF(
					SmartDashboard.getNumber("Drive Turn P: ", 0),
					SmartDashboard.getNumber("Drive Turn I: ", 0),
					SmartDashboard.getNumber("Drive Turn D: ", 0)
			);
		}
	}
}
