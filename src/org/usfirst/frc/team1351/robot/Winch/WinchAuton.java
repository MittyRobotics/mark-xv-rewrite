package org.usfirst.frc.team1351.robot.Winch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;

public class WinchAuton {
	public static void climb() {
         while (DriverStation.getInstance().isEnabled() && Winch.getUltrasonic() > Winch.getWinchSize()) {
         	Winch.set(ControlMode.PercentOutput, 1);
	         try {
		         Thread.sleep(10);
	         } catch (InterruptedException e) {
		         e.printStackTrace();
	         }
         }
	}
}
