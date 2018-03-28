package org.usfirst.frc.team1351.robot.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeTeleOp {
    private static double INTAKE_SPEED = 0.4;
    private static double OUTPUT_SPEED = 0.4;

    /**
     * Intakes the cube at a set speed
     */
    static void intake() {
        Intake.set(ControlMode.PercentOutput, INTAKE_SPEED);
    }

    /**
     * Releases cube from robot
     */
    static void outtake() {
        Intake.set(ControlMode.PercentOutput, OUTPUT_SPEED);
    }

    static void stop() {
        Intake.set(ControlMode.PercentOutput, 0);
    }

    /**
     * Lifts the intake into passive position
     */
    static void up() {
        Intake.extend(true);
    }

    /**
     * Drops intake into active position
     */
    static void down() {
        Intake.extend(false);
    }

    /**
     * Opens the intake
     */
    static void open() {
        Intake.close(false);
    }

    /**
     * Closes the Intake
     */
    static void close() {
        Intake.close(true);
    }

    public static void init() {

    }
}
