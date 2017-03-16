package org.usfirst.frc.team2882.robot.stuff;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.ADXL345_SPI;
import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

class PDPPair {
	String name;
	int channel;
	PDPPair(int chan, String nm) {
		name = nm; channel = chan;
	}
}

public class NetworkExpose {
	NetworkTable table;
	List<PDPPair> regs;
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	Accelerometer accel = new ADXL345_SPI(SPI.Port.kOnboardCS1, Range.k8G);
	
	public NetworkExpose() {
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
		table.putNumber("nrg", pdp.getTotalEnergy());
		table.putNumber("temperature", pdp.getTemperature());
//		accel.updateTable();
		NetworkTable accelTable = NetworkTable.getTable("accel");
		accelTable.putNumber("x", accel.getX());
		accelTable.putNumber("y", accel.getY());
		accelTable.putNumber("z", accel.getZ());
	}
}
