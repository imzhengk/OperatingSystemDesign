package EquipManage;

public class Test {

	public static void main(String[] args) {
		Thread rb1 = new Thread(new EquipC("a1",2));
		Thread rb2 = new Thread(new EquipC("a2",3));
		Thread rb3 = new Thread(new EquipA("a3",3));
		rb1.start();
		rb2.start();
		rb3.start();
//		rb4.start();
//		rb5.start();
	}

}
