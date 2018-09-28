//package org.usfirst.frc.team1351.robot.lift;
//
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
//public class LiftOld // implements Runnable is important
//
//// to make this class support the
//// Thread (run method)
//{
//	public static Thread liftThreadAuto;
//	public static Thread liftThreadTele;
//	private static TKOLift m_Instance = null;
//	/*
//	 * This creates an object of the TKOThread class, passing it the runnable of
//	 * this class (TKOIntake) TKOThread is just a thread that makes it easy to make
//	 * using the thread safe
//	 */
//	public TKOThread liftThread = null;
//	double p = SmartDashboard.getNumber("Lift P: ", Definitions.LIFT_P);
//	double i = SmartDashboard.getNumber("Lift I: ", Definitions.LIFT_I);
//	double d = SmartDashboard.getNumber("Lift D: ", Definitions.LIFT_D);
//	double count = 0;
//	double total = 0;
//	volatile double elon = 0;
//
//	// Typical constructor made protected so that this class is only accessed
//	// statically, though that doesn't matter
//	protected TKOLift() {
//
//	}
//
//	public static synchronized TKOLift getInstance() {
//		if (m_Instance == null) {
//			m_Instance = new TKOLift();
//		}
//		return m_Instance;
//	}
//
//	public void begin() {
//		liftThreadAuto = new Thread(this::pidLiftAuto);
//		liftThreadAuto.start();
//	}
//
//	public void tele() {
//		liftThreadTele = new Thread(this::bill);
//		liftThreadTele.start();
//	}
//
//	@Deprecated
//	public void start() {
//	}
//
//	@Deprecated
//	public void pidLiftDrecated(int setpoint, int threshold) {
//		try {
//			Timer t = new Timer();
//			t.reset();
//			t.start();
//
//			TKOHardware.configLiftTalons(p, i, d, ControlMode.Position);
//			double position = TKOHardware.getLiftTalon().getSelectedSensorPosition(0);
//			double error = TKOHardware.getLiftTalon().getClosedLoopError(0);
//			TKOHardware.getLiftTalon().set(ControlMode.Position, setpoint * Definitions.LIFT_TICKS_PER_INCH);
//
//			while (Math.abs(error) >= threshold * Definitions.LIFT_TICKS_PER_INCH) {
//				error = TKOHardware.getLiftTalon().getClosedLoopError(0);
//				TKOHardware.getLiftTalon().set(ControlMode.Position, setpoint * Definitions.LIFT_TICKS_PER_INCH);
//				System.out.println("Setpoint:" + setpoint * Definitions.LIFT_TICKS_PER_INCH + "     Encoder Value: " + position + "     Error: " + error);
//				System.out.println("p: " + p + " i: " + i + " d: " + d);
//				if (t.get() > 2.5) {
//					System.out.println("TIME OUT");
//					break;
//				}
//			}
//			TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, Definitions.LIFT_HOLD_VOLT);
//			Timer.delay(Definitions.LIFT_HOLD_TIME);
//			TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, 0);
//			System.out.println("REACHED END OF PID LOOP");
//			System.out.println("Setpoint:" + setpoint * Definitions.LIFT_TICKS_PER_INCH + "     Encoder Value: " + position + "     Error: " + error);
//
//			hitLimitSwitch();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Deprecated
//	public void hitLimitSwitch() {
//		try {
//			while (!TKOHardware.getDigitalInput(DInput.LIFT_2ND_STAGE).get() || !TKOHardware.getDigitalInput(DInput.LIFT_3RD_STAGE).get() || TKOHardware.getDigitalInput(DInput.LIFT_0).get()) {
//				TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, 0);
////				talon2.set(ControlMode.PercentOutput, 0);
//				if (!TKOHardware.getDigitalInput(DInput.LIFT_3RD_STAGE).get() || !TKOHardware.getDigitalInput(DInput.LIFT_2ND_STAGE).get()) {
//
//					TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, -0.2);
////					talon2.set(ControlMode.PercentOutput, -0.2);
//					Timer.delay(0.5);
//					TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, 0);
////					talon2.set(ControlMode.PercentOutput, 0);
//
//				}
//				if (TKOHardware.getDigitalInput(DInput.LIFT_0).get()) {
//
//					TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, 0.2);
////					talon2.set(ControlMode.PercentOutput, 0.2);
//					Timer.delay(0.5);
//					TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, 0);
////					talon2.set(ControlMode.PercentOutput, 0);0
//
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	@Deprecated
//	public void stop() {
//		if (liftThread.isThreadRunning()) {
//			System.out.println("AVERAGE: " + total / count);
//
//			liftThread.setThreadRunning(false);
//		}
//	}
//
//	public void setEncoder() {
//		try {
//			while (!TKOHardware.getDigitalInput(DInput.LIFT_0).get()) {
//				TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, -0.4);
//				System.out.println(TKOHardware.getDigitalInput(DInput.LIFT_0).get() + "   " + TKOHardware.getLiftTalon().getSelectedSensorPosition(0));
//			}
//			TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, 0);
//			Timer.delay(0.5);
//			TKOHardware.getLiftTalon().setSelectedSensorPosition(0, 0, 1000);
//			System.out.println(TKOHardware.getLiftTalon().getSelectedSensorPosition((0)));
//			Timer.delay(0.5);
//			TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, 0.4);
//			Timer.delay(0.5);
//			TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, 0);
//			System.out.println(TKOHardware.getLiftTalon().getSelectedSensorPosition((0)));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Deprecated
//	public void manualLift() {
//		try {
//			if (TKOHardware.getJoystick(1).getTrigger()) { //manual lift
//				if (TKOHardware.getJoystick(1).getY() < -0.1 || TKOHardware.getJoystick(1).getY() > 0.1) {
//					double yValue = TKOHardware.getJoystick(1).getY();
//					TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, yValue);
//				} else {
//					TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, 0);
//				}
//				if (TKOHardware.getLiftTalon().getOutputCurrent() >= 35) {
//					System.out.println("ERROR Over 35 amps");
//				}
//				count++;
//				total += TKOHardware.getLiftTalon().getSelectedSensorPosition(0);
//				System.out.println("Encoder Value: " + TKOHardware.getLiftTalon().getSelectedSensorPosition(0));
////				System.out.println(TKOHardware.getLiftTalon().getSelectedSensorPosition(0));
////				System.out.println("T9: " + TKOHardware.getLiftTalon(0).getOutputCurrent());
////				System.out.println("T5: " + TKOHardware.getLiftTalon(1).getOutputCurrent());
//			} else {
//				TKOHardware.getLiftTalon().set(ControlMode.PercentOutput, 0);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void bill() {
//		try {
//			while (DriverStation.getInstance().isOperatorControl()) {
//				if (TKOHardware.getLiftTalon().getOutputCurrent() >= 15) {
//					System.out.println("ERROR is " + TKOHardware.getLiftTalon().getOutputCurrent() + " amps");
//				}
//
//				if (TKOHardware.getJoystick(1).getRawButton(2)) {
//					elon = 0;
//				} else if (TKOHardware.getJoystick(1).getRawButton(3)) {
//					elon = 84;
//				} else if (TKOHardware.getJoystick(1).getRawButton(4)) {
//					elon = 48;
//				} else if (TKOHardware.getJoystick(1).getRawButton(5)) {
//					elon = 16;
//				}
//
//				if (TKOHardware.getJoystick(1).getTrigger()) {
//					if (TKOHardware.getJoystick(1).getY() > 0.1) {
//						if (elon + 0.5 * TKOHardware.getJoystick(1).getY() > 84) {
//							elon = 84;
//						} else {
//							elon += 0.5 * TKOHardware.getJoystick(1).getY();
//						}
//						System.out.println(elon);
//					} else if (TKOHardware.getJoystick(1).getY() < -0.1) {
//						if (elon + 0.5 * TKOHardware.getJoystick(1).getY() < -1) {
//							elon = -1;
//						} else {
//							elon += 0.5 * TKOHardware.getJoystick(1).getY();
//						}
//					}
//				}
//
//				billDriverAddition();
//
//				Thread.sleep(10);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void billDriverAddition() {
//		try {
//			if (TKOHardware.getXboxController().getBumper(Hand.kLeft)) {
//				if (elon + 0.12 > 84) {
//					elon = 84;
//				} else {
//					elon += 0.12;
//				}
//				System.out.println(elon);
//			} else if (TKOHardware.getXboxController().getBumper(Hand.kRight)) {
//				if (elon - 0.12 < -1) {
//					elon = -1;
//				} else {
//					elon -= 0.12;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void pidLiftAuto() {
//		TKOHardware.configLiftTalons(p, i, d, ControlMode.Position);
//		try {
//			TKOHardware.getLiftTalon().configClosedloopRamp(0.5, 0);
//		} catch (TKOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		while (liftThreadAuto.isAlive()) {
////			System.out.println(elon + " elons");
//			while (DriverStation.getInstance().isEnabled()) {
//				try {
//					while (DriverStation.getInstance().isEnabled()) {
//						if (elon * Definitions.LIFT_TICKS_PER_INCH != TKOHardware.getLiftTalon().getClosedLoopTarget(0)) {
//							TKOHardware.getLiftTalon().set(ControlMode.Position, elon * Definitions.LIFT_TICKS_PER_INCH);
//							System.out.println(TKOHardware.getLiftTalon().getClosedLoopError(0) + " " + TKOHardware.getLiftTalon().getClosedLoopTarget(0));
//						}
//						Thread.sleep(10);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public void setElon(double value) {
//		elon = value;
//	}
//}
