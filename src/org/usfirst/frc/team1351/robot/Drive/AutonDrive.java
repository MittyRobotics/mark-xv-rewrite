package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class AutonDrive extends Drive {
    private static final float[] TICKSPERINCH = {487.5f, 487.5f}; //Ticks per inch values (Low - 0, High - 1)
	private float proportionalConstant, integralConstant, derivativeConstant;
	private boolean isLowGear; // T - Low : F - High
	private int incrementer;
	
	/**
	 * Converts distance into ticks, sets the gear ratio, and sets the threshold
	 */
	public AutonDrive() {
		proportionalConstant = 0; // SmartDashboard.getNumber("Drive P: " 0.f);
		integralConstant = 0; // SmartDashboard.getNumber("Drive I: " 0.f);
		derivativeConstant = 0; // SmartDashboard.getNumber("Drive D: " 0.f);
		incrementer = 500;
        shiftDown();
	}
	
    public void forward(byte distance, byte gear) {
    	double leftSetpoint = distance* TICKSPERINCH[gear];
    	double rightSetpoint = distance* TICKSPERINCH[gear];
    	leftSetpoint += getLeftEncoder();
    	rightSetpoint += getRightEncoder();
    	while(DriverStation.getInstance().isEnabled() && getRightTarget() < rightSetpoint && getLeftTarget() < leftSetpoint){
    		setRight(ControlMode.Position, getRightTarget());
    		setLeft(ControlMode.Position, getLeftTarget());
    		Timer.delay(0.01);
    	}
    }

    public void backwards(byte distance) {
        //TODO Use a PID Loop to Drive Backwards {distance} Inches Using the Encoders
    }

    public void turnLeft(int degrees) {
        //TODO Use a PID Loop to Turn Left {degrees} Degrees Using the Gyro
    }

    public void turnRight(int degrees) {
        //TODO Use a PID Loop to Turn Right {degrees} Degrees Using the Gyro
    }
    
    private void initAuton() {
    	setRight(ControlMode.Position, 0);
    	setLeft(ControlMode.Position, 0);
    	setPIDF(proportionalConstant, integralConstant, derivativeConstant, 0);
    }

    @Override
    public void kill() {

    }

    @Override
    public void start() {
    	initAuton();
    }

    @Override
    public void stop() {

    }

}
