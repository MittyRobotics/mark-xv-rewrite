package frc.robot.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Defaults;

public final class Lift {
	private static final int TALON_IDS[] = {9, 5};

	private static WPI_TalonSRX[] talons;

	public static void init() {
		talons = new WPI_TalonSRX[TALON_IDS.length];
		for (int i = 0; i < TALON_IDS.length; i++) {
			talons[i] = new WPI_TalonSRX(TALON_IDS[i]);
			talons[i].setNeutralMode(NeutralMode.Coast);
			if (i > 0) {
				talons[i].set(ControlMode.Follower, TALON_IDS[0]);
			}
		}
	}

	public static void set(final double value) {
		talons[0].set(ControlMode.PercentOutput, value);
	}

	private static void configAmpLimit() {
		talons[0].configContinuousCurrentLimit((int) SmartDashboard.getNumber("Lift Amp Limit", Defaults.LIFT_AMP_LIMIT), 0);
		talons[0].enableCurrentLimit(true);
	}
}
