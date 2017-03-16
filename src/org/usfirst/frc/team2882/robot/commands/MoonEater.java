package org.usfirst.frc.team2882.robot.commands;

import org.usfirst.frc.team2882.robot.Robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Autonomous
 */
public class MoonEater extends Command {
	private final static double RUN_TIME = 1.0;
	private final Accelerometer accel = new BuiltInAccelerometer();
	private final Gyro gyra = new ADXRS450_Gyro();
	
	private double distance = 0.0;
	private double time = 0.0;
	private static final double GOAL = 0.1;
	
	private PIDController pidC;
	
	private double toffset;
    public MoonEater() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.movementSubsystem);
    	toffset = Timer.getFPGATimestamp();
    	
    	pidC = new PIDController(0.05, 0.0, 0.0, (PIDSource) gyra, Robot.movementSubsystem);
    	pidC.initTable(NetworkTable.getTable("PIDANG"));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	toffset = Timer.getFPGATimestamp();
    	pidC.setAbsoluteTolerance(0.1);
    	pidC.setInputRange(0.0, 360.0);
    	pidC.setContinuous(true);
    	pidC.setOutputRange(-0.25, 0.25);
    	pidC.setSetpoint(90.0);
    	pidC.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	double delta = Timer.getFPGATimestamp() - toffset;
//    	if(delta > 0.1) {
//    		distance += (accel.getX() * delta * delta);
//    		toffset = Timer.getFPGATimestamp();
//    	}
//    	NetworkTable stuff = NetworkTable.getTable("Stuff");
//    	stuff.putNumber("dist", distance);
//    	stuff.putNumber("delta", delta);
//    	stuff.putNumber("toff", toffset);
//    	if(distance < GOAL) {
//    		Robot.movementSubsystem.move(0.3, 0);
//    	}
//    	if(gyra.getAngle() < 90.0) {
//    		Robot.movementSubsystem.move(0, -0.25);
//    	} else if (gyra.getAngle() < 90.0 || gyra.getAngle() > 91.0) {
//    		Robot.movementSubsystem.move(0, 0.25);
//    	}
    	pidC.updateTable();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pidC.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	super.end();
    	pidC.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	super.interrupted();
//    	this.end();
    	pidC.disable();
    }
}
