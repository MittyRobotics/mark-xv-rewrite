//package org.usfirst.frc.team1351.robot.lift;
//
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import org.usfirst.frc.team1351.robot.atoms.Atom;
//import org.usfirst.frc.team1351.robot.evom.TKOLift;
//import org.usfirst.frc.team1351.robot.util.TKOException;
//
///**
// * @author Louis
// * <p>
// * Last edited by Tiina Otala, 3/17/2018
// */
//@Deprecated
//public class LiftAtom extends Atom {
//
//	int setting; // 1 = switch, 2 = scale
//	double p, i, d;
//	double distance;
//
//	//	Ultrasonic liftUS = new Ultrasonic(0, 1, Ultrasonic.Unit.kInches);
//	@Deprecated
//	public LiftAtom(int distance) {
//		p = SmartDashboard.getNumber("Lift P: ", 0f);
//		i = SmartDashboard.getNumber("Lift I: ", 0f);
//		d = SmartDashboard.getNumber("Lift D: ", 0f);
//		this.distance = distance;
//	}
//
//	@Deprecated
//	public void init() {
//		try {
//			TKOHardware.autonInit(p, i, d);
//		} catch (TKOException e) {
//			e.printStackTrace();
//			System.out.println("Talon initialization failed!");
//		}
//		System.out.println("Lift atom initialized");
//	}
//
//	@Deprecated
//	@Override
//	public void execute() {
//		System.out.println("Executing Lift Atom");
//		TKOLift.getInstance().setElon(distance);
//	}
//
//		/*
//		Timer t = new Timer();
//		t.reset();
//		t.start();
//		try
//		{
//			System.out.println("Distance: " + distance);
//			{
//				TKOHardware.getLiftTalon().set(ControlMode.Position, distance);
//				System.out.println("Setpoint: " + TKOHardware.getLiftTalon().getClosedLoopTarget(Definitions.LIFT_SLOT));
//
//				//run loop while enabled and outside of threshold
//				while (DriverStation.getInstance().isEnabled()
//				&& (Math.abs(TKOHardware.getLiftTalon().getClosedLoopError(0)) > Definitions.LIFT_THRESHOLD))
//				{
//					//checking for danger zone
//					if (TKOHardware.getLiftTalon().getOutputCurrent() > 40) {
//						System.out.println("ERROR: Burnout range!");
//						break;
//					}
//					//checking for timeout in PID loop. current timeout: 7.5 seconds.
//					if (t.get() > 3.5)
//					{
//						System.out.println("ERROR: Timeout in drive atom!");
//						break;
//					}
//					//print statements
//					System.out.println("Lift Encoder: "  + TKOHardware.getLiftTalon().getSelectedSensorPosition(0));
//					Timer.delay(0.01);
//				}
//				System.out.println("Position at end of atom - Lift: " + TKOHardware.getLiftTalon().getSelectedSensorPosition(0));
//				Timer.delay(0.005);
//				TKOHardware.getLiftTalon(0).set(ControlMode.PercentOutput, 0);
//			}
//			/*
//			else if (setting == 2)
//			{
//				// should use ultrasonic sensor to determine appropriate height
//				dist = 64 * Definitions.TICKS_PER_INCH;
//				double range = liftUS.getRangeInches();
//				double pRange = range;
//				boolean topReached = false;
//				while (DriverStation.getInstance().isEnabled())
//				{
//					range = liftUS.getRangeInches();
//					if (range - pRange < -0.1 && !topReached)
//					{
//						dist = 14 * Definitions.TICKS_PER_INCH;
//						topReached = true;
//					}
//					else if (range - pRange > 0.1 && topReached)
//					{
//						dist = 13 * Definitions.TICKS_PER_INCH;
//					}
//					if (dist > 0)
//					{
//						while (TKOHardware.getLiftTalon().getClosedLoopTarget(0) < dist)
//						{
//							System.out.println("Lift Error: " + TKOHardware.getLiftTalon().getClosedLoopError(0) + " Lift Setpoint: " + TKOHardware.getLiftTalon().getClosedLoopTarget(0));
//							TKOHardware.getLiftTalon().set(ControlMode.Position, TKOHardware.getLiftTalon().getClosedLoopTarget(0) + incrementer);
//						}
//					}
//					else
//					{
//						while (TKOHardware.getLiftTalon().getClosedLoopTarget(0) > dist)
//						{
//							System.out.println("Lift Error: " + TKOHardware.getLiftTalon().getClosedLoopError(0) + " Lift Setpoint: " + TKOHardware.getLiftTalon().getClosedLoopTarget(0));
//							TKOHardware.getLiftTalon().set(ControlMode.Position, TKOHardware.getLiftTalon().getClosedLoopTarget(0) - incrementer);
//						}
//					}
//					pRange = range;
//				}
//			}
//			//TODO add code for outtake
//			*/
//
//}
