package org.usfirst.frc.team1351.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import org.usfirst.frc.team1351.robot.Drive.Drive;
import org.usfirst.frc.team1351.robot.Drive.DriveAuton;


public class Robot extends SampleRobot {
	@Override
	public void robotInit() {
		Drive.init();
		DriveAuton.init();
	}

	@Override
	public void autonomous() {}

	@Override
	public void operatorControl() {}

	@Override
	public void test() {
		DriveAuton.turn(90);
	}

	@Override
	public void disabled() {}
}
