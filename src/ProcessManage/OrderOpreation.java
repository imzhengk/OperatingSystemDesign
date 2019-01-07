package ProcessManage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Queue;

import EquipManage.Equip;
import Windows.FileWindow;

public class OrderOpreation {
	FileWindow fw;
	PCB pcb;
	Queue<String> order = new LinkedList<String>();
	static int x = 0;
	int time = 2;
	
	public String getOrderContent() {
		StringBuffer sb = new StringBuffer();
		for(String str : this.order) {
			sb.append(str + "\n");
		}
		return sb.toString();
	}
	
	public OrderOpreation(PCB pcb,FileWindow fw) {
		this.pcb = pcb;
		this.order = pcb.order;
		fw.processtable.setText(getOrderContent());
	}
	
	public int ExeOrder() {
		boolean flag = false;
		while(!order.isEmpty()) {		
			String str = order.poll();
			if(str.startsWith("!")) {
				UseEquip(str);
				flag = true;
				break;
			}
			else if(str.startsWith("end")) {
				try {
					End();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			else if(str.startsWith("x=")) {
				Assign(str);
			}
			else if(str.startsWith("x++")) {
				SelfIn();
			}
			else if(str.startsWith("x--")) {
				SelfDe();
			}
			try {
				Thread.sleep(time*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(!flag) {
			Process.destroyPCB(pcb);
		}
		return x;
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
		char[] chs = str.toCharArray();
		int time = chs[2] - '0';
		pcb.time = 5;
		pcb.x = x;
		Process.waitPCB(pcb);
		String strs = pcb.name + " " + chs[1] + " " + time;
		Equip.getEquip(strs);
	}
	
	public void End() throws Exception {
		File f = new File("out.txt");
		BufferedWriter out = new BufferedWriter(new FileWriter(f,true));
		out.append(pcb.name + " x=" + x + "\n");
		out.close();
	}

}
