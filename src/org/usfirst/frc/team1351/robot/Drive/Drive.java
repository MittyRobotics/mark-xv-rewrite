package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;

public abstract class Drive {
    static byte TALONCOUNT = 4;

    TalonSRX[] talons;
    DoubleSolenoid driveSolenoid;
    XboxController controller = new XboxController(0);

    public abstract void kill();

    void init() {
        controller = new XboxController(0);
        driveSolenoid = new DoubleSolenoid(0, 1);

        talons = new TalonSRX[TALONCOUNT];
        for (int i = 0; i < talons.length; i++) {
            talons[i] = new TalonSRX(i);
        }
    }

    void shiftDown() {
        driveSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    void shiftUp() {
        driveSolenoid.set(DoubleSolenoid.Value.kForward);
    }
}
