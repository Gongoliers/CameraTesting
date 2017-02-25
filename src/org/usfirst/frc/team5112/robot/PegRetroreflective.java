package org.usfirst.frc.team5112.robot;
import org.opencv.core.Range;

import com.kylecorry.frc.vision.TargetSpecs;

public class PegRetroreflective implements TargetSpecs{

	public static double inchesToMeters(double inches) {
		return inches*0.0254;
		
	}
	
	@Override
	public Range getHue() {
		return new Range(75, 125);
	}

	@Override
	public Range getSaturation() {
		return new Range(175, 255);
	}

	@Override
	public Range getValue() {
		return new Range(65, 255);
	}

	@Override
	public double getWidth() {
		return inchesToMeters(2 / 12.0);
	}

	@Override
	public double getHeight() {
		return inchesToMeters(5 / 12.0);
	}

	@Override
	public double getArea() {
		return getWidth() * getHeight();
	}

	@Override
	public int getMinPixelArea() {
		return 125;
	}

}