package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
public class AutonDrive extends Drive {
	private double incrementer, ticksPerInch, threshold;
	private double proportionalConstant, integralConstant, derivativeConstant;
	private int gear; // 0 - Low; 1 - High
	
	/**
	 * Converts distance into ticks, sets the gear ratio, and sets the threshold
	 * @param threshold The distance in tick off
	 * @param gear The setting that the gear is in (0 - Low; 1 - High)
	 */
	private AutonDrive(double distance, double threshold, int gear){
		ticksPerInch = 487.5; //TODO: Get correct values and update for High and Low Gear
		proportionalConstant = 0; // SmartDashboard.getNumber("Drive P: " 0.f);
		integralConstant = 0; // SmartDashboard.getNumber("Drive I: " 0.f);
		derivativeConstant = 0; // SmartDashboard.getNumber("Drive D: " 0.f);
		this.gear = gear;
		this.threshold = threshold;
	}
	
    public void forward(byte distance) {
        //TODO Use a PID Loop to Drive Forward {distance} Inches Using the Encoders
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
