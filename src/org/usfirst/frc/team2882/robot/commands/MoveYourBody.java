package org.usfirst.frc.team2882.robot.commands;

import org.usfirst.frc.team2882.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class MoveYourBody extends InstantCommand {
	private double move_y;
	private double move_x;
	
    public MoveYourBody(double m, double m2) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.movementSubsystem);
    	move_y = m;
    	move_x = m2;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.movementSubsystem.move(move_y, move_x);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
