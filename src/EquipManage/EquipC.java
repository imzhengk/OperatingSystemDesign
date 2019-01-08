package EquipManage;
import java.util.LinkedList;

import Windows.FileWindow;

public class EquipC extends Thread {
	
    private final static int BUFFER_SIZE = 1;   
    private volatile static LinkedList<Object> buffer = new LinkedList<Object>();
	String name;
	int time;
	FileWindow fw;
	
	public EquipC(String name,int time,FileWindow fw) {
		this.name = name;
		this.time = time;
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
                	fw.equc1.setText("使用中");
                }
                buffer.notify();
            }
            try {
                Thread.sleep(time*1000);
                int n = buffer.size();
                if(n==1) {
                	fw.equc1.setText("空闲");
                }
        		buffer.removeFirst();
        		break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}