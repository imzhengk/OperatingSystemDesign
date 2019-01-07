package ProcessManage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Queue;

public class OrderOpreation {
	PCB pcb;
	public static Queue<String> order = new LinkedList<String>();
	int x = 0;
	
	public OrderOpreation(PCB pcb) {
		this.pcb = pcb;
		this.order = pcb.order;
	}
	
	public void ExeOrder() {
		boolean flag = false;
		while(!order.isEmpty()) {
			String str = order.poll();
			if(str.startsWith("A") || str.startsWith("B") || str.startsWith("C")) {
				UseEquip(str);
				flag = true;
				break;
			}
			if(str.startsWith("end")) {
				try {
					End();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			if(str.startsWith("x=")) {
				Assign(str);
			}
			if(str.startsWith("x++")) {
				SelfIn();
			}
			if(str.startsWith("x--")) {
				SelfDe();
			}
			System.out.println(pcb.name + " " + x);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(!flag) {
			Process.destroyPCB(pcb);
		}
	}
	
	public int Assign(String str) {
		x = Integer.parseInt(str.substring(2, str.length()));
		return x;
	}
	
	public int SelfIn() {
		return x++;
	}
	
	public int SelfDe() {
		return x--;
	}
	
	public void UseEquip(String str) {
		Process.waitPCB(pcb);
//		equip.UseEquip(str);  使用设备
	}
	
	public void End() throws Exception {
		File f = new File("out.txt");
		BufferedWriter out = new BufferedWriter(new FileWriter(f,true));
		out.append(pcb.name + " x=" + x + "\n");
		out.close();
	}

}
