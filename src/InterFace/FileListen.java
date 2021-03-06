package InterFace;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import FileManage.FileOperation;
import Windows.FileWindow;
import MemoryManage.MemoryOperation;
import ProcessManage.Process;

public class FileListen implements ActionListener {
	FileWindow fw;
	FileOperation fo;
	MemoryOperation mo;
	
	public FileListen(FileWindow fw) throws Exception {
		this.fw = fw;
		fo = new FileOperation();
		mo = new MemoryOperation();
		fw.content.setText("格式：命令 磁盘(DiskC/D) [目录(z)] [文件(z)]");
		fw.table.setText("C盘分配表:\n" + fo.showTable("DiskC") + "\n" + "D盘分配表:\n" + fo.showTable("DiskD"));
		fw.strut.setText("C盘:\n" + fo.showStruct("DiskC") + "\n" + "D盘:\n" + fo.showStruct("DiskD"));
		fw.memorytable.setText(MemoryOperation.show());
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
			fw.document.setText(toHelp());
		}
		else if(command.equals("exe")) {
			String name = str[1] + " " + str[2] + " " + str[3];
			try {
				CPUListen.create(name);
				fw.queue.setText(Process.getReadyQueue() + "\n" + Process.getWaitQueue());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			fw.memorytable.setText(MemoryOperation.show());			
		}
		else if(command.equals("close")) {
			CPUListen.closeAll();
			fw.content.setText("关机成功");
			fw.memorytable.setText(MemoryOperation.show());			
		}
		else {
			switch(command) {
			case "makdir":
				try {
					if(fo.makeDir(disk,dirname)) {
						fw.content.setText("创建目录完成");
					}
					else {
						fw.content.setText("目录重名，无法创建");
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
					fw.document.setText(typestr);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "edit":
				String content = fw.document.getText();
				try {
					if(fo.EditFile(disk,dirname,filename,content)) {
						fw.content.setText("修改文件完成");
					}
					else {
						fw.content.setText("文件为只读，无法修改");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				break;
			case "copy":
				String[] ss = fw.content.getText().split(" ");
				try {
					fo.CopyFile(disk,dirname,filename,ss[0],ss[1],ss[2]);
					fw.content.setText("拷贝文件完成");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "move":
				String[] s = fw.content.getText().split(" ");
				try {
					fo.MoveFile(disk,dirname,filename,s[0],s[1],s[2]);
					fw.content.setText("移动文件完成");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				break;
			case "change":
				String contenta = fw.content.getText();
				try {
					fo.Change(disk,dirname,filename,contenta);
					fw.content.setText("改变属性完成");
				} catch (Exception e2) {
					e2.printStackTrace();
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
		fw.table.setText("C盘分配表:\n" + fo.showTable("DiskC") + "\n" + "D盘分配表:\n" + fo.showTable("DiskD"));
		try {
			fw.strut.setText("C盘:\n" + fo.showStruct("DiskC") + "\n" + "D盘:\n" + fo.showStruct("DiskD"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public String toHelp() {
		return "exe 执行"
				+ "\nmakdir 创建目录"
				+ "\ndeldir 删除目录"
				+ "\ncreate 创建文件"
				+ "\ndelete 删除文件"
				+ "\ntype 显示文件"
				+ "\nedit 编辑文件"
				+ "\ncopy 拷贝文件"
				+ "\nmove 移动文件"
				+ "\nchange 改变文件属性"
				+ "\nformat 磁盘格式化"
				+ "\nclose 关机"
				+ "\n ";
	}
}
