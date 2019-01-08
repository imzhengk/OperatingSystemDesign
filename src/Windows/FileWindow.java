package Windows;


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import InterFace.CPUListen;
import InterFace.FileListen;

public class FileWindow extends JFrame {
	public JTextField text;
	public JTextField content;
	public JTextArea table;
	public JTextArea strut;
	public JTextArea document;
	public JTextArea memorytable;
	public JTextArea processtable;
	public JTextArea queue;
	public JTextArea outfile;
	
	public JTextField noworder;
	public JTextField nowx;
	
	public JTextField equa1;
	public JTextField equa2;
	public JTextField equa3;
	public JTextField equb1;
	public JTextField equb2;
	public JTextField equc1;
	
	public FileWindow() throws Exception {
		init();
		setVisible(true);
		setBounds(200,50,800,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	void init() throws Exception {
		setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		text = new JTextField(25);
		content = new JTextField(30);
		table = new JTextArea(15,25);  //磁盘分配表
		strut = new JTextArea(15,25);  //目录文件结构名
		memorytable = new JTextArea(10,20);  //主存分配表
		document = new JTextArea(15,20);  //文件内容
		document.setText("显示文件内容");
		processtable = new JTextArea(10,15);  //进程内容
		queue = new JTextArea(10,20);  //就绪队列内容
		outfile = new JTextArea(10,15);  //out文件内容
		outfile.setText("显示out文件");
		
		noworder = new JTextField(5);
		nowx = new JTextField(2);
		
		equa1 = new JTextField("空闲",5);
		equa2 = new JTextField("空闲",5);
		equa3 = new JTextField("空闲",5);
		equb1 = new JTextField("空闲",5);
		equb2 = new JTextField("空闲",5);
		equc1 = new JTextField("空闲",5);
		
		JButton open = new JButton("开机");
		JButton button = new JButton("确认");
		
		CPUListen cpulisten = new CPUListen(this);
		open.addActionListener(cpulisten);
		
		FileListen filelisten = new FileListen(this);
		button.addActionListener(filelisten);
		
		panel.add(open);
		panel.add(new JLabel("   当前指令："));
		panel.add(noworder);
		panel.add(new JLabel("x的值："));
		panel.add(nowx);
		add(panel);
		add(panel3);
		panel2.add(new JLabel("命令："));
		panel2.add(text);
		panel2.add(button);
		panel2.add(new JLabel("       "));
		panel2.add(content);
		

		add(panel2);
		add(new JScrollPane(table));
		add(new JScrollPane(strut));
		add(new JScrollPane(document));
		add(new JScrollPane(memorytable));
		add(new JScrollPane(processtable));
		add(new JScrollPane(queue));
		add(new JScrollPane(outfile));

		panel3.add(new JLabel("       "));
		panel3.add(new JLabel("A1"));
		panel3.add(equa1);
		panel3.add(new JLabel("A2"));
		panel3.add(equa2);
		panel3.add(new JLabel("A3"));
		panel3.add(equa3);
		panel3.add(new JLabel("B1"));
		panel3.add(equb1);
		panel3.add(new JLabel("B1"));
		panel3.add(equb2);
		panel3.add(new JLabel("C1"));
		panel3.add(equc1);
		
	}
	
}
