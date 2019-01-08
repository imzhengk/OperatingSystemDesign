package InterFace;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import MemoryManage.*;
import ProcessManage.*;
import ProcessManage.Process;
import Windows.FileWindow;

public class CPUListen extends Thread implements ActionListener {  
	FileWindow fw;
	Equip eq;
	OrderThread ot;
	
	public CPUListen(FileWindow fw) {
		this.fw = fw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		fw.setTitle("Mogic OS       开机时间：" + dateFormat.format(new Date()));
		eq = new Equip(fw);
		ot = new OrderThread(fw);
		this.start();
		eq.start();
		ot.start();;
	}

	public void run() {
		while(true) {
			while(Process.readyqueue.isEmpty()) {
				fw.queue.setText(Process.getReadyQueue() + "\n" + Process.getWaitQueue());
				fw.processtable.setText("没有进程");
			}
			while(!Process.readyqueue.isEmpty()) {
				fw.content.setText("进程正在运行，请稍后。。。每条指令" + OrderOpreation.time + "秒");
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
			File f = new File("out.txt");
			FileWriter fw = new FileWriter(f);
			fw.write("");
			fw.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
}
