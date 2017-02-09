package org.usfirst.frc.team2882.robot.commands;

import org.usfirst.frc.team2882.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Shimmy to victory
 * 
 * GOAL: Shake the robot at the rate specified by pulse,
 * for time seconds.
 * 
 * TODO Use voltage stalling to detect when to stop.
 * http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/PowerDistributionPanel.html
 */
public class Shimmy extends TimedCommand {
	private double pulse;

	private double sign = 1.0;
	private Timer timer = new Timer();
	
	private final double SPEED = 0.5;
	
	
    public Shimmy(double _p, double _t) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super(_t);
    	requires(Robot.movementSubsystem);
    	pulse = _p;
    	timer.reset();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.hasPeriodPassed(pulse)) {
    		sign *= -1;
    	}
    	// Shimmy
    	Robot.movementSubsystem.move(SPEED * 0.5, sign * SPEED);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
