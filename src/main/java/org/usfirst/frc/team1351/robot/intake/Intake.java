package org.usfirst.frc.team1351.robot.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake {
	private static final int[] INTAKE_TALONS = {6, 7};

	private static WPI_TalonSRX[] talons;
	private static DoubleSolenoid intakeExtend, intakeClose40, intakeClose60;

	public static void init() {
		talons = new WPI_TalonSRX[INTAKE_TALONS.length];
		for (int i = 0; i < talons.length; i++) {
			talons[i] = new WPI_TalonSRX(INTAKE_TALONS[i]);
			if (i > 0) {
				talons[0].setInverted(true);
				talons[i].set(ControlMode.Follower, INTAKE_TALONS[0]);
			}
		}
		intakeClose40 = new DoubleSolenoid(2, 3);
		intakeClose60 = new DoubleSolenoid(0, 1);
		intakeExtend = new DoubleSolenoid(4, 5);
	}

	static void set(ControlMode controlMode, double value) {
		talons[0].set(controlMode, value);
	}

	static void extend(boolean shouldExtend) {
		if (shouldExtend) {
			intakeExtend.set(DoubleSolenoid.Value.kForward);
		} else {
			intakeExtend.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public static synchronized void setArmsHardHold() {
		intakeClose40.set(Value.kReverse); //40 branch off
		intakeClose60.set(Value.kForward); //60 branch on
	}

	public static synchronized void setArmsRelease() {
		intakeClose40.set(Value.kForward); //40 branch on
		intakeClose60.set(Value.kReverse); //60 branch off
	}

	static void intake() {
		talons[0].set(ControlMode.PercentOutput, 1);
	}

	static void extake() {
		talons[0].set(ControlMode.PercentOutput, -1);
	}
}
