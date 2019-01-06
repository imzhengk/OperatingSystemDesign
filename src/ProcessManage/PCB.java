package ProcessManage;

import java.util.LinkedList;
import java.util.Queue;

public class PCB {
	String name;//进程名
	int page;//进程所在页
	PCB next = null; //下一条指令地址
	Queue<String> order = new LinkedList<String>(); //指令执行
	
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
	
	
}

