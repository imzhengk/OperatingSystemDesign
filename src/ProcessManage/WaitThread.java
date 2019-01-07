package ProcessManage;

import Windows.FileWindow;

public class WaitThread extends Thread {  
	FileWindow fw;
	
	public WaitThread(FileWindow fw) {
		this.fw = fw;
	}

	public void run() {
		while(true) {
			while(Process.waitqueue.isEmpty()) {
				
			}
			while(!Process.waitqueue.isEmpty()) {
				fw.queue.setText(Process.getReadyQueue() + "\n" + Process.getWaitQueue());
				PCB pcb = Process.waitqueue.element();
				int time = pcb.time;
				try {
					Thread.sleep(time*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Process.waitqueue.remove();
				Process.readyqueue.offer(pcb);
				fw.queue.setText(Process.getReadyQueue() + "\n" + Process.getWaitQueue());
//				Process.againPCB(Process.waitqueue.element());
			}
		}
	}
	
}