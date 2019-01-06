package EquipManage;
import java.util.LinkedList;

public class EquipC extends Thread {
	
    private final static int BUFFER_SIZE = 1;   
    private static LinkedList<Object> buffer = new LinkedList<Object>();	
	String name;
	int time;
	
	public EquipC(String name,int time) {
		this.name = name;
		this.time = time;
	}

	@Override
	public void run() {
		while(true) {
			synchronized (buffer){
            	while(buffer.size() >= BUFFER_SIZE){
            		try {
                		System.out.printf(name + "等待中\n");
						buffer.wait(5000);   
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
            	}
                buffer.add(new Object());
                System.out.println(name + "正在使用");
                buffer.notify();
            }
            try {
                Thread.sleep(time*1000);
        		System.out.printf(name + "使用完成\n");
        		buffer.removeFirst();
        		break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}