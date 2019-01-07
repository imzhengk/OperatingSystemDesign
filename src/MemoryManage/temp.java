package MemoryManage;

/*number=total/16;
block=total%16;
if(flagg[number]==1)
    flag=1;
else 
    flag=0;
position=total;
Page page=new Page(number,block,flag,poisition);
if(string.equals("++")
Instruction process= new Instruction("++",number,block);
if(string.equals("--") 
Instruction process= new Instruction("--",number,block);
if(string.equals("=")
Instruction process= new Instruction("=",number,block);
*/
/*int i=total/16;
if(i>31)
	System.out.println("内存空间512kb，无法装入大于512kb的文件");
else
{
    for(int j=occupy;j<=occupy+i;j++ )
    {
	    if(occupy+i>32)
		    occupy=0;
        flag[j]=1;			
    }
    occupy++;	
    System.out.println("如果想要查看主存空间分配表，请按1");
    Scanner s=new Scanner(System.in);
    int t=s.nextInt();
    if(t==1)
        for(int e=0;e<32;e++)
       {
	        System.out.printf(flag[e]+"    ");
	        if((e+1)%8==0)
		        System.out.println("");
        }
} 
}*/

/* public class Instruction {
String operation;
int number,unit;
Instruction(String operation,int number,int unit){
this.operation = operation;
this.number = number;
this.unit = unit;
}
}
public class Page {
int number,flag,block,position,newflag;
Page(int number,int flag,int block,int positon) {
this.number = number;
this.flag = flag;
this.block = block;
this.position = positon;
this.newflag = 0;
}
public String getAddress(int unit) {
if(this.flag == 1) {
int address = this.block*16 + unit;
this.newflag = 1;
return String.valueOf(address);
}
else {
return "*";
}
}
public void setStop(Page past) {
System.out.println("----处理页面中断-----");
if(past.newflag == 1)
System.out.println("调出页号：" + past.number);
System.out.println("装入页号" + this.number + "\n----处理完毕----");
this.flag = 1;
this.block = past.block;
past.block = -1;
past.flag = 0;
past.newflag = 0;
}
}
public class Test1 {
//页号，主存标志，主存块号，磁盘上位置
Page[] FIFO = new Page[4];
int i = 0,k = 0;
for(Page pa:page) {
if(pa.flag == 1) {
	FIFO[i] = pa;
	i++;
}
}
for(Instruction pro:process) {
for(Page pa:page) {
	String str = "";
	if(pa.number == pro.number) {
		str = pa.getAddress(pro.unit);
		System.out.println("指令执行，绝对地址" + str);
	}		
	if(str.equals("*")) {
		Page temp = FIFO[k];
		pa.setStop(temp);
		FIFO[k] = pa;
		k = (k+1) % 4;
		System.out.println("指令执行，绝对地址" + pa.getAddress(pro.unit));
	}
}
}
for(Page F:FIFO) {
System.out.print(F.number+" ");
}
}

import java.io.*;

public class SystemArea {
static String content[]=new String[32];
static int flag[]=new int[32];
String p,q,z;//p是传过来的进程名，q是要撤销的进程名，z是内容，
static int occupy=0;
/*int number,block,flag,position;*/
/*public void showLable(){
System.out.println("0表示为分配，1表示已经分配");
}*/

/*public int getPage(String p)
{
this.p=p;
if(occupy>31) 
occupy=0;
while(flag[occupy]!=0)
occupy++;
if(flag[occupy]==0)
{
content[occupy]=p;
flag[occupy]=1;
occupy++;
}

return occupy;
}
public void destroyPage(String q)
{
this.q=q;
for(int i=0;i<32;i++)
{
String t=content[i];
if(t.equals(q))
	flag[i]=0;
break;
}
}
public void  writeMemory(String z)
{
try 
{
this.z=z;
File f=new File("Memory/" + occupy+ ".txt");
FileWriter out=new FileWriter(f);
BufferedWriter outt=new BufferedWriter(out);
outt.write(z);
outt.close();
out.close();
}
catch(IOException e) {}
}
public String getOrders(String p)
{
String s=null;
for(int i=0;i<32;i++)
{
String h=content[i];
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
}
}
return s;
}
}*/