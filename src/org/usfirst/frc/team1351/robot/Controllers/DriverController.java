package org.usfirst.frc.team1351.robot.Controllers;

import edu.wpi.first.wpilibj.XboxController;

public class DriverController extends Controller {
	XboxController controller;


	@Override
	public void run() {
		this.setName("Driver Controller Thread");
		this.setPriority(10);
		this.setDaemon(false);

		while (shouldRun) {

		}
	}
}
