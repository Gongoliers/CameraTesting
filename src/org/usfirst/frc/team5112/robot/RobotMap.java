package org.usfirst.frc.team5112.robot;

import com.kylecorry.geometry.Point;
import com.kylecorry.geometry.Pose;
import com.kylecorry.geometry.Quaternion;
import com.kylecorry.geometry.Vector3;
import com.kylecorry.tf.TransformationMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static SpeedController drivetrainLeftRear;
	public static SpeedController drivetrainRightRear;
	public static SpeedController drivetrainLeftFront;
	public static SpeedController drivetrainRightFront;
	public static RobotDrive drivetrainDrivetrain;
	public static SpeedController intakeIntakeMotor;
	public static SpeedController hopperHopperMotor;
	public static SpeedController scalerScalerMotor;
	public static SpeedController shooterFlywheel;
	public static TransformationMap tf;
	public static Gyro gyro;
	
	public static double inchesToMeters(double inches) {
		return inches*0.0254;
		
	}
	public static void init() {
		drivetrainLeftRear = new VictorSP(3);
		LiveWindow.addActuator("Drivetrain", "LeftRear", (VictorSP) drivetrainLeftRear);

		drivetrainRightRear = new VictorSP(0);
		LiveWindow.addActuator("Drivetrain", "RightRear", (VictorSP) drivetrainRightRear);

		drivetrainLeftFront = new VictorSP(2);
		LiveWindow.addActuator("Drivetrain", "LeftFront", (VictorSP) drivetrainLeftFront);

		drivetrainRightFront = new VictorSP(1);
		LiveWindow.addActuator("Drivetrain", "RightFront", (VictorSP) drivetrainRightFront);

		drivetrainDrivetrain = new RobotDrive(drivetrainLeftFront, drivetrainLeftRear, drivetrainRightFront,
				drivetrainRightRear);

		drivetrainDrivetrain.setSafetyEnabled(true);
		drivetrainDrivetrain.setExpiration(0.1);
		drivetrainDrivetrain.setSensitivity(0.5);
		drivetrainDrivetrain.setMaxOutput(1.0);

		drivetrainDrivetrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		drivetrainDrivetrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		drivetrainDrivetrain.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		drivetrainDrivetrain.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		intakeIntakeMotor = new Spark(4);
		LiveWindow.addActuator("Intake", "IntakeMotor", (Spark) intakeIntakeMotor);

		hopperHopperMotor = new Spark(5);
		LiveWindow.addActuator("Hopper", "HopperMotor", (Spark) hopperHopperMotor);

		shooterFlywheel = new Victor(6);
		LiveWindow.addActuator("Shooter", "Flywheel", (Victor) shooterFlywheel);

		scalerScalerMotor = new Victor(7);
		LiveWindow.addActuator("Scaler", "ScalerMotor", (Victor) scalerScalerMotor);
		
		gyro = new AnalogGyro(0);
		gyro.calibrate();
		
		tf = new TransformationMap();
		tf.put("PegCamera", new Pose(new Point(0, inchesToMeters(16), 0), Quaternion.zero));
		tf.put("BoilerCamera", "PegCamera", new Pose(new Point(0, inchesToMeters(-1.5), inchesToMeters(3)), new Quaternion(Math.PI/4, Vector3.i)));
		tf.put("Intake", new Pose(new Point(0, inchesToMeters(16), 0), Quaternion.zero));
		tf.put("GearHolster", new Pose(new Point(inchesToMeters(0.5), inchesToMeters(14.75), inchesToMeters(14)), Quaternion.zero));
		tf.put("Shooter", new Pose(new Point(inchesToMeters(-6.25), inchesToMeters(-9.75), inchesToMeters(11.25)), Quaternion.zero));
		
	}
}
