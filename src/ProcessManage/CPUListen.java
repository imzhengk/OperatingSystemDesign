package ProcessManage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

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
			while(!Process.readyqueue.isEmpty()) {
				OrderOpreation op = new OrderOpreation(Process.readyqueue.peek());
				op.ExeOrder();
			}
		}
	}
	
}
