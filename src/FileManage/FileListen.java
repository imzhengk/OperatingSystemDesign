package FileManage;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Windows.FileWindow;
import MemoryManage.MemoryOperation;
import ProcessManage.CPUListen;

public class FileListen implements ActionListener {
	FileWindow fw;
	FileOperation fo;
	MemoryOperation mo;
	
	public FileListen(FileWindow fw) throws Exception {
		this.fw = fw;
		fo = new FileOperation();
		mo = new MemoryOperation();
		fw.content.setText("格式：命令 磁盘(DiskC/D) [目录(z)] [文件(z)] exe DiskC User Zhengk");
		fw.table.setText("C盘内容:\n" + fo.showTable("DiskC") + "\n" + "D盘内容:\n" + fo.showTable("DiskD"));
		fw.strut.setText("C盘:\n" + fo.showStruct("DiskC") + "\n" + "D盘:\n" + fo.showStruct("DiskD"));
		fw.memorytable.setText(MemoryOperation.show());
		//makdir DiskC User z
		//create DiskC User zhengk
		//edit DiskC User zhengk
		//show DiskC z z
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String strs = fw.text.getText();
		String[] str = strs.split(" ");
		String command = str[0];
		String disk = str[1];
		String dirname = str[2];
		String filename = str[3];
		if(command.equals("help")) {
			fw.content.setText("exe makdir deldir create delete type edit copy format");
		}
		else if(command.equals("exe")) {
			String name = str[1] + " " + str[2] + " " + str[3];
			try {
				CPUListen.create(name);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			fw.memorytable.setText(MemoryOperation.show());			
		}
		else {
			switch(command) {
			case "makdir":
				try {
					if(fo.makeDir(disk,dirname)) {
						fw.content.setText("创建文件完成");
					}
					else {
						fw.content.setText("文件重名，无法创建");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "deldir":
				try {
					fo.deleteDir(disk,dirname);
					fw.content.setText("删除目录完成");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "create":
				try {
					if(fo.createFile(disk,dirname,filename)) {
						fw.content.setText("创建文件完成");
					}
					else {
						fw.content.setText("文件重名，无法创建");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "delete":
				try {
					fo.deleteFile(disk,dirname,filename);
					fw.content.setText("删除文件完成");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "type":
				try {
					String typestr = FileOperation.TypeFile(disk,dirname,filename);
					fw.content.setText(typestr);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "edit":
				String content = fw.content.getText();
				try {
					fo.EditFile(disk,dirname,filename,content);
					fw.content.setText("修改文件完成");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "copy":
				String[] s = fw.content.getText().split(" ");
				try {
					fo.CopyFile(disk,dirname,filename,s[0],s[1],s[2]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "format":
				try {
					fo.format(disk);
					fw.content.setText("格式化完成");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			default:
				fw.content.setText("您输入的命令有误");
				break;
			}
		}
		fw.table.setText("C盘内容:\n" + fo.showTable("DiskC") + "D盘内容:\n" + fo.showTable("DiskD"));
		try {
			fw.strut.setText("C盘:\n" + fo.showStruct("DiskC") + "\n" + "D盘:\n" + fo.showStruct("DiskD"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
