package org.usfirst.frc.team1351.robot.Logger;

import com.ctre.phoenix.ErrorCode;

public class Logger {
	public enum Scope {
		BOTH, BLACKBOXONLY, DRIVERSTATIONONLY
	}

	public void log(String message, Scope scope) {
		switch (scope) {
			case BOTH:
				Broadcaster.broadcast(message);
				Blackbox.blackbox(message);
				break;

			case BLACKBOXONLY:
				Blackbox.blackbox(message);
				break;

			case DRIVERSTATIONONLY:
				Broadcaster.broadcast(message);
				break;
		}
	}

	public void log(ErrorCode errorCode, Scope scope) {
		switch (scope) {
			case BOTH:
				Broadcaster.broadcast(errorCode.name());
				Blackbox.blackbox(errorCode.name());
				break;

			case BLACKBOXONLY:
				Blackbox.blackbox(errorCode.name());
				break;

			case DRIVERSTATIONONLY:
				Broadcaster.broadcast(errorCode.name());
				break;
		}
	}
}
