package ProcessManage;

import java.util.LinkedList;
import java.util.Queue;

public class ProcessOpreation {

	static Queue<PCB> readyqueue = new LinkedList<PCB>(); 
	static Queue<PCB> waitqueue = new LinkedList<PCB>(); 
    
	public ProcessOpreation() {
		
	}
	
	public void createPCB(String name) {
		PCB pcb = new PCB(name);
//		int page = memory.getPage(name); 调用主存
		pcb.getPage(0);
//		String orders = memory.getOrders(name);
		pcb.getOrders("x=5 x++");
		readyqueue.offer(pcb);
	}
	
	public void destroyPCB(PCB pcb) {
//		memory.destroyPage(name); 调用主存
		readyqueue.remove(pcb);
	}
	
	public void waitPCB(PCB pcb) {
//		memory.destroyPage(name); 调用主存
		waitqueue.offer(pcb);
	}
	
}
