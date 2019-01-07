package ProcessManage;

import java.util.LinkedList;
import java.util.Queue;

import FileManage.Tools;
import MemoryManage.MemoryOperation;

public class Process {

	static Queue<PCB> readyqueue = new LinkedList<PCB>(); 
	static Queue<PCB> waitqueue = new LinkedList<PCB>(); 
	
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
		readyqueue.remove(pcb);
	}
	
	public static void waitPCB(PCB pcb) {
		MemoryOperation.destroyPage(pcb.name);  //调用主存
		readyqueue.remove(pcb);
		waitqueue.offer(pcb);
	}
	
	public static void againPCB(PCB pcb) {
		waitqueue.remove(pcb);
		readyqueue.offer(pcb);
	}
	
}
