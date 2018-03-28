package org.usfirst.frc.team1351.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import org.usfirst.frc.team1351.robot.Controllers.Controller;
import org.usfirst.frc.team1351.robot.Drive.Drive;

public class Robot extends SampleRobot {
	Controller controller = new Controller();
	@Override
	public void robotInit() {
		Drive.init();
	}

	@Override
	public void autonomous() {}

	@Override
	public void operatorControl() {
		controller.run();
	}

	@Override
	public void test() {}

	@Override
	public void disabled() {}
}
