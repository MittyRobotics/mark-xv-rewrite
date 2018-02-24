package org.usfirst.frc.team1351.robot.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeAuton {
    private static double INTAKE_SPEED = 0.4;
    private static double OUTPUT_SPEED = 0.4;

    /**
     * Intakes the cube at a speed known as INTAKE_SPEED
     */
    static void intake() {
    	Intake.set(ControlMode.PercentOutput, INTAKE_SPEED);
    }
}
