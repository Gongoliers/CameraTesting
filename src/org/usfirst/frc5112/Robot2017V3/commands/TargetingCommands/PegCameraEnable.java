package org.usfirst.frc5112.Robot2017V3.commands.TargetingCommands;


import org.usfirst.frc.team5112.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PegCameraEnable extends Command {

	public PegCameraEnable() {
		requires(Robot.targetingSystem);
	}

	protected void initialize() {
		Robot.targetingSystem.switchToPegCamera();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		end();
	}
}