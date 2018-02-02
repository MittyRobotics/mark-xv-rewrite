package org.usfirst.frc.team1351.robot.Drive;

import edu.wpi.first.wpilibj.XboxController;

import static com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kLeft;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

public class TeleOpDrive extends Drive {
    private XboxController controller;

    private byte driveType = 0;

    private Thread dThread;

    public TeleOpDrive() {
        controller = new XboxController(0);
        init();

        //Setups the Thread
        dThread = new Thread(this::teleOp);
        dThread.setPriority(9);
        dThread.setName("Drive Thread");
    }

    @Override
    public void start() {
        dThread.start();
    }

    @Override
    public void stop() {
        dThread.interrupt();
    }

    @Override
    public void forward(byte distance) {
        System.out.println("Method {forward} should not be used on TeleOpDrive. If this message is not on purpose, check if this method is being called in the right spot.");
    }

    @Override
    public void backwards(byte distance) {
        System.out.println("Method {backwards} should not be used on TeleOpDrive. If this message is not on purpose, check if this method is being called in the right spot.");
    }

    @Override
    public void turnLeft(int degrees) {
        System.out.println("Method {turnLeft} should not be used on TeleOpDrive. If this message is not on purpose, check if this method is being called in the right spot.");
    }

    @Override
    public void turnRight(int degrees) {
        System.out.println("Method {turnRight} should not be used on TeleOpDrive. If this message is not on purpose, check if this method is being called in the right spot.");
    }

    private void teleOp() {
        while (!dThread.isInterrupted()) {
            if (driveType == 0) {  //Tank Drive
                //Left Side Tank Drive
                if (controller.getY(kLeft) < -0.05 || controller.getY(kLeft) > 0.05) {
                    double yValue = controller.getY(kLeft);
                    setLeft(PercentOutput, yValue);
                } else {
                    setLeft(PercentOutput, 0);
                }

                //Right Side Tank Drive
                if(controller.getY(kRight) < -0.05 || controller.getY(kRight) > 0.05) {
                    double yValue = controller.getY(kRight);
                    setRight(PercentOutput, yValue);
                } else {
                    setRight(PercentOutput, 0);
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
            if (controller.getXButton() && controller.getStickButton(kLeft)) {
                ramp(0.5);
            }

            //Ramping Toggle Off
            if (controller.getYButton() && controller.getStickButton(kLeft)) {
                ramp(0);
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
