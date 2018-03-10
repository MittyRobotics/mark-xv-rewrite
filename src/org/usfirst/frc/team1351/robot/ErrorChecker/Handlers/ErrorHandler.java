package org.usfirst.frc.team1351.robot.ErrorChecker.Handlers;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team1351.robot.ErrorChecker.Commands.TalonCommands.FeedbackSensor.TalonFeedbackSensorErrorCommand;
import org.usfirst.frc.team1351.robot.ErrorChecker.Commands.TalonCommands.PDIF.TalonPDIFErrorCommand;
import org.usfirst.frc.team1351.robot.Logger.Logger;

import static com.ctre.phoenix.ErrorCode.OK;

public abstract class ErrorHandler {
	private ErrorCode e;


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
		this.e = e;
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
		} else {
			Logger.log("Command was retired in order to work correctly.", Logger.Scope.DRIVERSTATIONONLY);
		}
	}

	private void handleFirmVersionCouldNotBeRetrieved() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleNotImplemented() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleFeatureNotSupported() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleGeneralWarning() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handlePulseWidthSensorNotPresent() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleInvalidHandle() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleIncompatibleMode() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleGainsAreNotSet() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleDistanceBetweenWheelsTooSmall() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleTicksPerRevZero() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleWheelRadiusTooSmall() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleModuleNotInitGetError() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleModuleNotInitSetError() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleGenModuleError() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handlePortModuleTypeMismatch() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleGenPortError() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleNotAllPIDValuesUpdated() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleSigNotUpdated() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleGeneralError() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleFirmwareTooOld() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleSensorNotPresent() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleCanOverflow() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handBufferFull() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleCanNoSessionsAvail() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleUnexpectedArbId() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleCanNoMoreTxJobs() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleTxTimeout() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleCanMsgNotFound() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleRxTimeout() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleCanInvalidParam() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleInvalidParamValue() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleTxFailed() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleCanTxFull() {
		Logger.log(e, Logger.Scope.BOTH);
	}

	private void handleCanMsgStale() {
		Logger.log(e, Logger.Scope.BOTH);
	}
}
