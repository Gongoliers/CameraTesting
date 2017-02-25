package org.usfirst.frc.team5112.robot;

import org.opencv.core.Range;

import com.kylecorry.frc.vision.TargetSpecs;

public class BoilerRetroSpecs implements TargetSpecs{

	@Override
	public Range getHue() {
		return new Range(55, 145);
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
		return inchesToMeters(15);
	}

	@Override
	public double getHeight() {
		return inchesToMeters(10);
	}

	private double inchesToMeters(double inches) {
		return inches * 0.0254;
	}

	@Override
	public double getArea() {
		return getWidth()*getHeight();
	}

	@Override
	public int getMinPixelArea() {
		// TODO Auto-generated method stub
		return 0;
	}

}