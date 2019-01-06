package FileManage;

public class FileBlock {
	String filename;
	String extension;
//	char fileordir;
//	char onlyread;
//	char showornot;
	int startblock;
	int filelength;
	
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
		return filename + " " + extension + " " + startblock + " " + filelength + " ";
	}
}
