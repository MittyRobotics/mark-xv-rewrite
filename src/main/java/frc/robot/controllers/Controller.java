package frc.robot.controllers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.drive.DriveTeleOp;
import frc.robot.lift.Lift;

import static edu.wpi.first.wpilibj.GenericHID.Hand.kLeft;
import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

public class Controller extends Thread {
	@Override
	public void run() {
		final XboxController xboxController = new XboxController(0);

		final Joystick[] joysticks = new Joystick[2];
		for (int i = 0; i < joysticks.length; i++) {
			joysticks[i] = new Joystick(i + 1);
		}

		while (DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isOperatorControl()) {
			synchronized (this) {
				// DRIVING CONTROLS

				// Left Side Tank drive
				if (xboxController.getY(kLeft) < -0.05 || xboxController.getY(kLeft) > 0.05) {
					DriveTeleOp.setLeft(xboxController.getY(kLeft));
				} else {
					DriveTeleOp.setLeft(0);
				}

				// Right Side Tank drive
				if (xboxController.getY(kRight) < -0.05 || xboxController.getY(kRight) > 0.05) {
					DriveTeleOp.setRight(xboxController.getY(kRight));
				} else {
					DriveTeleOp.setRight(0);
				}

				// Manual Gear Hold : Will Override Switch Mode
				if (xboxController.getTriggerAxis(kRight) >= 0.2) {
					DriveTeleOp.setGear(0);
				} else {
					DriveTeleOp.setGear(1);
				}

				// Ramping Toggle On
				if (xboxController.getBumper(kRight) && !xboxController.getBumper(kLeft)) {
					DriveTeleOp.setGear(0);
				}

				// Ramping Toggle Off
				if (xboxController.getBumper(kLeft) && !xboxController.getBumper(kRight)) {
					DriveTeleOp.setGear(1);
				}

				// OPERATOR CONTROLS
				if (joysticks[1].getY() > 0.05 && Lift.getSetpoint() > 96){
					Lift.setSetpoint(Lift.getSetpoint() + 0.1);
				}
				else if(joysticks[1].getY() < -0.05 && Lift.getSetpoint() < 0) {
					Lift.setSetpoint(Lift.getSetpoint() - 0.1);
				}
				if(joysticks[1].getRawButton(5)){
					Lift.setSetpoint(84); //scale height
				}
				if(joysticks[1].getRawButton(4)){
					Lift.setSetpoint(36); //switch height
				}
				if(joysticks[0].getY() > 0.05){
					Intake.
				}

				// THROTTLE THREAD
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

/* TODO
Tank
Right/Left Bumper-Low/High
High Default
Lift - Buttons
Right - Lift Height
Right Trigger - Intake Open/Close Toggle
Middle Rright Joystick Button -
Left Right Joystick Button - Scale
Right Right Joystick - Switch
Left Joystick - Rollers
 */
