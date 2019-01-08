package EquipManage;
import java.util.LinkedList;

import ProcessManage.PCB;
import ProcessManage.Process;
import Windows.FileWindow;

public class EquipB extends Thread {
	
    private final static int BUFFER_SIZE = 2;   
    private volatile static LinkedList<Object> buffer = new LinkedList<Object>();
    PCB pcb;
	FileWindow fw;
	
	public EquipB(PCB pcb,FileWindow fw) {
		this.pcb = pcb;
		this.fw = fw;
	}

	@Override
	public void run() {
		while(true) {
			synchronized (buffer){
            	while(buffer.size() >= BUFFER_SIZE){
            		try {
						buffer.wait(2000);   
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
            	}
                buffer.add(new Object());
                int n = buffer.size();
                if(n==1) {
                	fw.equb1.setText("使用中");
                }
                else if (n==2) {
                	fw.equb2.setText("使用中");
                }
                buffer.notify();
            }
            try {
                Thread.sleep(pcb.time*1000);
                Process.againPCB(pcb);
                int n = buffer.size();
                if(n==1) {
                	fw.equb1.setText("空闲");
                }
                else if (n==2) {
                	fw.equb2.setText("空闲");
                }
        		buffer.removeFirst();
        		break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
