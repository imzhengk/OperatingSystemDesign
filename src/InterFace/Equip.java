package InterFace;

import java.util.LinkedList;
import java.util.Queue;

import EquipManage.*;
import ProcessManage.PCB;
import Windows.FileWindow;

public class Equip extends Thread {
	
	static volatile Queue<PCB> euqipqueue = new LinkedList<PCB>();
	FileWindow fw;
	
	public Equip(FileWindow fw) {
		this.fw = fw;
	}
	
	@Override
	public void run() {
		while(true) {
			while(euqipqueue.isEmpty()) {
				
			}
			while(!euqipqueue.isEmpty()) {
				UseEquip(euqipqueue.element());
				euqipqueue.remove();
			}
		}
	}
	
	public void UseEquip(PCB pcb) {
		String equip = pcb.equip;
		if(equip.equals("A")) {
			Thread thread = new Thread(new EquipA(pcb,fw));
			thread.start();
		}
		else if(equip.equals("B")) {
			Thread thread = new Thread(new EquipB(pcb,fw));
			thread.start();
		}
		else if(equip.equals("C")) {
			Thread thread = new Thread(new EquipC(pcb,fw));
			thread.start();
		}
	}
	
	public static void getEquip(PCB pcb) {
		euqipqueue.offer(pcb);
	}

}
