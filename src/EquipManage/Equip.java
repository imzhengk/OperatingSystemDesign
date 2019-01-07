package EquipManage;

import java.util.LinkedList;
import java.util.Queue;

import Windows.FileWindow;

public class Equip extends Thread {
	
	static volatile Queue<String> euqipqueue = new LinkedList<String>();
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
				String[] strs = euqipqueue.element().split(" ");
				int time = Integer.parseInt(strs[2]);
				UseEquip(strs[0],strs[1],time);
				euqipqueue.remove();
			}
		}
	}
	
	public void UseEquip(String name,String equip,int time) {
		if(equip.equals("A")) {
			Thread thread = new Thread(new EquipA(name,time,fw));
			thread.start();
		}
//		else if(equip.equals("B")) {
//			Thread thread = new Thread(new EquipB(name,time,fw));
//			thread.start();
//		}
//		else if(equip.equals("C")) {
//			Thread thread = new Thread(new EquipC(name,time,fw));
//			thread.start();
//		}
	}
	
	public static void getEquip(String str) {
		euqipqueue.offer(str);
	}

}
