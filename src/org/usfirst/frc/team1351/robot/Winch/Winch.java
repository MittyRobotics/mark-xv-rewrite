package org.usfirst.frc.team1351.robot.Winch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Winch {
	private static final FeedbackDevice ENCODER = FeedbackDevice.QuadEncoder;
	private static final byte[] WINCHTALONS = {4, 5};

	private static WPI_TalonSRX[] talons;


	public static void init() {
		talons = new WPI_TalonSRX[WINCHTALONS.length];
		for (int i = 0; i < talons.length; i++) {
			talons[i] = new WPI_TalonSRX(WINCHTALONS[i]);
		}

		talons[WINCHTALONS[0]].set(ControlMode.PercentOutput, 0);
		for (int i = 1; i < WINCHTALONS.length; i++) {
			talons[i].set(ControlMode.Follower, talons[0].getDeviceID());
		}

		for (byte LEFTDRIVETALON : WINCHTALONS) {
			talons[LEFTDRIVETALON].setInverted(true);
		}
	}

	static void set(ControlMode controlMode, double value) {
		talons[0].set(controlMode, value);
	}

	}

	}
}
