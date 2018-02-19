package org.usfirst.frc.team1351.robot.Winch;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class WinchTeleOp {
	private static final float LADENOUTPUT = 0.5f;
	private static final float UNLADENOUTPUT = 0.2f;
	private static final float FULLFORWARDOUTPUT = 0.1f;

	public static void laden() {
		Winch.set(ControlMode.Current, LADENOUTPUT);
	}

	public static void unladen() {
		Winch.set(ControlMode.Current, UNLADENOUTPUT);
	}

	public static void fullForward() {
		Winch.set(ControlMode.Current, FULLFORWARDOUTPUT);
	}

	public static void negLaden() {
		Winch.set(ControlMode.Current, -LADENOUTPUT);
	}

	public static void negUnladen() {
		Winch.set(ControlMode.Current, -UNLADENOUTPUT);
	}

	public static void negFullForward() {
		Winch.set(ControlMode.Current, -FULLFORWARDOUTPUT);
	}
}
