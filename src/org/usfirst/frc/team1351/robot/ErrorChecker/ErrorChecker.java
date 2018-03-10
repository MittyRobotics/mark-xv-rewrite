package org.usfirst.frc.team1351.robot.ErrorChecker;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team1351.robot.ErrorChecker.Commands.TalonCommands.FeedbackSensor.ConfigSelectedFeedbackSensor;
import org.usfirst.frc.team1351.robot.ErrorChecker.Commands.TalonCommands.FeedbackSensor.TalonFeedbackSensorErrorCommand;
import org.usfirst.frc.team1351.robot.ErrorChecker.Commands.TalonCommands.PDIF.*;
import org.usfirst.frc.team1351.robot.ErrorChecker.Handlers.DriveErrorHandler;

import static com.ctre.phoenix.ErrorCode.OK;

public class ErrorChecker {
	@Deprecated
	public static void handleCommand(ErrorCommand command, Object target, Object[] data, String source) {
		System.out.println("This is Deprecated, Please Use The Correct Method!"); // TODO Incorporate Into Logger
		if (command.run(target, data) != OK) {
			source = source.toLowerCase();
			sendToHandler(command, target, data, source);
		}
	}

	public static void handledConfig_kP(WPI_TalonSRX target, int slotIdx, double value, int timeoutMs, String source) {
		TalonPDIFErrorCommand command = new ConfigP();
		if (command.run(target, slotIdx, value, timeoutMs) != OK) {
			sendTalonPDIFCommandToHandler(command, target, slotIdx, value, timeoutMs, source);
		}
	}

	public static void handledConfig_kI(WPI_TalonSRX target, int slotIdx, double value, int timeoutMs, String source) {
		TalonPDIFErrorCommand command = new ConfigI();
		if (command.run(target, slotIdx, value, timeoutMs) != OK) {
			sendTalonPDIFCommandToHandler(command, target, slotIdx, value, timeoutMs, source);
		}
	}
	public static void handledConfig_kD(WPI_TalonSRX target, int slotIdx, double value, int timeoutMs, String source) {
		TalonPDIFErrorCommand command = new ConfigD();
		if (command.run(target, slotIdx, value, timeoutMs) != OK) {
			sendTalonPDIFCommandToHandler(command, target, slotIdx, value, timeoutMs, source);
		}
	}
	public static void handledConfig_kF(WPI_TalonSRX target, int slotIdx, double value, int timeoutMs, String source) {
		TalonPDIFErrorCommand command = new ConfigF();
		if (command.run(target, slotIdx, value, timeoutMs) != OK) {
			sendTalonPDIFCommandToHandler(command, target, slotIdx, value, timeoutMs, source);
		}
	}


	public static void handledConfigSelectedFeedbackSensor(WPI_TalonSRX target, FeedbackDevice feedbackDevice, int slotIdx, int timeoutMs, String source) {
		ConfigSelectedFeedbackSensor command = new ConfigSelectedFeedbackSensor();
		if (command.run(target, feedbackDevice, slotIdx, timeoutMs) != OK) {
			sendTalonFeedbackSensorCommandToHandler(command, target, feedbackDevice, slotIdx, timeoutMs, source);
		}
	}

	@Deprecated
	private static void sendToHandler(ErrorCommand command, Object target, Object[] data, String source) {
		switch (source) {
			case "drive":
				break;

			case "intake":
				break;

			case "lift":
				break;

			case "winch":
				break;

			case "vision":
				break;

			case "autonomous":
				break;

			case "controllers":
				break;

			default:
				break;
		}
	}

	private static void sendTalonPDIFCommandToHandler(TalonPDIFErrorCommand command, WPI_TalonSRX target, int slotIdx, double value, int timeoutMs, String source) {
		switch (source) {
			case "drive":
				DriveErrorHandler driveErrorHandler = new DriveErrorHandler();
				driveErrorHandler.handle(command, target, slotIdx, value, timeoutMs);
				break;

			case "intake":
				break;

			case "lift":
				break;

			case "winch":
				break;

			case "vision":
				break;

			case "autonomous":
				break;

			case "controllers":
				break;

			default:
				break;
		}
	}

	private static void sendTalonFeedbackSensorCommandToHandler(TalonFeedbackSensorErrorCommand command, WPI_TalonSRX target, FeedbackDevice feedbackDevice, int slotIdx, int timeoutMs, String source) {
		switch (source) {
			case "drive":
				DriveErrorHandler driveErrorHandler = new DriveErrorHandler();
				driveErrorHandler.handle(command, target, feedbackDevice, slotIdx, timeoutMs);
				break;

			case "intake":
				break;

			case "lift":
				break;

			case "winch":
				break;

			case "vision":
				break;

			case "autonomous":
				break;

			case "controllers":
				break;

			default:
				break;
		}
	}
}
