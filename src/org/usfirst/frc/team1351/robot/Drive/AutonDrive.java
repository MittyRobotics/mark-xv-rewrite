package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class AutonDrive extends Drive {
    private static final float[] TICKSPERINCH = {487.5f, 487.5f}; //Ticks per inch values (Low - 0, High - 1)
	private float proportionalConstant, integralConstant, derivativeConstant;
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
	
	@Override
    public void forward(byte distance, byte gear) {
        setGear(gear);

    	double leftSetpoint = distance * TICKSPERINCH[gear];
    	double rightSetpoint = distance * TICKSPERINCH[gear];
    	leftSetpoint += getLeftEncoder();
    	rightSetpoint += getRightEncoder();
    	if (leftSetpoint > 0) {
	    	while (DriverStation.getInstance().isEnabled() && getRightTarget() < rightSetpoint && getLeftTarget() < leftSetpoint){
	    		setRight(ControlMode.Position, getRightTarget() + incrementer);
	    		setLeft(ControlMode.Position, getLeftTarget() + incrementer);
	    		Timer.delay(0.01);
	    	}
    	}
    }

    @Override
    public void backwards(byte distance, byte gear) {
	    setGear(gear);

    	double leftSetpoint = distance * TICKSPERINCH[gear];
    	double rightSetpoint = distance * TICKSPERINCH[gear];
    	leftSetpoint += getLeftEncoder();
    	rightSetpoint += getRightEncoder();
    	if (leftSetpoint > 0) {
	    	while (DriverStation.getInstance().isEnabled() && getRightTarget() > rightSetpoint && getLeftTarget() > leftSetpoint){
	    		setRight(ControlMode.Position, getRightTarget() - incrementer);
	    		setLeft(ControlMode.Position, getLeftTarget() - incrementer);
	    		Timer.delay(0.01);
	    	}
    	}
    }

    @Override
    public void turnLeft(int degrees, byte gear) {
        setGear(gear);
        //TODO Use a PID Loop to Turn Left {degrees} Degrees Using the Gyro
    }

    @Override
    public void turnRight(int degrees, byte gear) {
        setGear(gear);
        //TODO Use a PID Loop to Turn Right {degrees} Degrees Using the Gyro
    }
    
    private void initAuton() {
    	setRight(ControlMode.Position, 0);
    	setLeft(ControlMode.Position, 0);
    	setPIDF(proportionalConstant, integralConstant, derivativeConstant);
    }

    private void setGear(byte gear) {
	     switch (gear) {
             case 0:
                 shiftDown();
                 break;

             case 1:
                 shiftUp();
                 break;

             default:
                 System.out.println("Gear Not Found");
                 break;
         }
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
