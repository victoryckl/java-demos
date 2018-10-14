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
		
		JButton b1 = new JButton("���¼������߳���ִ�г���ʱ����");
		JButton b2 = new JButton("ʹ��SwingWorkerִ�г���ʱ����");
		final JLabel l = new JLabel("����ִ�н��");
		f.add(b1);
		f.add(b2);
		f.add(l);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				l.setText("��ʼִ��");
				try {
					Thread.sleep(3000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				l.setText("ִ�����");
			}
		});
		
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						System.out.println("ִ�����SwingWorder���߳��ǣ�" + Thread.currentThread().getName());
                        l.setText("��ʼִ��");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        l.setText("ִ�����");
						return null;
					}
				};
				worker.execute();
			}
		});
		f.setVisible(true);
	}
}
