package org.usfirst.frc.team1351.robot.ErrorChecker.Commands.TalonCommands.PDIF;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface TalonPDIFErrorCommand {
	ErrorCode run(WPI_TalonSRX target, int slotIdx, double value, int timeoutMs);
}
