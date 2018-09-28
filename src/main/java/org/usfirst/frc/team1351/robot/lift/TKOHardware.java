//package org.usfirst.frc.team1351.robot.lift;
//
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.NeutralMode;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.can.CANMessageNotFoundException;
//import edu.wpi.first.wpilibj.util.AllocationException;
//
//public class TKOHardware {
//	protected static WPI_TalonSRX liftTalons[] = new WPI_TalonSRX[Definitions.NUM_LIFT_TALONS];
//
//	public static synchronized void initLiftTalons() {
//		for (int i = 0; i < Definitions.NUM_LIFT_TALONS; i++) {
//			if (liftTalons[i] == null) {
//				try {
//					liftTalons[i] = new WPI_TalonSRX(Definitions.LIFT_TALON_ID[i]);
//				} catch (AllocationException | CANMessageNotFoundException e) {
//					e.printStackTrace();
//					System.out.println("MOTOR CONTROLLER " + i + " NOT FOUND OR IN USE");
//					TKOLogger.getInstance().addMessage("MOTOR CONTROLLER " + i + " CAN ERROR");
//				}
//			}
//		}
//
//		configLiftTalons(Definitions.LIFT_P, Definitions.LIFT_I, Definitions.LIFT_D, Definitions.LIFT_TALONS_NORMAL_CONTROL_MODE);
//	}
//
//
//	public static synchronized void configLiftTalons(double p, double I, double d, ControlMode mode) {
//		for (int i = 0; i < Definitions.NUM_LIFT_TALONS; i++) {
//			liftTalons[i] = null;
//			liftTalons[i] = new WPI_TalonSRX(Definitions.LIFT_TALON_ID[i]);
//			if (liftTalons[i] != null) {
//				if (i % 2 == 1) // if follower
//				{
//					liftTalons[i].set(ControlMode.Follower, liftTalons[i - 1].getDeviceID()); // set to follow the TalonSRX with id i - 1;
//				} else
//				// if not follower
//				{
//					liftTalons[i].set(mode, 0);
//					liftTalons[i].configSelectedFeedbackSensor(Definitions.LIFT_ENCODER_TYPE, 0, Definitions.DEFAULT_TIMEOUT);
//					liftTalons[i].config_kP(0, p, Definitions.DEFAULT_TIMEOUT);
//					liftTalons[i].config_kI(0, i, Definitions.DEFAULT_TIMEOUT);
//					liftTalons[i].config_kD(0, d, Definitions.DEFAULT_TIMEOUT);
//				}
//				liftTalons[i].setNeutralMode(Definitions.LIFT_NEUTRAL_MODE[i]);
//				liftTalons[i].setInverted(Definitions.LIFT_REVERSE_OUTPUT_MODE[i]);
//				liftTalons[i].setSensorPhase(Definitions.LIFT_REVERSE_SENSOR[i]);
//				liftTalons[i].configOpenloopRamp(Definitions.LIFT_RAMP, Definitions.DEFAULT_TIMEOUT);
//				liftTalons[i].configClosedloopRamp(Definitions.LIFT_RAMP, Definitions.DEFAULT_TIMEOUT);// TODO change ramp rate for pid
//				liftTalons[i].configForwardSoftLimitEnable(false, Definitions.DEFAULT_TIMEOUT);
//				liftTalons[i].configReverseSoftLimitEnable(false, Definitions.DEFAULT_TIMEOUT);
//				liftTalons[i].configContinuousCurrentLimit(25, 0);
//				liftTalons[i].enableCurrentLimit(true);
//
//			}
//		}
//	}
//
//
//	public static synchronized void changeTalonMode(WPI_TalonSRX target, ControlMode newMode, double newP, double newI,
//	                                                double newD) throws TKOException {
//		if (target == null)
//			throw new TKOException("ERROR Attempted to change mode of null TalonSRX");
//		if (newMode == target.getControlMode()) {
//			target.config_kP(0, newP, Definitions.DEFAULT_TIMEOUT);
//			target.config_kI(0, newI, Definitions.DEFAULT_TIMEOUT);
//			target.config_kD(0, newD, Definitions.DEFAULT_TIMEOUT);
//			return;
//		} else {
//			// if (target.getControlMode() != TalonSRX.TalonControlMode.Position && target.getControlMode() != TalonSRX.TalonControlMode.Speed)
//			target.configSelectedFeedbackSensor(Definitions.DEFAULT_ENCODER_TYPE, 0, Definitions.DEFAULT_TIMEOUT);
//
//			//TODO: print PID
//
//			target.set(newMode, 0);
//			target.config_kP(0, newP, Definitions.DEFAULT_TIMEOUT);
//			target.config_kI(0, newI, Definitions.DEFAULT_TIMEOUT);
//			target.config_kD(0, newD, Definitions.DEFAULT_TIMEOUT);
//			//TODO: figure out if ya need enable
////		target.enableControl();
//			System.out.println("CHANGED TALON [" + target.getDeviceID() + "] TO [" + target.getControlMode() + "]");
//		}
//	}
//
//	public static synchronized WPI_TalonSRX getLiftTalon() throws TKOException {
//		if (liftTalons[0] == null) {
//			throw new TKOException("Lift Talon 0 is null");
//		}
//		return liftTalons[0];//liftTalons[0];
//	}
//}
