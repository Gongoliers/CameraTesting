package org.usfirst.frc.team5112.robot.subsystems;

import java.util.List;

import org.opencv.core.Mat;
import org.usfirst.frc.team5112.robot.Boiler;
import org.usfirst.frc.team5112.robot.BoilerRetroSpecs;
import org.usfirst.frc.team5112.robot.Peg;
import org.usfirst.frc.team5112.robot.PegRetroreflective;
import org.usfirst.frc.team5112.robot.RobotMap;


import com.kylecorry.frc.vision.CameraSource;
import com.kylecorry.frc.vision.TargetGroup;
import com.kylecorry.frc.vision.TargetGroupDetector;
import com.kylecorry.geometry.Point;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoCamera;
import edu.wpi.first.wpilibj.command.Subsystem;


public class TargetingSystem extends Subsystem {

	private TargetGroupDetector boilerDetector, pegDetector;
	private Mat frame;
	public VideoCamera cam0, cam1;
	private MjpegServer server;
	private CvSink cvSink;

	// TODO: Put methods for controlling this subsystem
	// here. Call these from Commands.
	public TargetingSystem() {
		boilerDetector = new TargetGroupDetector(new BoilerRetroSpecs(), new Boiler());
		pegDetector = new TargetGroupDetector(new PegRetroreflective(), new Peg());
		cam0 = new UsbCamera("PegCamera", 0);
		cam1 = new UsbCamera("BoilerCamera", 1);
		cam0.setResolution(480, 360);
		cam1.setResolution(480, 360);

		server = new MjpegServer("serve_USB Camera 0", 1181);
		
		cvSink = new CvSink("opencv_USB Camera 0");

		switchToPegCamera();
		
		frame = new Mat();
		
		enableTargetMode(cam0);
		enableTargetMode(cam1);
	}
	
	public void switchToBoilerCamera(){
		cvSink.setSource(cam1);
		server.setSource(cam1);
	}
	
	
	public void switchToPegCamera(){
		cvSink.setSource(cam0);
		server.setSource(cam0);
	}
	
	public Mat getImage(){
		cvSink.grabFrame(frame);
		return frame;
	}
	
	public Point getBoilerPosition(int imageWidth, double cameraViewAngle, double targetActualWidth) {
		TargetGroup point = getBoilerTarget();
		if(point == null){
			return null;
		}
		double angle = Math.toRadians(90 - point.computeAngle(imageWidth, cameraViewAngle));
		double distance = point.computeDistance(imageWidth, targetActualWidth, cameraViewAngle);
		Point targetPoint = Point.fromCylindrical(distance, angle, 0);
		Point targetFromShooter = RobotMap.tf.transform(targetPoint, "BoilerCamera", "Shooter");
		return targetFromShooter;
	}
	
	public Point getPegPosition(int imageWidth, double cameraViewAngle, double targetActualWidth) {
		TargetGroup point = getPegTarget();
		if(point == null){
			return null;
		}
		double angle = Math.toRadians(90 - point.computeAngle(imageWidth, cameraViewAngle));
		double distance = point.computeDistance(imageWidth, targetActualWidth, cameraViewAngle);
		Point targetPoint = Point.fromCylindrical(distance, angle, 0);
		Point targetFromShooter = RobotMap.tf.transform(targetPoint, "PegCamera", "Shooter");
		return targetFromShooter;
	}

	public static double getDistance(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}

	public static double getAngle(double x, double y) {
		return Math.toDegrees(Math.atan2(y, x));
	}

	public void initDefaultCommand() {
		//setDefaultCommand(new TargetingModeOff());
	}

	public TargetGroup getBoilerTarget() {
		List<TargetGroup> targetGroups = boilerDetector.detect(getImage());
		if (!targetGroups.isEmpty()) {
			return targetGroups.get(0);
		}
		return null;
	}

	public TargetGroup getPegTarget() {
		List<TargetGroup> targetGroups = pegDetector.detect(getImage());
		if (!targetGroups.isEmpty()) {
			return targetGroups.get(0);
		}
		return null;
	}

	public void enableTargetMode(VideoCamera camera) {
		camera.setBrightness(0);
		camera.setExposureManual(0);
	}

	public void disableTargetMode(VideoCamera camera) {
		camera.setBrightness(50);
		camera.setExposureManual(20);
	}
}
