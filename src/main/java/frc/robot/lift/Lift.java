package frc.robot.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Defaults;

public final class Lift {
	public static final double SWITCH_HEIGHT = 31;
	public static final double SCALE_HEIGHT = 84; // TODO

	private static final int TALON_IDS[] = {9, 5};

	private static WPI_TalonSRX[] talons;

	public static void init() {
		talons = new WPI_TalonSRX[TALON_IDS.length];
		for (int i = 0; i < TALON_IDS.length; i++) {
			talons[i] = new WPI_TalonSRX(TALON_IDS[i]);
			if (i > 0) {
				talons[i].set(ControlMode.Follower, TALON_IDS[0]);
			}
		}

		talons[0].configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		talons[0].setSensorPhase(true);
		talons[0].configContinuousCurrentLimit(25, 0);
		talons[0].enableCurrentLimit(true);
		talons[0].setSelectedSensorPosition(0, 0, 0); // TODO("This Should be Hardware Set Not Programmed") Clean

		configAmpLimit();

		SmartDashboard.putNumber("Lift P", Defaults.LIFT_P);
		SmartDashboard.putNumber("Lift I", Defaults.LIFT_I);
		SmartDashboard.putNumber("Lift D", Defaults.LIFT_D);
		SmartDashboard.putNumber("Lift Amp Limit", Defaults.LIFT_AMP_LIMIT);
	}

	public static void set(final double value) {
		talons[0].set(ControlMode.PercentOutput, value);
	}

	private static void configAmpLimit() {
		talons[0].configContinuousCurrentLimit((int) SmartDashboard.getNumber("Lift Amp Limit", Defaults.LIFT_AMP_LIMIT), 0);
		talons[0].enableCurrentLimit(true);
	}
}
