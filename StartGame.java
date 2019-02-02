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
	 * 게임 시작 장면
	 */
	
	ImageIcon foricon9_1 = new ImageIcon("D:\\그림\\시작버튼.png");
	Image foricon9_2 = 
			foricon9_1.getImage().getScaledInstance(300,100 , Image.SCALE_SMOOTH);
	ImageIcon icon9 = new ImageIcon(foricon9_2);
	//시작 버튼 이미지 생성.
	
	Image icon8=new ImageIcon("D:\\그림\\시작장면.jpg").getImage();	//시작 배경화면 생성.
	
	private static final long serialVersionUID = 1L;
	
	MyPanel panel;	//시작 장면의 패널
	JButton button;	//시작버튼
	
	
	public StartGame() {
		/*
		 * 처음 시작 장면에 대한 초기 설정.
		 */
		setSize(500, 800);
		setTitle("선생님 놀리기 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button = new JButton("시작");
		button.setBounds(100 , 600 , 300 , 100);
		button.setIcon(icon9);
		button.setBorderPainted(false);	//버튼 경계선 없애기
		button.setContentAreaFilled(false);	//버튼 안에 그림 이외에 남는 공간 없애기
		button.addActionListener(this);
		
		panel = new MyPanel();
		panel.setLayout(null);
		panel.add(button);
		
		add(panel);
		
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button) {
			//시작 버튼 눌렀을 때
			new TeaseTeacher(); // TeaseTeacher 생성자 호출
			this.dispose();		//시작 장면을 꺼준다.
		}
	}
	
	 class MyPanel extends JPanel{
		 //패널에 이미지 넣기
		private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){   	
	                super.paintComponent(g);
	                g.drawImage(icon8,0,0,getWidth(),getHeight(),this);
	        }
	    }
}