package org.usfirst.frc.team1351.robot.Controllers;

import java.util.HashMap;
import java.util.Map;

class Controller extends Thread {
	volatile boolean isTeleOp;
	Map<String, Thread> commands = new HashMap<String, Thread>();

	public void init() {

	}


}
