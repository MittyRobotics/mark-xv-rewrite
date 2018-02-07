package org.usfirst.frc.team1351.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SampleRobot;

import org.usfirst.frc.team1351.robot.Autonomous.Autonomous;
import org.usfirst.frc.team1351.robot.Controllers.Controller;
import org.usfirst.frc.team1351.robot.Drive.Drive;
import org.usfirst.frc.team1351.robot.Vision.Vision;

public class Robot extends SampleRobot {
	private Compressor compressor;

	@Override
	public void robotInit() {
		compressor = new Compressor(0);

		Drive.init();
		Vision.init();

		Autonomous.init();
		Controller.init();
	}

	@Override
	public void autonomous() {
		compressor.start();
	}

	@Override
	public void operatorControl() {
		compressor.start();
	}

	@Override
	public void test() {
		//TODO USE THIS FOR ANY TESTING OF CODE
	}

	@Override
	public void disabled() {
		compressor.stop();
	}
}
