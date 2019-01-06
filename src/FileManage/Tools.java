package FileManage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Tools {
	
	//把内容写入某个文件
	public static void WriteContent(String content,String filename) throws Exception {
		File f = new File(filename);
		FileWriter writer = new FileWriter(f, true);
        writer.write(content + "\n"); 
        writer.close();
	}
	
	//获取分配表盘符值
	public static int getd(String disk) {
		int d = 0;
		if(disk.equals("DiskC")) d = 0;
		if(disk.equals("DiskD")) d = 1;
		return d;
	}
	
	//找盘块号
	public static int searchBlock(File f,String name) throws Exception {
		FileReader in = new FileReader(f);
		BufferedReader br = new BufferedReader(in);
		int blocknum = 0;
		String line = null;
		while((line = br.readLine()) != null) {
			String[] str = line.split(" ");
			if(str[0].equals(name)) {
				blocknum = Integer.parseInt(str[2]);
			}
		}
		in.close();
		br.close();	
		return blocknum;
	}
	
	//文件同名判断
	public static boolean judgeSameName(String dirname,String filename) throws Exception {
		FileReader in = new FileReader(dirname);
		BufferedReader br = new BufferedReader(in);
		boolean flag = false;
		String line = null;
		while((line = br.readLine()) != null) {
			String[] str = line.split(" ");
			if(str[0].equals(filename)) {
				flag = true;
				break;
			}
		}
		in.close();
		br.close();	
		return flag;
	}
	
	//1.2修改目录文件内容
	public static void modifyDir(File f,String filename) throws Exception {
		StringBuffer sb = new StringBuffer();
		FileReader in = new FileReader(f);
		BufferedReader br = new BufferedReader(in);
		String line = null;
		while((line = br.readLine()) != null) {
			String[] str = line.split(" ");
			if(!str[0].equals(filename)) {
				sb.append(line + "\n");
			}
		}
		br.close();
		FileWriter writer = new FileWriter(f);
        writer.write(sb.toString());   
        writer.close();
	}
	
	//1.2修改文件长度
	public static void modifyLength(File f,String filename,int filelength) throws Exception {
		StringBuffer sb = new StringBuffer();
		FileReader in = new FileReader(f);
		BufferedReader br = new BufferedReader(in);
		String line = null;
		while((line = br.readLine()) != null) {
			String[] str = line.split(" ");
			if(!str[0].equals(filename)) {
				sb.append(line + "\n");
			}
			else {
				str[3] = String.valueOf(filelength);
				sb.append(str[0] + " " + str[1] + " " + str[2] + " " + str[3] + " " + "\n");
			}
		}
		br.close();
		FileWriter writer = new FileWriter(f);
        writer.write(sb.toString());   
        writer.close();
	}	
	
}
