package org.usfirst.frc.team1351.robot.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;


public class Intake {
    private static final int[] INTAKETALONS = {8, 9};

    private static WPI_TalonSRX[] talons;
    private static DoubleSolenoid intakeExtend,  intakeClose;


    public static void init() {
        intakeClose = new DoubleSolenoid(1, 0);
        intakeExtend = new DoubleSolenoid(3, 2);
        talons = new WPI_TalonSRX[INTAKETALONS.length];
        for (int i = 0; i < talons.length; i++) {
            talons[i] = new WPI_TalonSRX(INTAKETALONS[i]);
        }
        if(talons.length > 1) {
            for(int i = 1; i < talons.length; i++){
                talons[i].set(ControlMode.Follower, talons[0].getDeviceID());
            }
        }
    }

    static void set(ControlMode controlMode, double value) {
        talons[0].set(controlMode, value);
    }

    static void extend(boolean pneuDir) {
        if(pneuDir) {
            intakeExtend.set(DoubleSolenoid.Value.kForward);
        } else {
            intakeExtend.set(DoubleSolenoid.Value.kReverse);
        }
    }

    static void close(boolean pneuDir) {
        if(pneuDir) {
            intakeClose.set(DoubleSolenoid.Value.kForward);
        } else {
            intakeClose.set(DoubleSolenoid.Value.kReverse);
        }
    }
}
