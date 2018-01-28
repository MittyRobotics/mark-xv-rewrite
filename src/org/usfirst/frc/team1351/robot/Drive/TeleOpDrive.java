package org.usfirst.frc.team1351.robot.Drive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kLeft;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

public class TeleOpDrive extends Drive {
    private byte driveType = 0;

    private Thread dThread;

    public TeleOpDrive() {
        init();

        //Setups the Thread
        //TODO Change Thread Priority Based on Thread Priority List
        dThread = new Thread(this::teleOp);
        dThread.setPriority(9);
        dThread.setName("Drive Thread");
    }

    public void start() {
        dThread.start();
    }

    public void stop() {
        dThread.interrupt();
    }

    private void teleOp() {
        while (!dThread.isInterrupted()) {
            if (driveType == 0) {  //Tank Drive
                //Left Side Tank Drive
                if (controller.getY(kLeft) < -0.05 || controller.getY(kLeft) > 0.05) {
                    double yValue = controller.getY(kLeft);
                    talons[0].set(PercentOutput, -1 * yValue);
                    talons[1].set(PercentOutput, -1 * yValue);
                } else {
                    talons[0].set(PercentOutput, 0);
                    talons[1].set(PercentOutput, 0);
                }

                //Right Side Tank Drive
                if(controller.getY(kRight) < -0.05 || controller.getY(kRight) > 0.05) {
                    double yValue = controller.getY(kRight);
                    talons[2].set(PercentOutput, yValue);
                    talons[3].set(PercentOutput, yValue);
                } else {
                    talons[2].set(PercentOutput, 0);
                    talons[3].set(PercentOutput, 0);
                }

                //Manual Gear Switch
                if (controller.getAButton()) {
                    shiftUp();
                } else if (controller.getBButton()) {
                    shiftDown();
                }

                //Manual Gear Hold : Will Override Switch Mode
                if (controller.getTriggerAxis(kRight) >= 0.2) {
                    shiftDown();
                } else {
                    shiftUp();
                }
            } else if (driveType == 1) {  //Arcade Drive
                //TODO Arcade Drive
            }


            //Ramping Toggle On
            if (controller.getXButton()) {
                for (TalonSRX talon : talons) {
                    talon.configOpenloopRamp(0.5, 0);
                }
            }

            //Ramping Toggle Off
            if (controller.getYButton()) {
                for (TalonSRX talon : talons) {
                    talon.configOpenloopRamp(0, 0);
                }
            }

            //Activate Tank Drive
            if (controller.getBumper(kLeft)) {
                driveType = 0;
            }

            //Activate Arcade Drive
            if (controller.getBumper(kRight)) {
                //driveType = 1;
            }

            //Throttle Thread
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void kill() {
        dThread = null;
        killHardware();
    }
}
