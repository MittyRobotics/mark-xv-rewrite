package org.usfirst.frc.team1351.robot.Vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Vision {
	//TODO: Move camera out of init()
	public static void init() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("camera", 0);
		camera.setResolution(360, 280);
		camera.setFPS(30);
		camera.setExposureAuto();
		camera.setWhiteBalanceAuto();
	}
}
