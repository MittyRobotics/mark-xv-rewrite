package org.usfirst.frc.team1351.robot.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeTeleOp {
    static void intake() {
        Intake.set(ControlMode.PercentOutput, Intake.getIntakeSpeed());
    }

    static void release() {
        Intake.set(ControlMode.PercentOutput, Intake.getReleaseSpeed());
    }

    static void stop() {
        Intake.set(ControlMode.PercentOutput, 0);
    }

    static void up() {
        Intake.extend(true);
    }

    static void down() {
        Intake.extend(false);
    }

    static void open() {
        Intake.close(false);
    }

    static void close() {
        Intake.close(true);
    }

    public static void init() {

    }

}
