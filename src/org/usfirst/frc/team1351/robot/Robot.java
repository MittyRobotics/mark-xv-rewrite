package org.usfirst.frc.team1351.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SampleRobot;
import org.usfirst.frc.team1351.robot.Autonomous.Autonomous;
import org.usfirst.frc.team1351.robot.Controllers.Controller;
import org.usfirst.frc.team1351.robot.Drive.Drive;
import org.usfirst.frc.team1351.robot.Threading.ThreadHandler;


public class Robot extends SampleRobot {
	private Compressor compressor;

	@Override
	public void robotInit() {
		compressor = new Compressor(0);

		Drive.init();

		Autonomous.init();
		Controller.init();
	}

	@Override
	public void autonomous() {
		ThreadHandler.setEnabled(true);

		compressor.start();

		Autonomous.run();
	}

	@Override
	public void operatorControl() {
		ThreadHandler.setEnabled(true);
		ThreadHandler.setTeleOp(true);

		compressor.start();
	}

	@Override
	public void test() {
		//TODO USE THIS FOR ANY TESTING OF CODE
	}

	@Override
	public void disabled() {
		ThreadHandler.setEnabled(false);
		ThreadHandler.setTeleOp(false);

		compressor.stop();
	}
}
