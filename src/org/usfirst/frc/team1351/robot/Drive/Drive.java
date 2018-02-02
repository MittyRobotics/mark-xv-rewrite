package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public abstract class Drive {
    private static final FeedbackDevice ENCODER = FeedbackDevice.QuadEncoder;

    private TalonSRX[] talons;
    private DoubleSolenoid driveSolenoid;
    private ADXRS450_Gyro gyro;

    private static byte[] LEFTDRIVETALONS = {0, 1};
    private static byte[] RIGHTDRIVETALONS = {2, 3};


    public abstract void kill();
    public abstract void start();
    public abstract void stop();

    void init() {
        driveSolenoid = new DoubleSolenoid(0, 1);
        gyro = new ADXRS450_Gyro();


        talons = new TalonSRX[LEFTDRIVETALONS.length + RIGHTDRIVETALONS.length];
        for (int i = 0; i < talons.length; i++) {
            talons[i] = new TalonSRX(i);
        }
        
        
        //Sets Left encoder talon and sets the rest as Followers
        talons[LEFTDRIVETALONS[0]].set(ControlMode.PercentOutput,0);
        for(int i = 1; i < LEFTDRIVETALONS.length; i++)
        {
        	talons[LEFTDRIVETALONS[i]].set(ControlMode.Follower,talons[0].getDeviceID());
        }
        
        
        //Sets Right encoder talons and sets the rest as Followers
        talons[RIGHTDRIVETALONS[0]].set(ControlMode.PercentOutput, 0);
        for(int i = 1; i < LEFTDRIVETALONS.length; i++)
        {
        	talons[LEFTDRIVETALONS[i]].set(ControlMode.Follower,talons[0].getDeviceID());
        }
        
        
        //Inverts Left Talons
        for (byte LEFTDRIVETALON : LEFTDRIVETALONS) {
            talons[LEFTDRIVETALON].setInverted(true);
        }
    }

    void killHardware() {
        talons = null;
        driveSolenoid = null;
    }

    void setLeft(ControlMode controlMode, double value) {
        talons[LEFTDRIVETALONS[0]].set(controlMode, value);
    }

    void setRight(ControlMode controlMode, double value) {
        talons[RIGHTDRIVETALONS[0]].set(controlMode, value);
    }

    void shiftDown() {
        driveSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    void shiftUp() {
        driveSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    void ramp(double seconds) {
        for (TalonSRX talon : talons) {
            talon.configOpenloopRamp(seconds, 0);
        }
    }

    /**
     * Sets the left and right encoder talons PIDF values and default encoder.
     * @param P Proportional Constant
     * @param I Integral Constant
     * @param D Derivative Constant 
     * @param F Feedforward Constant
     */
    void setPIDF(double P, double I, double D, double F) {
        talons[RIGHTDRIVETALONS[0]].config_kP(0, P, 0);
        talons[RIGHTDRIVETALONS[0]].config_kI(0, I, 0);
        talons[RIGHTDRIVETALONS[0]].config_kD(0, D, 0);
        talons[RIGHTDRIVETALONS[0]].config_kF(0, F, 0);
        talons[RIGHTDRIVETALONS[0]].configSelectedFeedbackSensor(ENCODER, 0, 1000);
        
        
        talons[LEFTDRIVETALONS[0]].config_kP(0, P, 0);
        talons[LEFTDRIVETALONS[0]].config_kI(0, I, 0);
        talons[LEFTDRIVETALONS[0]].config_kD(0, D, 0);
        talons[LEFTDRIVETALONS[0]].config_kF(0, F, 0);
        talons[LEFTDRIVETALONS[0]].configSelectedFeedbackSensor(ENCODER, 0, 1000);
    }

    double getGyro() {
        return gyro.getAngle();
    }
}
