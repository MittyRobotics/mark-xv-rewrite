package org.usfirst.frc.team1351.robot.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeAuton {
    public static void intake() {
    	Intake.set(ControlMode.PercentOutput, Intake.getIntakeSpeed());
    }

    public static void release() {

    /**
     * Intakes the cube at a speed known as INTAKE_SPEED
     */
    static void intake() {
    	Intake.set(ControlMode.PercentOutput, INTAKE_SPEED);
    }
}
