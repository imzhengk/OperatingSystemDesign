package EquipManage;
import java.util.LinkedList;

import Windows.FileWindow;

public class EquipA extends Thread {
	
    private final static int BUFFER_SIZE = 3;   
    private volatile static LinkedList<Object> buffer = new LinkedList<Object>();
	String name;
	int time;
	FileWindow fw;
	
	public EquipA(String name,int time,FileWindow fw) {
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
                	fw.equa1.setText(name + "使用A1" + time);
                }
                else if (n==2) {
                	fw.equa2.setText(name + "使用A2" + time);
                }
                else if (n==3) {
                	fw.equa3.setText(name + "使用A3" + time);
                }
                buffer.notify();
            }
            try {
                Thread.sleep(time*1000);
                int n = buffer.size();
                if(n==1) {
                	fw.equa1.setText(name + "使用A1完成");
                }
                else if (n==2) {
                	fw.equa2.setText(name + "使用A2完成");
                }
                else if (n==3) {
                	fw.equa3.setText(name + "使用A3完成");
                }
        		buffer.removeFirst();
        		break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}