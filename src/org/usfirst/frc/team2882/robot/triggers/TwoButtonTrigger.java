package org.usfirst.frc.team2882.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class TwoButtonTrigger extends Trigger {
	private Button permission;
	private Button trigger;
	public TwoButtonTrigger(Button _p, Button _t) {
		permission = _p; trigger = _t;
	}
    public boolean get() {
        return permission.get() && trigger.get();
    }
}
