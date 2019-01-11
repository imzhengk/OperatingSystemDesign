package FileManage;

public class FileBlock {
	String filename;  //文件名
	String extension; //扩展名
	int startblock; //起始块号
	int filelength; //文件长度
	//文件属性
//	String fd = null; //目录or文件
	String wr = "write"; //可写or只读
//	String show = "show"; //显示or隐藏
	
	public FileBlock(String filename) {
		String[] str = filename.split(" ");
		this.filename = str[0];
		this.extension = str[1];
	}
	
	public void setBlock(int startblock) {
		this.startblock = startblock; 
	}
	
	public void setFileLength(int filelength) {
		this.filelength = filelength; 
	}
	
	@Override
	public String toString() {
		return filename + " " + extension + " " + startblock + " " + filelength + " " + wr + " ";
	}
}
