package org.usfirst.frc.team1351.robot.Lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftAuton {
	private static final double TICKS_PER_INCH = 0; //Ticks per inch values (Low - 0, High - 1) TODO Fix Values

	private static volatile double setpoint = 0;
	private static volatile boolean enabled;

	private static Thread liftThread;

	public static void init() {
		SmartDashboard.putNumber("Lift P", 0);
		SmartDashboard.putNumber("Lift I", 0);
		SmartDashboard.putNumber("Lift D", 0);

		liftThread = new Thread(LiftAuton::liftThread);
	}

	public static void start() {
		if (!liftThread.isAlive()) {
			enabled = true;
			liftThread.start();
		}
	}

	public static void stop() {
		enabled = false;
	}

	public static void updateSetpoint(int newSetpoint) {
		setpoint = newSetpoint * TICKS_PER_INCH;
	}

	private static void liftThread() {
		double proportionalConstant = SmartDashboard.getNumber("Lift P: ", 0);
		double integralConstant = SmartDashboard.getNumber("Lift I: ", 0);
		double derivativeConstant = SmartDashboard.getNumber("Lift D: ", 0);

		Lift.setPIDF(proportionalConstant, integralConstant, derivativeConstant);

		while (DriverStation.getInstance().isEnabled() && enabled) {
			Lift.set(ControlMode.Position, setpoint);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
