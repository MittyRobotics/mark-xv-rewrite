package org.usfirst.frc.team1351.robot.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeAuton {
    	Intake.set(ControlMode.PercentOutput, Intake.getIntakeSpeed());

    static void intake() {
    }
}
