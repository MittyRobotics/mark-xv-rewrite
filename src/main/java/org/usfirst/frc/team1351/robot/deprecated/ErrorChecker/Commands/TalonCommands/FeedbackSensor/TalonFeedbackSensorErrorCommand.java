package org.usfirst.frc.team1351.robot.deprecated.ErrorChecker.Commands.TalonCommands.FeedbackSensor;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface TalonFeedbackSensorErrorCommand {
	ErrorCode run(WPI_TalonSRX target, FeedbackDevice feedbackDevice, int slotIdx, int timeoutMs);
}
