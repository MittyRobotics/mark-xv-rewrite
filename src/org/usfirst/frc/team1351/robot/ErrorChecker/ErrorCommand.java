package org.usfirst.frc.team1351.robot.ErrorChecker;

import com.ctre.phoenix.ErrorCode;

public interface ErrorCommand {
	@Deprecated
	ErrorCode run(Object target, Object[] data);
}
