package org.usfirst.frc.team1351.robot.ErrorChecker.Commands.TalonCommands.PDIF;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ConfigP implements TalonPDIFErrorCommand {
	@Override
	public ErrorCode run(WPI_TalonSRX target, int slotIdx, double value, int timeoutMs) {
		return target.config_kP(slotIdx, value, timeoutMs);
	}
}
