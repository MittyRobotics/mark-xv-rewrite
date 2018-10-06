package frc.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;

public final class DriveTeleOp {
	public static void setLeft(final double value) {
		Drive.setLeftTalons(ControlMode.PercentOutput, value);
	}

	public static void setRight(final double value) {
		Drive.setRightTalons(ControlMode.PercentOutput, value);
	}

	public static void setGear(final int gear) {
		Drive.setGear(gear);
	}

	public static void test() {
		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isAutonomous()) {
			setLeft(0.2);
			setRight(0.2);
			System.out.println(Drive.getLeftEncoder() + "\n" + Drive.getRightEncoder() + "\n");
			try {
				Thread.sleep(100);
			} catch (InterruptedException ignored) {

			}
		}
	}
}
