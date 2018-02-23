package org.usfirst.frc.team1351.robot.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeTeleOp {
    static void intake() {
        Intake.set(ControlMode.PercentOutput, Intake.getIntakeSpeed());
    }

    static void release() {
        Intake.set(ControlMode.PercentOutput, Intake.getReleaseSpeed());
    }

    static void enable() {
        Intake.extend(false);
    }

    static void disable() {
        Intake.extend(true);
    }

    public static void init() {

    }

}
