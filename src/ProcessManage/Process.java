package ProcessManage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

import FileManage.Tools;
import MemoryManage.MemoryOperation;

public class Process {

	public static volatile Queue<PCB> readyqueue = new LinkedList<PCB>(); 
	public static volatile LinkedList<PCB> waitqueue = new LinkedList<PCB>(); 
	public static volatile Queue<String> orderqueue = new LinkedList<String>(); 
	
	public static String getReadyQueue() {
		StringBuffer sb = new StringBuffer();
		sb.append("就绪队列内容：\n");
		if(!readyqueue.isEmpty()) {
			for(PCB pcb : readyqueue) {
				sb.append(pcb.toString()+ "\n");
			}
		}
		return sb.toString();
	}
	
	public static String getWaitQueue() {
		StringBuffer sb = new StringBuffer();
		sb.append("阻塞队列内容：\n");
		if(!waitqueue.isEmpty()) {
			for(PCB pcb : waitqueue) {
				sb.append(pcb.toString()+ "\n");
			}
		}
		return sb.toString();
	}
	
	public static String showOutfile() throws Exception {
		StringBuffer sb = new StringBuffer();
		File f = new File("out.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = null;
		while((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		br.close();
		return sb.toString();
	}
	
	public static void createPCB(String name) throws Exception {
		
		String pcbname = Tools.getName(name);
		PCB pcb = new PCB(pcbname);
		int page = MemoryOperation.getPage(name); //调用主存
		pcb.getPage(page);
		String orders = MemoryOperation.getOrders(pcbname); //调用主存
		pcb.getOrders(orders);
		readyqueue.offer(pcb);
	}
	
	public static void destroyPCB(PCB pcb) {
		MemoryOperation.destroyPage(pcb.name);  //调用主存
		readyqueue.remove();
	}
	
	public static void waitPCB(PCB pcb) {
		readyqueue.remove();
		waitqueue.offer(pcb);
	}
	
	public static void againPCB(PCB pcb) {
		waitqueue.remove(pcb);
		readyqueue.offer(pcb);
	}
	
}
