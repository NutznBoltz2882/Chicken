package org.usfirst.frc.team2882.robot.commands;

import org.usfirst.frc.team2882.robot.Robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Autonomous
 */
public class MoonEater extends Command {
	private final static double RUN_TIME = 1.0;
	private final Accelerometer accel = new BuiltInAccelerometer();
	
	private double distance = 0.0;
	private double time = 0.0;
	private static final double GOAL = 0.1;
	
	private double toffset;
    public MoonEater() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.movementSubsystem);
    	toffset = Timer.getFPGATimestamp();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	toffset = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double delta = Timer.getFPGATimestamp() - toffset;
    	if(delta > 0.1) {
    		distance += (accel.getY() * delta * delta);
    		toffset = Timer.getFPGATimestamp();
    	}
    	NetworkTable stuff = NetworkTable.getTable("Stuff");
    	stuff.putNumber("dist", distance);
    	stuff.putNumber("delta", delta);
    	stuff.putNumber("toff", toffset);
    	if(distance < GOAL) {
    		Robot.movementSubsystem.move(0.3, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return distance >= GOAL;
    }

    // Called once after isFinished returns true
    protected void end() {
    	super.end();
    	Robot.movementSubsystem.move(0.0,  0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	super.interrupted();
    	//this.end();
    }
}
