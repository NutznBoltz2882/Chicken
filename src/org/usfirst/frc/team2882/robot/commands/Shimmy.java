package org.usfirst.frc.team2882.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Shimmy to victory
 * 
 * GOAL: Shake the robot at the rate specified by pulse,
 * for time seconds.
 * 
 * Use voltage stalling to detect events.
 * http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/PowerDistributionPanel.html
 */
public class Shimmy extends Command {
	private float pulse;
	private float time;
	
	private float accumulator;
	
    public Shimmy(float _p, float _t) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
