package ProcessManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
				fw.queue.setText(Process.getReadyQueue() + "\n" + Process.getWaitQueue());
				fw.processtable.setText("没有进程");
			}
			while(!Process.readyqueue.isEmpty()) {
				fw.content.setText("进程正在运行，请稍后。。。每条指令5秒");
				OrderOpreation op = new OrderOpreation(Process.readyqueue.peek(),fw);
				fw.queue.setText(Process.getReadyQueue() + "\n" + Process.getWaitQueue());
				int x = op.ExeOrder();
				fw.content.setText("进程结束，结果是：x = " + x);
				fw.memorytable.setText(MemoryOperation.show());
				try {
					fw.outfile.setText(Process.showOutfile());
				} catch (Exception e) {
					e.printStackTrace();
				}
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
	
	public static void closeAll() {
		try {
			MemoryOperation.closeAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
}
