package cn.how2j.gui;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestGUI {
	public static void main(String[] args) {
		JFrame f = new JFrame("LoL");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.setLayout(null);
		JButton b = new JButton("一键秒对方基地挂");
		b.setBounds(50, 50, 280, 30);
		f.add(b);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗体的时候，退出程序
		f.setVisible(true);
	}
}
