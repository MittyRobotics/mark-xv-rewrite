package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveAuton {
	private static final float[] TICKSPERINCH = {487.5f, 487.5f}; //Ticks per inch values (Low - 0, High - 1) TODO Fix Values
	private static final float TALONMOVETHRESHOLD = 2.5f;
	private static final float TALONTURNTHRESHOLD = 0.5f;
	private static final float MOVETHRESHOLD = 10f;
	private static final float TURNTHRESHOLD = 3f;

	private static double proportionalConstant;
	private static double integralConstant;
	private static double derivativeConstant;
	private static int gear;
	private static PIDController pid;

	public static void move(int distance) {
		distance *= TICKSPERINCH[gear];
		prepMove();

		pid.setSetpoint(distance);
		pid.enable();

		int count = 0;
		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isAutonomous()) {
			if (Math.abs(pid.getError()) < MOVETHRESHOLD) {
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

		pid.setSetpoint(degrees);
		pid.enable();

		int count = 0;
		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isAutonomous()) {
			if (Math.abs(pid.getError()) < TURNTHRESHOLD) {
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
		pid = new PIDController(proportionalConstant, integralConstant, derivativeConstant, Drive.getGyroInstance(), Drive.getLeftTalonInstance());
		pid.setOutputRange(-1, 1);
		pid.setAbsoluteTolerance(TALONMOVETHRESHOLD);

		Drive.setRightFollower(true);
	}

	private static void prepTurn() {
		pid = new PIDController(proportionalConstant, integralConstant, derivativeConstant, Drive.getGyroInstance(), Drive.getLeftTalonInstance());
		pid.setOutputRange(-1, 1);
		pid.setAbsoluteTolerance(TALONTURNTHRESHOLD);
		pid.setContinuous(true);

		Drive.invertLeftTalon(false);
		Drive.setRightFollower(true);
	}

	private static void endAuton() {
		pid.free();

		Drive.invertLeftTalon(true);
		Drive.setRightFollower(false);
	}

	public static void init() {
		proportionalConstant = SmartDashboard.getNumber("Drive P: ", 0);
		integralConstant = SmartDashboard.getNumber("Drive I: ", 0);
		derivativeConstant = SmartDashboard.getNumber("Drive D: ", 0);

		Drive.setRightDriveEncoderTalon(ControlMode.Position, 0);
		Drive.setLeftDriveEncoderTalon(ControlMode.Position, 0);
		Drive.setPIDF(proportionalConstant, integralConstant, derivativeConstant);
	}

	public static void setGear(int gear) {
		DriveAuton.gear = gear;
		Drive.changeGear(gear);
	}
}
