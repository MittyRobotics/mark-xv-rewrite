package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive Autonomous
 * @author looklotsofpeople
 * @since 2018 Build Season
 * @version 2018.1.1
 */
public class DriveAuton {
	private static final double[] TICKS_PER_INCH = {487.5f, 487.5f}; //Ticks per inch values (Low - 0, High - 1) TODO Fix Values
	private static final double MOVE_THRESHOLD = 10f;
	private static final double TURN_THRESHOLD = 3f;

	private static double proportionalConstant;
	private static double integralConstant;
	private static double derivativeConstant;
	private static int gear;

	public static void move(int distance) {
		distance *= TICKS_PER_INCH[gear];
		prepMove();


		int count = 0;
		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isAutonomous()) {
			Drive.setLeftTalons(ControlMode.Position, distance);
			if (Math.abs(Drive.getLeftError()) < MOVE_THRESHOLD) {
				if (count > 1000) {
					break;
				}
				count++;
			} else {
				count = 0;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		endAuton();
	}

	public static void turn(int degrees) {
		System.out.println("Executing gyro turn");

		prepTurn();
		System.out.println(Drive.getGyro());

		int count = 0;
		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isAutonomous()) {
			Drive.setLeftTalons(ControlMode.Position, degrees);
			if (Math.abs(Drive.getLeftError()) < TURN_THRESHOLD) {
				if (count > 1000) {
					break;
				}
				count++;
			} else {
				count = 0;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		endAuton();

		System.out.println("Done executing gyro turn");
	}

	private static void prepMove() {
		Drive.setRightFollower(true);
	}

	private static void prepTurn() {
		Drive.invertLeftTalons(false);
		Drive.setRightFollower(true);
	}

	private static void endAuton() {
		Drive.invertLeftTalons(true);
		Drive.setRightFollower(false);
	}

	public static void init() {
		proportionalConstant = SmartDashboard.getNumber("Drive P: ", 0);
		integralConstant = SmartDashboard.getNumber("Drive I: ", 0);
		derivativeConstant = SmartDashboard.getNumber("Drive D: ", 0);

		Drive.setRightTalons(ControlMode.Position, 0);
		Drive.setLeftTalons(ControlMode.Position, 0);
		Drive.setPIDF(proportionalConstant, integralConstant, derivativeConstant);
	}
}