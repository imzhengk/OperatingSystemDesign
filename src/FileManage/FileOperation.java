package FileManage;

import FileManage.Tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileOperation {

	static char[][] AllocationTable = new char[2][128];  //盘块分配表
	static String[] DiskName = {"DiskC","DiskD"};
	
	//把分配表写入文件
	public void WriteToFile(String disk) throws Exception {
		int d = Tools.getd(disk);
		File f = new File(disk + ".txt");
		FileWriter out = new FileWriter(f);
		for(int i=0;i<128;i++) {
			out.write(AllocationTable[d][i]);
		}
		out.close();
	}
	
	//把文件写入分配表
	public void WriteToTable(String disk) throws Exception {
		int d = Tools.getd(disk);
		File f = new File(disk + ".txt");
		FileReader in = new FileReader(f);
		in.read(AllocationTable[d],0,128);
		in.close();
	}
	
	//初始化设置
	public FileOperation() throws Exception {
		for(String str : DiskName) {
			WriteToTable(str);
		}
	}
	
	//分配盘块
	public int allocate(String disk) throws Exception {
		int num = 0;
		int d = Tools.getd(disk);
		for(int i=3;i<128;i++) {
			if(AllocationTable[d][i]=='0') {
				AllocationTable[d][i]='1';
				num = i;
				break;
			}
		}
		WriteToFile(disk);
		File f = new File(disk + "/" + num + ".txt");
		f.createNewFile();
		return num;
	}
	
	//创建新目录
	public void makeDir(String disk,String dirname) throws Exception {
		String str = dirname + " ml";
		FileBlock fb = new FileBlock(str);
		int first = allocate(disk);
		fb.setBlock(first);
		String content = fb.toString();
		Tools.WriteContent(content,disk + "/" + "2.txt");
	}
	
	//删除目录
	public void deleteDir(String disk,String dirname) throws Exception {
		int dirblocknum = searchDir(disk,dirname);
		File dirfile = new File(disk + "/" + dirblocknum + ".txt");
		FileReader in = new FileReader(dirfile);
		BufferedReader br = new BufferedReader(in);
		String line = null;
		while((line = br.readLine()) != null) {
			String[] str = line.split(" ");
			deleteFile(disk,dirname,str[0]);
		}
		br.close();
		File f = new File(disk + "/" + "2.txt"); 
		Tools.modifyDir(f,dirname);
	}
	
	//找目录盘块号
	public int searchDir(String disk,String dirname) throws Exception {
		File f = new File(disk + "/" + "2.txt");
		return Tools.searchBlock(f,dirname);
	}
	
	//找文件盘块号
	public int searchFile(String disk,String dirname,String filename) throws Exception {
		int dirblocknum = searchDir(disk,dirname);
		File f = new File(disk + "/" + dirblocknum + ".txt");
		return Tools.searchBlock(f,filename);
	}
	
	//创建某个文件对象
	public File fileObject(String disk,String dirname,String filename) throws Exception {
		int blocknum = searchFile(disk,dirname,filename);
		File f = new File(disk + "/" + blocknum + ".txt"); 
		return f;
	}
	
	//创建新文件
	public void createFile(String disk,String dirname,String filename) throws Exception {
		int dirblocknum = searchDir(disk,dirname);
		String filesdirname = disk + "/" + dirblocknum + ".txt";  //文件块存放处
		boolean flag = Tools.judgeSameName(filesdirname,filename);
		if(flag) {
			System.out.println("文件重名，无法创建");
		}
		else {
			String str = filename + " txt";
			FileBlock fb = new FileBlock(str);
			int first = allocate(disk);
			fb.setBlock(first);
			fb.setFileLength(0);
			String content = fb.toString();
			Tools.WriteContent(content,filesdirname);
		}
	}
	
	//删除文件
	public void deleteFile(String disk,String dirname,String filename) throws Exception {
		int fileblocknum = searchFile(disk,dirname,filename);
		int dirblocknum = searchDir(disk,dirname);
		modifyTable(disk,fileblocknum);           //修改分配表
		File f = new File(disk + "/" + fileblocknum + ".txt"); 
		f.delete();                               //删除文件
		File dirfile = new File(disk + "/" + dirblocknum + ".txt");  //修改目录项内容
		Tools.modifyDir(dirfile,filename);
	}
	
	//删除文件--修改分配表
	public void modifyTable(String disk,int num) throws Exception {
		int d = Tools.getd(disk);
		AllocationTable[d][num] = '0';
		WriteToFile(disk);
	}
	
	//拷贝文件
	public void CopyFile(String disk,String dirname,String filename,String disk1,String dirname1,String filename1) throws Exception {
		String str = TypeFile(disk,dirname,filename);
		deleteFile(disk,dirname,filename);
		createFile(disk1,dirname1,filename1);
		EditFile(disk1,dirname1,filename1,str);
	}
	
	//显示文件内容
	public String TypeFile(String disk,String dirname,String filename) throws Exception {
		int fileblocknum = searchFile(disk,dirname,filename);
		File f = new File(disk + "/" + fileblocknum + ".txt"); 
		return TypeContent(f);
	}
		
	//显示文件内容-显示
	public String TypeContent(File f) throws Exception {
		StringBuffer sb = new StringBuffer();
		FileReader in = new FileReader(f);
		BufferedReader br = new BufferedReader(in);
		String line = null;
		while((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
		br.close();
		return sb.toString();
	}
	
	//修改文件内容
	public void EditFile(String disk,String dirname,String filename,String content) throws Exception {
		File f = fileObject(disk,dirname,filename); 
		FileWriter writer = new FileWriter(f);
        writer.write(content);   
        writer.close();
        
        int dirnum = searchDir(disk,dirname);
        File dirfile = new File(disk + "/" + dirnum + ".txt");
        Tools.modifyLength(dirfile,filename,content.length());
	}
	
	//磁盘格式化
	public void format(String disk) throws Exception {
		int d = Tools.getd(disk);
		for(int i=2;i<128;i++) {
			if(AllocationTable[d][i]=='1') {
				File f = new File(disk + "/" + i + ".txt");
				f.delete();
				AllocationTable[d][i]='0';
			}
		}
		AllocationTable[d][2]='1';
		WriteToFile(disk);	
	}
	
	//展示分配表
	public String showTable(String disk) {
		int d = Tools.getd(disk);
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<128;i++) {
			sb.append(AllocationTable[d][i] + " ");
			if((i+1)%16 == 0) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

}
