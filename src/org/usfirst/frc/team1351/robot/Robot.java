package org.usfirst.frc.team1351.robot;

import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team1351.robot.Drive.AutonDrive;
import org.usfirst.frc.team1351.robot.Drive.Drive;
import org.usfirst.frc.team1351.robot.Drive.TeleOpDrive;

public class Robot extends SampleRobot {
    private Compressor compressor;
    private Drive drive;


    @Override
    public void robotInit() 
    {
        //Initializes Auton and TeleOp Objects
        compressor = new Compressor(0);
    }

    @Override
    public void autonomous() 
    {
        setupAutonomous();

    }

    @Override
    public void operatorControl() 
    {
        setupOperatorControl();

        //Starts Compressor
        compressor.start();

        //Starts Drive
    }

    @Override
    public void test() 
    {
        //Starts Compressor (Use to Pressurize Before Running Teleop / Matches)
        compressor.start();
    }

    @Override
    public void disabled() {
        //Turns Off Aspects
        compressor.stop();

        //Drive
        drive.kill();
        drive = null;
    }

    private void setupOperatorControl() {
        drive = new TeleOpDrive();
    }

    private void setupAutonomous() {
        drive = new AutonDrive();
    }
}
