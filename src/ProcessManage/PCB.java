package ProcessManage;

import java.util.LinkedList;
import java.util.Queue;

public class PCB {
	String name;//进程名
	int page;//进程所在页
	PCB next = null; //下一条进程地址
	
	public int time = 0; //阻塞时间
	public String equip = null; //设备名
	
	int x = 0; //x的内容
	
	Queue<String> order = new LinkedList<String>(); //指令内容
	
	public PCB(String name) {
		this.name = name;
	}
	
	public void getPage(int page) {
		this.page = page;
	}
	
	public void getOrders(String orders) {
		String[] strs = orders.split(" ");
		for(String str : strs) {
			order.offer(str);
		}
	}
	
	@Override
	public String toString() {
		return "进程名：" + name + " 页号：" + page;
	}
		
}

