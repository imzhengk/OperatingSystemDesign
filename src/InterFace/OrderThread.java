package InterFace;

import Windows.FileWindow;
import ProcessManage.Process;

public class OrderThread extends Thread {
	int time = 2;
	FileWindow fw;
	
	public OrderThread(FileWindow fw) {
		this.fw = fw;
	}

	public void run() {
		while(true) {
			while(Process.orderqueue.isEmpty()) {
				
			}
			while(!Process.orderqueue.isEmpty()) {
				String[] str = Process.orderqueue.element().split(" ");				
				fw.noworder.setText(str[0]);
				fw.nowx.setText(str[1]);
				Process.orderqueue.remove();
			}
		}
	}
}

