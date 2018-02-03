package org.usfirst.frc.team1351.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Compressor;


public class Robot extends SampleRobot {
	private Compressor compressor;

    @Override
    public void robotInit() {
        //Initializes Auton and TeleOp Objects
        compressor = new Compressor(0);
    }

    @Override
    public void autonomous() {
        setupAutonomous();
    }

    @Override
    public void operatorControl() {
        setupOperatorControl();

        //Starts Compressor
        compressor.start();
    }

    @Override
    public void test() {
    	//TODO USE THIS FOR ANY TESTING OF CODE
    }

    @Override
    public void disabled() {
        //Turns Off Aspects
        compressor.stop();
    }

    private void setupOperatorControl() {

    }

    private void setupAutonomous() {

    }
}
