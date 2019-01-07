package MemoryManage;

import java.io.*;

import FileManage.*;

public class MemoryOperation {
	
	static String content[]=new String[32];
    static int flag[]=new int[32];
	static String p,q;//p是传过来的进程名，q是要撤销的进程名，
	static int occupy=0;
	/*int number,block,flag,position;*/
	
	public static String show(){
		StringBuffer sb = new StringBuffer();
		sb.append("主存内容：\n");
		 for(int e=0;e<32;e++){
			 sb.append((flag[e]+"    "));
		     if((e+1)%8==0) {
		    	 sb.append("\n");
		     }
		 }
		 return sb.toString();
	}
    
	public static int getPage(String name) throws Exception
	{
		String[] str = name.split(" ");
		String disk = str[0];
		String dirname = str[1];
		String filename = str[2];
		
		String s = FileOperation.TypeFile(disk,dirname,filename);
		p = filename;
		if(occupy>31) 
			occupy=0;
		while(flag[occupy]!=0)
			occupy++;
		if(flag[occupy]==0)
		{
			content[occupy]=p;
		    flag[occupy]=1;
		    try {
				 File f=new File("Memory/" + occupy+ ".txt");
				    FileWriter out=new FileWriter(f);
				    BufferedWriter outt=new BufferedWriter(out);
				    outt.write(s);
				    outt.close();
				    out.close();
				}
				catch (IOException e) {}
		}
		
		return occupy;
	}
	public static void destroyPage(String name)
	{
		q = name;
		for(int i=0;i<32;i++)
		{
			String t=content[i];
			if(t.equals(q))
				{
				flag[i]=0;
				break;
				}
			
		}
	}
	/*public void  writeMemory(String z)
	{
		try 
		{
			
		    this.z=z;
		   
		}
		catch(IOException e) {}
	}*/
	public static String getOrders(String name) throws Exception
	{
		p = name;
		String s = null;
		for(int i=0;i<32;i++)
		{
		    String h = content[i];
		    if(h.equals(p))
		    {
		    	try {
		    	File f=new File("Memory/" + i+ ".txt");
		    	FileReader in=new FileReader(f);
		    	BufferedReader inn=new BufferedReader(in);
		    	s=inn.readLine();
		    	in.close();
		    	inn.close();
		    	}
		    	catch(IOException e) {}
		    	break;
		    }
	    }
		return s;
    }
	public void closeAll()
	{
		File file = new File("Memory");
        File[] files = file.listFiles();
        for(int i=0; i<files.length; i++){
            File tmp = files[i];
            if(tmp.toString().endsWith(".txt")){
                tmp.delete();
            }
        }

	}
}

