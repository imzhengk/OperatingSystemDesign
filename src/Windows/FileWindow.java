package Windows;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import FileManage.*;
import ProcessManage.CPUListen;

public class FileWindow extends JFrame {
	public JTextField text;
	public JTextField content;
	public JTextArea table;
	public JTextArea strut;
	public JTextArea memorytable;
	
	public FileWindow() throws Exception {
		init();
		setVisible(true);
		setBounds(100,100,800,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	void init() throws Exception {
		setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		text = new JTextField(25);
		content = new JTextField(40);
		table = new JTextArea(15,25);  //磁盘分配表
		strut = new JTextArea(15,25);  //目录文件结构名
		memorytable = new JTextArea(15,25);  //主存分配表
		JButton open = new JButton("开机");
		JButton button = new JButton("确认");
		
		CPUListen cpulisten = new CPUListen(this);
		open.addActionListener(cpulisten);
		
		FileListen filelisten = new FileListen(this);
		button.addActionListener(filelisten);
		
		panel.add(text);
		panel.add(button);
		panel2.add(content);
		add(open);
		add(panel);
		add(panel2);
		add(new JScrollPane(table));
		add(new JScrollPane(strut));
		add(new JScrollPane(memorytable));
	}
	
}
