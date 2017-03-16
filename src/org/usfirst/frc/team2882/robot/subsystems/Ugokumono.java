package org.usfirst.frc.team2882.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

import org.usfirst.frc.team2882.robot.commands.StopIt;
import org.usfirst.frc.team2882.robot.subsystems.util.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Spark;

/**
 *
 */
public class Ugokumono extends Subsystem implements PIDOutput {
	private SpeedController[] motors = new SpeedController[NUMBER_OF_MOTORS];
	private static int NUMBER_OF_MOTORS = 4;
	private static double BACK_LIMIT = 1;
	// LEft negative gyro Right positive gyro
	private Gyro gyro = new ADXRS450_Gyro();
	
	private PIDController[] pidcs = new PIDController[NUMBER_OF_MOTORS/2]; // Why half? BC we have 1 encoder.
	// We'll have to have the robot system adjust the other side based on the gyrometer.
	
	private static boolean do_backLimit = true;
	
	private static SpeedController synthesizeMotor(MotorType type, int port) {
		switch (type) {
		case SPARK:
			return new Spark(port);
		case VICTOR:
			return new Victor(port);
		case VICTOR_SP:
			return new VictorSP(port);
		default:
			throw new RuntimeException("IMPOSSIBLE!");
		}
	}
	
	public void setBackLimit(boolean b) {
		do_backLimit = b;
	}
	
	public Ugokumono(int offset, MotorType[] motorTypes) {
		gyro.calibrate();
		if(motorTypes.length != NUMBER_OF_MOTORS)
			throw new RuntimeException("MotorTypes length != required");
		for(int i = offset; i < offset + NUMBER_OF_MOTORS; i++) {
			motors[i-offset] = synthesizeMotor(motorTypes[i-offset], i);
		}
		
		// TODO Setup pidcs with output values
	}
	
	public void move(double y, double angle) {
		NetworkTable.getTable("GYRO").putNumber("ang", gyro.getAngle());
		// TODO Make this set a goal angle and direction (absolute)
		// rather than just approximating it.
		double tl = 0, tr = 0, bl = 0, br = 0;
		if (y < 0 && do_backLimit)
			y *= BACK_LIMIT;
		// Wheels are pos. opposite like
		// top
		// ^ v
		// ^ v
		// btm
		tl -= angle; tr -=angle; bl -= angle; br -= angle;
		tl -= y; tr += y; bl -= y; br += y;
		
		// TODO Use these motor values for the PIDSubsystem, rather than directly
		double[] motor_values = new double[] { tl, bl, tr, br };
		for(int i = 0; i < NUMBER_OF_MOTORS; i++) {
			motors[i].set(motor_values[i]);
		}
		// TODO Use the gyroscope to fix these values
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StopIt());
    }

	@Override
	public void pidWrite(double output) {
		// Use pid to get to goal ANGLE, sooo
		this.move(0, output);
	}
}

