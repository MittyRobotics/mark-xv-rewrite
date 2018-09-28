package org.usfirst.frc.team1351.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import org.usfirst.frc.team1351.robot.drive.Drive;
import org.usfirst.frc.team1351.robot.drive.DriveAuton;

public class Robot extends SampleRobot {
	@Override
	public void robotInit() {
		Drive.init();
		DriveAuton.init();
	}

	@Override
	public void autonomous() {
		DriveAuton.move(36);
	}

	@Override
	public void operatorControl() {

	}

	@Override
	public void test() {

	}

	@Override
	public void disabled() {

	}
}
