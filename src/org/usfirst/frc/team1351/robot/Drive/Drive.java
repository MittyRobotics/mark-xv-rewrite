package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;

public abstract class Drive {
    static byte TALONCOUNT = 4;

    TalonSRX[] talons;
    private DoubleSolenoid driveSolenoid;
    XboxController controller = new XboxController(0);

    private static byte[] LEFTDRIVETALONS = {};
    private static byte[] RIGHTDRIVETALONS = {};



    public abstract void kill();

    void init() {
        controller = new XboxController(0);
        driveSolenoid = new DoubleSolenoid(0, 1);

        talons = new TalonSRX[TALONCOUNT];
        for (int i = 0; i < talons.length; i++) {
            talons[i] = new TalonSRX(i);
        }
    }

    void killHardware() {
        talons = null;
        driveSolenoid = null;
        controller = null;
    }

    void setLeft(ControlMode controlMode, double value) {
        talons[0].set(controlMode, value);
        talons[1].set(controlMode, value);
    }

    void setRight() {

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
}
