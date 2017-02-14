package org.usfirst.frc.team2882.robot.commands;

import org.usfirst.frc.team2882.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class Dance extends TimedCommand {
	private final static double DANCE_TIME = 2.0;
	private final static double SPPED = 0.5;
    public Dance() {
    	super(DANCE_TIME);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.movementSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(this.timeSinceInitialized());
    	double tp = this.timeSinceInitialized() - (DANCE_TIME - SPPED);
    	Robot.movementSubsystem.move(0, SPPED - Math.max(0, tp));
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
