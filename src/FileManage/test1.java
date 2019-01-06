package FileManage;

import java.io.File;
import java.io.FileWriter;

public class test1 {

	public static void main(String[] args) throws Exception {
		File f = new File("DiskD.txt");
		FileWriter out = new FileWriter(f);
		int n = 0;
		for(int i=0;i<128;i++) {
			out.write('0');
		}
		out.close();

	}

}
