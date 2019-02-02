package MyOwnProject;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartGame extends JFrame implements ActionListener{
	
	/*
	 * ���� ���� ���
	 */
	
	ImageIcon foricon9_1 = new ImageIcon("D:\\�׸�\\���۹�ư.png");
	Image foricon9_2 = 
			foricon9_1.getImage().getScaledInstance(300,100 , Image.SCALE_SMOOTH);
	ImageIcon icon9 = new ImageIcon(foricon9_2);
	//���� ��ư �̹��� ����.
	
	Image icon8=new ImageIcon("D:\\�׸�\\�������.jpg").getImage();	//���� ���ȭ�� ����.
	
	private static final long serialVersionUID = 1L;
	
	MyPanel panel;	//���� ����� �г�
	JButton button;	//���۹�ư
	
	
	public StartGame() {
		/*
		 * ó�� ���� ��鿡 ���� �ʱ� ����.
		 */
		setSize(500, 800);
		setTitle("������ ��� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button = new JButton("����");
		button.setBounds(100 , 600 , 300 , 100);
		button.setIcon(icon9);
		button.setBorderPainted(false);	//��ư ��輱 ���ֱ�
		button.setContentAreaFilled(false);	//��ư �ȿ� �׸� �̿ܿ� ���� ���� ���ֱ�
		button.addActionListener(this);
		
		panel = new MyPanel();
		panel.setLayout(null);
		panel.add(button);
		
		add(panel);
		
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button) {
			//���� ��ư ������ ��
			new TeaseTeacher(); // TeaseTeacher ������ ȣ��
			this.dispose();		//���� ����� ���ش�.
		}
	}
	
	 class MyPanel extends JPanel{
		 //�гο� �̹��� �ֱ�
		private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){   	
	                super.paintComponent(g);
	                g.drawImage(icon8,0,0,getWidth(),getHeight(),this);
	        }
	    }
}