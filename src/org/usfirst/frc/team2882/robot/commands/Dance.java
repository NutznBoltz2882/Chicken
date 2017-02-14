package org.usfirst.frc.team2882.robot.commands;

import org.usfirst.frc.team2882.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Dance extends Command {
	Timer nTime = new Timer();
	boolean xyzzy = false;
    public Dance() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.movementSubsystem);
    	nTime.reset();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	nTime.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(nTime.hasPeriodPassed(2.0)) xyzzy = true;
    	if(Robot.canDance) Robot.movementSubsystem.move(0, 0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return xyzzy;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
