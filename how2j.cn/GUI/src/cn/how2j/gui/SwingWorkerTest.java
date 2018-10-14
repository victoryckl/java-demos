package cn.how2j.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class SwingWorkerTest {
	public static void main(String[] args) {
		JFrame f = new JFrame("LoL");
		f.setSize(300, 300);
		f.setLocation(200, 200);
		f.setLayout(new FlowLayout());
		
		JButton b1 = new JButton("在事件调度线程中执行长耗时任务");
		JButton b2 = new JButton("使用SwingWorker执行长耗时任务");
		final JLabel l = new JLabel("任务执行结果");
		f.add(b1);
		f.add(b2);
		f.add(l);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				l.setText("开始执行");
				try {
					Thread.sleep(3000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				l.setText("执行完毕");
			}
		});
		
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						System.out.println("执行这个SwingWorder的线程是：" + Thread.currentThread().getName());
                        l.setText("开始执行");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        l.setText("执行完毕");
						return null;
					}
				};
				worker.execute();
			}
		});
		f.setVisible(true);
	}
}
