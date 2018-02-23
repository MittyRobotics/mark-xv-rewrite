package org.usfirst.frc.team1351.robot.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeAuton {

    static void intake() {
    	Intake.set(ControlMode.PercentOutput, INTAKE_SPEED);
    }
}
