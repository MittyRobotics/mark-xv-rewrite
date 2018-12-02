package frc.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Defaults;

public final class DriveAuton {
	private static final double[] TICKS_PER_INCH = {487.5d, 167.3d};

	// TODO Fix ALL Values
	private static final double MOVE_THRESHOLD = 10d;
	private static final long MOVE_INCREMENT_DELAY = 125;
	private static final double MOVE_INCREMENT = 3;
	private static final double MOVE_TIMEOUT = 2;

	// TODO Fix ALL Values
	private static final double TURN_THRESHOLD = 3d;
	private static final long TURN_INCREMENT_DELAY = 10;
	private static final double TURN_INCREMENT = 6 / (1000 / (double) TURN_INCREMENT_DELAY);
	private static final double TURN_TIMEOUT = 1;

	private static ADXRS450_Gyro gyro;

	static void init() {
		gyro = new ADXRS450_Gyro();
	}

	public static void move(final double distance) {
		Drive.setLeftTalons(ControlMode.Position, Drive.getLeftEncoder());
		Drive.setRightTalons(ControlMode.Position, Drive.getRightEncoder());

		final double leftTarget = Drive.getLeftEncoder() + distance * TICKS_PER_INCH[0];
		final double rightTarget = Drive.getRightEncoder() + distance * TICKS_PER_INCH[0];

		final Timer timer = new Timer();
		timer.start();

		while ((leftTarget != Drive.getLeftTarget() || rightTarget != Drive.getRightTarget()) && DriverStation.getInstance().isAutonomous() && DriverStation.getInstance().isEnabled()) {
			Drive.setLeftTalons(ControlMode.Position, Math.min(Drive.getLeftTarget() + (MOVE_INCREMENT * TICKS_PER_INCH[Drive.getGear()]), leftTarget));
			Drive.setRightTalons(ControlMode.Position, Math.min(Drive.getRightTarget() + (MOVE_INCREMENT * TICKS_PER_INCH[Drive.getGear()]), rightTarget));
			System.out.println(Math.min(Drive.getRightTarget() + (MOVE_INCREMENT * TICKS_PER_INCH[Drive.getGear()]), rightTarget));
			try {
				Thread.sleep(MOVE_INCREMENT_DELAY);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}

		Drive.setLeftTalons(ControlMode.Position, leftTarget);
		Drive.setRightTalons(ControlMode.Position, rightTarget);

		while ((Math.abs(Drive.getLeftError()) > MOVE_THRESHOLD || Math.abs(Drive.getRightError()) > MOVE_THRESHOLD) && DriverStation.getInstance().isAutonomous() && DriverStation.getInstance().isEnabled()) {
			System.out.println(timer.get());
			if (timer.get() > MOVE_TIMEOUT) {
				break;
			}
			try {
				Thread.sleep(10);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void turn(final double angle) {
		//Drive.setGear(0);
		final double setpoint = gyro.getAngle() + angle;

		final PIDController leftPidController = new PIDController(SmartDashboard.getNumber("Turn P", Defaults.TURN_P),
				SmartDashboard.getNumber("Turn I", Defaults.TURN_I),
				SmartDashboard.getNumber("Turn D", Defaults.TURN_D),
				gyro, Drive.getLeftTalon());
		final PIDController rightPidController = new PIDController(SmartDashboard.getNumber("Turn P", Defaults.TURN_P),
				SmartDashboard.getNumber("Turn I", Defaults.TURN_I),
				SmartDashboard.getNumber("Turn D", Defaults.TURN_D),
				gyro, Drive.getRightTalon());

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
}
