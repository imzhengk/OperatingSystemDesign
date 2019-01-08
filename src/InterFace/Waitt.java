package InterFace;

import ProcessManage.PCB;
import ProcessManage.Process;
import Windows.FileWindow;

public class Waitt extends Thread {  
	FileWindow fw;
	PCB pcb;
	
	public Waitt(PCB pcb,FileWindow fw) {
		this.pcb = pcb;
		this.fw = fw;
	}

	public void run() {
		try {
			Thread.sleep(pcb.time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Process.againPCB(pcb);
		fw.queue.setText(Process.getReadyQueue() + "\n" + Process.getWaitQueue());
	}
	
}