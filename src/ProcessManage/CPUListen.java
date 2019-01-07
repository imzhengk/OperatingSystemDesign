package ProcessManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;

import MemoryManage.MemoryOperation;
import Windows.FileWindow;

public class CPUListen extends Thread implements ActionListener {  
	FileWindow fw;
	
	public CPUListen(FileWindow fw) {
		this.fw = fw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.start();
	}

	public void run() {
		while(true) {
			while(Process.readyqueue.isEmpty()) {
				fw.queue.setText(Process.getReadyQueue());
				fw.processtable.setText("没有进程");
			}
			while(!Process.readyqueue.isEmpty()) {
				OrderOpreation op = new OrderOpreation(Process.readyqueue.peek(),fw);
				fw.queue.setText(Process.getReadyQueue());
				int x = op.ExeOrder();
				fw.content.setText("结果是：x = " + x);
				fw.memorytable.setText(MemoryOperation.show());
			}
		}
	}
	
	public static void create(String name) {
		try {
			Process.createPCB(name);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
}
