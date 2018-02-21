package org.usfirst.frc.team1351.robot.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeTeleOp {
    private static double INTAKESPEED = 0.4;
    private static double OUTPUTSPEED = 0.4;

    static void intake() {
        Intake.set(ControlMode.PercentOutput, INTAKESPEED);
    }

    static void outtake() {
        Intake.set(ControlMode.PercentOutput, OUTPUTSPEED);
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
