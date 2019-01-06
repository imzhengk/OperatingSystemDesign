package FileManage;

import java.util.Scanner;

public class test2 {

	public static void main(String[] args) throws Exception {
		FileOperation fo = new FileOperation();
		System.out.println("输入格式：命令 磁盘号(DiskC/DiskD) [目录名(z)] [文件名(z)]");
		//makdir DiskC User z
		//create DiskC User zhengk
		//edit DiskC User zhengk
		//show DiskC z z
		Scanner sc = new Scanner(System.in);
		String[] str = sc.nextLine().split(" ");
		String command = str[0];
		String disk = str[1];
		String dirname = str[2];
		String filename = str[3];
		if(command.equals("copy")) {
			str = sc.nextLine().split(" ");
			String disk1 = str[0];
			String dirname1 = str[1];
			String filename1 = str[2];
			fo.CopyFile(disk,dirname,filename,disk1,dirname1,filename1);
		}
		else if (command.equals("edit")) {
			String content = sc.nextLine();
			fo.EditFile(disk,dirname,filename,content);
		}
		else {
			switch(command) {
			case "makdir":
				fo.makeDir(disk,dirname);break;
			case "deldir":
				fo.deleteDir(disk,dirname);break;
			case "create":
				fo.createFile(disk,dirname,filename);break;
			case "delete":
				fo.deleteFile(disk,dirname,filename);break;
			case "type":
				fo.TypeFile(disk,dirname,filename);break;
			case "format":
				fo.format(disk);break;
			case "show":
				System.out.println(fo.showTable(disk));break;
			default:
				System.out.println("您输入的命令有误");break;
			}
		}
		sc.close();
	}

}
