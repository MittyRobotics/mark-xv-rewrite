package org.usfirst.frc.team1351.robot.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;


public class Intake {
    private static final int[] INTAKE_TALONS = {8, 9};

    private static WPI_TalonSRX[] talons;
    private static DoubleSolenoid intakeExtend,  intakeClose;

    /**
     * Initializes the Solenoids to the correct channels and the talons to the correct Master/Slave arrangement
     */
    public static void init() {
        intakeClose = new DoubleSolenoid(1, 0);
        intakeExtend = new DoubleSolenoid(3, 2);
        talons = new WPI_TalonSRX[INTAKE_TALONS.length];
        for (int i = 0; i < talons.length; i++) {
            talons[i] = new WPI_TalonSRX(INTAKE_TALONS[i]);
        }
        if(talons.length > 1) {
            for(int i = 1; i < talons.length; i++){
                talons[i].set(ControlMode.Follower, talons[0].getDeviceID());
            }
        }
    }

    /**
     * Sets the Master talon to a specified control mode and value
     * @param controlMode Control Mode that talon will be set to
     * @param value Value that the talon will be set to
     */
    static void set(ControlMode controlMode, double value) {
        talons[0].set(controlMode, value);
    }

    /**
     * Extends the lift into the pickup/drop position
     * @param pneuDir If you want to extend(true) or retract(false)
     */
    static void extend(boolean pneuDir) {
        if(pneuDir) {
            intakeExtend.set(DoubleSolenoid.Value.kForward);
        } else {
            intakeExtend.set(DoubleSolenoid.Value.kReverse);
        }
    }

    /**
     * Closes/opens the intake
     * @param pneuDir If you want to close(true) or open(false0 the intake
     */
    static void close(boolean pneuDir) {
        if(pneuDir) {
            intakeClose.set(DoubleSolenoid.Value.kForward);
        } else {
            intakeClose.set(DoubleSolenoid.Value.kReverse);
        }
    }
}
