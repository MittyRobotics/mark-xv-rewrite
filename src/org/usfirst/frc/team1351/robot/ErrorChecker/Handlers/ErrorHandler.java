package org.usfirst.frc.team1351.robot.ErrorChecker.Handlers;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team1351.robot.ErrorChecker.Commands.TalonCommands.FeedbackSensor.TalonFeedbackSensorErrorCommand;
import org.usfirst.frc.team1351.robot.ErrorChecker.Commands.TalonCommands.PDIF.TalonPDIFErrorCommand;

import static com.ctre.phoenix.ErrorCode.OK;

public abstract class ErrorHandler {
	String name;


	@Deprecated
	public void handle() {}

	public void handle(TalonPDIFErrorCommand command, WPI_TalonSRX target, int slotIdx, double value, int timeoutMs) {
		handleError(command.run(target, slotIdx, value, timeoutMs));
	}

	public void handle(TalonFeedbackSensorErrorCommand command, WPI_TalonSRX target, FeedbackDevice feedbackDevice, int slotIdx, int timeoutMs) {
		handleError(command.run(target, feedbackDevice, slotIdx, timeoutMs));
	}

	/*
	tx - transmit
	rx - recieve
	arb - ?
	 */

	private void handleError(ErrorCode e) {
		if (e != OK) {
			switch (e) {
				case CAN_MSG_STALE:
					handleCanMsgStale();
					break;
				case CAN_TX_FULL:
					handleCanTxFull();
					break;
				case TxFailed:
					handleTxFailed();
					break;
				case InvalidParamValue:
					handleInvalidParamValue();
					break;
				case CAN_INVALID_PARAM:
					handleCanInvalidParam();
					break;
				case RxTimeout:
					handleRxTimeout();
					break;
				case CAN_MSG_NOT_FOUND:
					handleCanMsgNotFound();
					break;
				case TxTimeout:
					handleTxTimeout();
					break;
				case CAN_NO_MORE_TX_JOBS:
					handleCanNoMoreTxJobs();
					break;
				case UnexpectedArbId:
					handleUnexpectedArbId();
					break;
				case CAN_NO_SESSIONS_AVAIL:
					handleCanNoSessionsAvail();
					break;
				case BufferFull:
					handBufferFull();
					break;
				case CAN_OVERFLOW:
					handleCanOverflow();
					break;
				case SensorNotPresent:
					handleSensorNotPresent();
					break;
				case FirmwareTooOld:
					handleFirmwareTooOld();
					break;
				case GeneralError:
					handleGeneralError();
					break;
				case GENERAL_ERROR:
					handleGeneralError();
					break;
				case SIG_NOT_UPDATED:
					handleSigNotUpdated();
					break;
				case SigNotUpdated:
					handleSigNotUpdated();
					break;
				case NotAllPIDValuesUpdated:
					handleNotAllPIDValuesUpdated();
					break;
				case GEN_PORT_ERROR:
					handleGenPortError();
					break;
				case PORT_MODULE_TYPE_MISMATCH:
					handlePortModuleTypeMismatch();
					break;
				case GEN_MODULE_ERROR:
					handleGenModuleError();
					break;
				case MODULE_NOT_INIT_SET_ERROR:
					handleModuleNotInitSetError();
					break;
				case MODULE_NOT_INIT_GET_ERROR:
					handleModuleNotInitGetError();
					break;
				case WheelRadiusTooSmall:
					handleWheelRadiusTooSmall();
					break;
				case TicksPerRevZero:
					handleTicksPerRevZero();
					break;
				case DistanceBetweenWheelsTooSmall:
					handleDistanceBetweenWheelsTooSmall();
					break;
				case GainsAreNotSet:
					handleGainsAreNotSet();
					break;
				case IncompatibleMode:
					handleIncompatibleMode();
					break;
				case InvalidHandle:
					handleInvalidHandle();
					break;
				case PulseWidthSensorNotPresent:
					handlePulseWidthSensorNotPresent();
					break;
				case GeneralWarning:
					handleGeneralWarning();
					break;
				case FeatureNotSupported:
					handleFeatureNotSupported();
					break;
				case NotImplemented:
					handleNotImplemented();
					break;
				case FirmVersionCouldNotBeRetrieved:
					handleFirmVersionCouldNotBeRetrieved();
					break;
			}
			System.out.println(e);
		} else {
			System.out.println("Command was retired in order to work correctly.");
		}
	}

	private void handleFirmVersionCouldNotBeRetrieved() {

	}

	private void handleNotImplemented() {

	}

	private void handleFeatureNotSupported() {

	}

	private void handleGeneralWarning() {

	}

	private void handlePulseWidthSensorNotPresent() {

	}

	private void handleInvalidHandle() {

	}

	private void handleIncompatibleMode() {

	}

	private void handleGainsAreNotSet() {

	}

	private void handleDistanceBetweenWheelsTooSmall() {

	}

	private void handleTicksPerRevZero() {

	}

	private void handleWheelRadiusTooSmall() {

	}

	private void handleModuleNotInitGetError() {

	}

	private void handleModuleNotInitSetError() {

	}

	private void handleGenModuleError() {

	}

	private void handlePortModuleTypeMismatch() {

	}

	private void handleGenPortError() {

	}

	private void handleNotAllPIDValuesUpdated() {

	}

	private void handleSigNotUpdated() {

	}

	private void handleGeneralError() {

	}

	private void handleFirmwareTooOld() {

	}

	private void handleSensorNotPresent() {

	}

	private void handleCanOverflow() {

	}

	private void handBufferFull() {

	}

	private void handleCanNoSessionsAvail() {

	}

	private void handleUnexpectedArbId() {

	}

	private void handleCanNoMoreTxJobs() {

	}

	private void handleTxTimeout() {

	}

	private void handleCanMsgNotFound() {

	}

	private void handleRxTimeout() {

	}

	private void handleCanInvalidParam() {

	}

	private void handleInvalidParamValue() {

	}

	private void handleTxFailed() {

	}

	private void handleCanTxFull() {

	}

	private void handleCanMsgStale() {

	}
}
