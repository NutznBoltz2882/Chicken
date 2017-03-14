package org.usfirst.frc.team2882.robot;

import org.usfirst.frc.team2882.robot.commands.Dance;
import org.usfirst.frc.team2882.robot.commands.ManualMonkey;
import org.usfirst.frc.team2882.robot.commands.MoveYourBody;
import org.usfirst.frc.team2882.robot.commands.Shimmy;
import org.usfirst.frc.team2882.robot.triggers.TwoButtonTrigger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
	public final Joystick stickOfJoy;
	
	public OI(int port0) {
		stickOfJoy = new Joystick(port0);
		
		Button button = new JoystickButton(stickOfJoy, 6);
		button.whenPressed(new Shimmy(0.15, 1.0));
		Button button2 = new JoystickButton(stickOfJoy, 3);
		Button button3 = new JoystickButton(stickOfJoy, 7);
		TwoButtonTrigger danceTrigger = new TwoButtonTrigger(button2, button3);
		danceTrigger.whenActive(new Dance());
	}
	
	// Updates the internal state, and creates commands as necessary
	public void poll(Scheduler scheduler) {
		double _y = -stickOfJoy.getY() * 1 ;
		double _angle = -stickOfJoy.getX() * 0.7;
		
		if(_y > 0.1 || _y < -0.1 || _angle > 0.1 || _angle < -0.1) {
			scheduler.add(new MoveYourBody(_y, _angle));
		}
		
		double _z = stickOfJoy.getZ();
//		System.out.println(_z);
//		System.out.println("P " + stickOfJoy.getZ());
		if(_z > 0.1 || _z < -0.1) {
			// Use negative numbers to go FORWARD! YAHOOO!
			scheduler.add(new ManualMonkey(_z));
		}
	}
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

