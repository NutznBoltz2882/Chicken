package org.usfirst.frc.team2882.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

import org.usfirst.frc.team2882.robot.commands.StopIt;
import org.usfirst.frc.team2882.robot.subsystems.util.MotorType;

import edu.wpi.first.wpilibj.Spark;

/**
 *
 */
public class Monkey extends Subsystem {
	private SpeedController[] motors = new SpeedController[NUMBER_OF_MOTORS];
	private static int NUMBER_OF_MOTORS = 2;

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

	public Ugokumono(int offset, MotorType[] motorTypes) {
		if(motorTypes.length != NUMBER_OF_MOTORS)
			throw new RuntimeException("MotorTypes length != required");
		for(int i = offset; i < offset + NUMBER_OF_MOTORS; i++) {
			motors[i-offset] = synthesizeMotor(motorTypes[i-offset], i);
		}
	}

	public void climb(double speed) {
		double m1 = 0, m2 = 0;
		// Wheels are pos. opposite like
		// top
		// v ^
		// v ^
		// btm
		m1 = speed; m2 = speed;

		double[] motor_values = new double[] { m1, m2 };
		for(int i = 0; i < NUMBER_OF_MOTORS; i++) {
			motors[i].set(motor_values[i]);
		}
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	// setDefaultCommand(new StopIt());
    }
}
