package org.usfirst.frc.team1351.robot.deprecated.ErrorChecker.Commands.TalonCommands.PDIF;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ConfigD implements TalonPDIFErrorCommand {
	@Override
	public ErrorCode run(WPI_TalonSRX target, int slotIdx, double value, int timeoutMs) {
		return target.config_kD(slotIdx, value, timeoutMs);
	}
}
