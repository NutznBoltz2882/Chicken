package org.usfirst.frc.team2882.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

import org.usfirst.frc.team2882.robot.commands.StopIt;
import org.usfirst.frc.team2882.robot.subsystems.util.MotorType;

import edu.wpi.first.wpilibj.Spark;

/**
 *
 */
public class Ugokumono extends Subsystem {
	private SpeedController[] motors = new SpeedController[NUMBER_OF_MOTORS];
	private static int NUMBER_OF_MOTORS = 4;
	
	private static SpeedController synthesizeMotor(MotorType type, int port) {
		switch (type) {
		case SPARK:
			return new Spark(port);
		case VICTOR:
			return new Victor(port);
		default:
			throw new RuntimeException("IMPOSSIBLE!");
		}
	}
	
	public Ugokumono(int offset, MotorType[] motorTypes) {
		for(int i = offset; i < offset + NUMBER_OF_MOTORS; i++) {
			motors[i-offset] = synthesizeMotor(motorTypes[i-offset], i);
		}
	}
	
	public void move(double y, double angle) {
		double tl = 0, tr = 0, bl = 0, br = 0;
		// Wheels are pos. opposite like
		// frt
		// v ^
		// v ^
		// bck
		tl += angle; tr +=angle; bl += angle; br += angle;
		tl -= y; tr += y; bl -= y; br += y;
		
		double[] motor_values = new double[] { tl, bl, tr, br };
		for(int i = 0; i < NUMBER_OF_MOTORS; i++) {
			motors[i].set(motor_values[i]);
		}
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StopIt());
    }
}

