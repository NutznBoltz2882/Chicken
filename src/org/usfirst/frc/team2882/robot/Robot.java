
package org.usfirst.frc.team2882.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team2882.robot.commands.ExampleCommand;
import org.usfirst.frc.team2882.robot.stuff.NetworkExpose;
import org.usfirst.frc.team2882.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team2882.robot.subsystems.Ugokumono;
import org.usfirst.frc.team2882.robot.subsystems.Monkey;
import org.usfirst.frc.team2882.robot.subsystems.util.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static Ugokumono movementSubsystem = new Ugokumono(0, new MotorType[]{
			MotorType.VICTOR_SP, MotorType.VICTOR_SP, MotorType.VICTOR_SP, MotorType.VICTOR_SP});
	public static Monkey pulleySubsystem = new Monkey(4, new MotorType[]{MotorType.SPARK, MotorType.SPARK});
	public static OI oi;
	public static boolean canDance = false;
	static NetworkExpose expose;

    Command autonomousCommand;

	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI(0);
        // instantiate the command used for the autonomous period
        autonomousCommand = new ExampleCommand();      
        expose = new NetworkExpose();
//        Scheduler.getInstance().add(new MoveYourBody());
        expose.expose(0, "0");
        expose.expose(1, "1");
        expose.expose(14, "3");
        expose.expose(15, "2");
        expose.expose(2, "4");
        expose.expose(3, "5");
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	oi.poll(Scheduler.getInstance());
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

    @Override
	public void robotPeriodic() {
		super.robotPeriodic();
		expose.process();
	}
}
