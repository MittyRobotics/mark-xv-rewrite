package org.usfirst.frc.team1351.robot.deprecated.ErrorChecker.Commands.TalonCommands.FeedbackSensor;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ConfigSelectedFeedbackSensor implements TalonFeedbackSensorErrorCommand {

	@Override
	public ErrorCode run(WPI_TalonSRX target, FeedbackDevice feedbackDevice, int slotIdx, int timeoutMs) {
		return target.configSelectedFeedbackSensor(feedbackDevice, slotIdx, timeoutMs);
	}
}
