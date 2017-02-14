package org.usfirst.frc.team2882.robot.stuff;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

class PDPPair {
	String name;
	int channel;
	PDPPair(int chan, String nm) {
		name = nm; channel = chan;
	}
}

public class PDPNetworkExpose {
	NetworkTable table;
	List<PDPPair> regs;
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public PDPNetworkExpose() {
		table = NetworkTable.getTable("pdp");
		regs = new ArrayList<PDPPair>();
	}
	
	public void expose(int channel, String as) {
		regs.add(new PDPPair(channel, as));
	}
	
	public void process() {
		for (PDPPair i : regs) {
			if(!table.putNumber(i.name, pdp.getCurrent(i.channel))) {
				System.out.println("MAYDAY MAYDAY CHANNEL NOT REPORTING "+i.name+" "+i.channel);
			} else {
//				if(pdp.getCurrent(i.channel) > 0)
//				System.out.println("XRVB " + i.name + " " + i.channel + " " + pdp.getCurrent(i.channel));
			}
		}
	}
}
