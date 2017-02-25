package org.usfirst.frc.team5112.robot;
import com.kylecorry.frc.vision.TargetGroupSpecs;

public class Boiler implements TargetGroupSpecs{

	@Override
	public Alignment getAlignment() {
			
		return Alignment.LEFT;
	}

	@Override
	public double getGroupHeight() {
		// TODO: Set return value.
		return inchesToMeters(10);
	}

	@Override
	public double getGroupWidth() {
		// TODO: Set return value.
		return inchesToMeters(15);
	}

	@Override
	public double getTargetHeightRatio() {
		// TODO: Set return value.
		return inchesToMeters(4/10.0);
	}

	@Override
	public double getTargetWidthRatio() {
		// TODO: Set return value.
		return 1.0;
	}
	
	private double inchesToMeters(double inches) {
		return inches * 0.0254;
	}

}