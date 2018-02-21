package org.usfirst.frc.team1351.robot.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeAuton {
    private static double INTAKESPEED = 0.4;
    private static double OUTPUTSPEED = 0.4;

    static void intake() {
    	Intake.set(ControlMode.PercentOutput, INTAKESPEED);
    }
}
