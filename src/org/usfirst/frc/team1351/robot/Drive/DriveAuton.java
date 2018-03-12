package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1351.robot.Logger.Logger;

/**
 * Drive Autonomous
 * @author looklotsofpeople
 * @since 2018 Build Season
 * @version 2018.1.1
 */
public class DriveAuton {
	private static final double[] TICKS_PER_INCH = {487.5f, 487.5f}; // TODO Fix Values
	private static final double MOVE_THRESHOLD = 10f;
	private static final double TURN_THRESHOLD = 3f;

	public static void move(int distance) {
		// TODO Test
		Logger.log("Executing Drive Move.", Logger.Scope.DRIVERSTATIONONLY);

		distance *= TICKS_PER_INCH[Drive.getGear()];
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
		// TODO Add PIDController
		Logger.log("Executing Drive Turn.", Logger.Scope.DRIVERSTATIONONLY);

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
		Drive.setGear(0);
		Drive.setRightFollower(true);
	}

	private static void prepTurn() {
		Drive.setGear(0);
		Drive.invertLeftTalons(false);
		Drive.setRightFollower(true);
	}

	private static void endAuton() {
		Drive.invertLeftTalons(true);
		Drive.setRightFollower(false);
	}

	public static void init() {
		double proportionalConstant = SmartDashboard.getNumber("Drive P: ", 0);
		double integralConstant = SmartDashboard.getNumber("Drive I: ", 0);
		double derivativeConstant = SmartDashboard.getNumber("Drive D: ", 0);

		Drive.setRightTalons(ControlMode.Position, 0);
		Drive.setLeftTalons(ControlMode.Position, 0);
		Drive.setPIDF(proportionalConstant, integralConstant, derivativeConstant);
	}
}
