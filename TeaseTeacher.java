package MyOwnProject;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/*
 * setialVersionUID�� ��ü�� ���Ͽ� ���ų� ������ �� ����ȭ �ϱ� ���� ����ȭ�� �����
 * ���� ���̵� �������ִ� ��. �������� �ʾƵ� JVM���� �ڵ����� ������������ ���� �����ϴ°�
 * ����.
 */

public class TeaseTeacher extends JFrame{

	private static final long serialVersionUID = 1L;
	Up up;	//Up ����Ŭ���� ����.
	Down down;	//Down ����Ŭ���� ����.
	
	JLabel label1, label2, scorelabel;	//label1 ���� �̹��� ����. label2 ���� �̹��� ����. scorelabel ���� ǥ��.
	JButton restartbutton, close;	//ScoreDialog�� �ٽ��ϱ�, �ݱ� ��ư.
	JPanel panel, scorepanel;	//panel ����â�� �г�, scorepanel ScoreDialog�� �г�.
	
	Random 	r;	//���� �̹��� ��ȯ�ϴ� �ð� �������� �ϱ� ���� Random Ŭ���� ����.
	Clip clip , clip2;	//������ �ֱ� ���� Clip Ŭ���� ������. clip�� ���� ������ ���� ����, clip2�� ���� �� ���� ����.
	Timer timer1;	//���� ǥ�ø� ���� Timer Ŭ���� ����.
	
	static int score;	//����
	static int i = 3;		//������ ������� ���ư��� ���� �ʿ��� ����.

	
	ImageIcon foricon1_1 = new ImageIcon("src/TeasePicture/Default1.png");
	ImageIcon foricon2_1 = new ImageIcon("src/TeasePicture/Default2.png");
	ImageIcon foricon3_1 = new ImageIcon("src/TeasePicture/Tease.png");
	ImageIcon foricon4_1 = new ImageIcon("src/TeasePicture/Warning.png");
	ImageIcon foricon5_1 = new ImageIcon("src/TeasePicture/LookBack.png");
	ImageIcon foricon6_1 = new ImageIcon("src/TeasePicture/getCaught.png");
	ImageIcon foricon7_1 = new ImageIcon("src/TeasePicture/getCaught2.png");
	
	Image foricon1_2 = foricon1_1.getImage().getScaledInstance(500,400 , Image.SCALE_SMOOTH);
	Image foricon2_2 = foricon2_1.getImage().getScaledInstance(500,400 , Image.SCALE_SMOOTH);
	Image foricon3_2 = foricon3_1.getImage().getScaledInstance(500,400 , Image.SCALE_SMOOTH);
	Image foricon4_2 = foricon4_1.getImage().getScaledInstance(500,400 , Image.SCALE_SMOOTH);
	Image foricon5_2 = foricon5_1.getImage().getScaledInstance(500,400 , Image.SCALE_SMOOTH);
	Image foricon6_2 = foricon6_1.getImage().getScaledInstance(500,400 , Image.SCALE_SMOOTH);
	Image foricon7_2 = foricon7_1.getImage().getScaledInstance(500,400 , Image.SCALE_SMOOTH);
	
	ImageIcon icon1 = new ImageIcon(foricon1_2);
	ImageIcon icon2 = new ImageIcon(foricon2_2);
	ImageIcon icon3 = new ImageIcon(foricon3_2);
	ImageIcon icon4 = new ImageIcon(foricon4_2);
	ImageIcon icon5 = new ImageIcon(foricon5_2);
	ImageIcon icon6 = new ImageIcon(foricon6_2);
	ImageIcon icon7 = new ImageIcon(foricon7_2);
	/*
	 * �̹��� ������ ����. icon1 : ���� ���� ������ , icon2 : ���� ���� �л� , icon3 : ���ߴ� �л�
	 * 			    icon4 : 'ũ��'�ϴ� ������, icon5 : ���ƺ��� ������, icon6 :'����'�ϴ� ������
	 * 				icon7 : �ɷ��� ���� �л�. 
	 */

	public TeaseTeacher(){	
		windowMaker();
		gameStart();
	}
	
	public void windowMaker() {	//JFrame�� â�� ���� ����.
		setSize(500, 800);
		setTitle("���� : 0");
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true);
		
		up = new Up(this);	//this�� TeaseTeacher�� ��ü�� Up�� �����ڿ� �Ѱ��ش�.
		down = new Down();

		panel = new JPanel();
		
		panel.setLayout(null);
		panel.add(label1);
		panel.add(label2);
		
		add(panel);
	}
	
	public void gameStart() {	
		//score�� 0���� �ʱ�ȭ���ְ� up�� down�� �ִ� run �޼��� ����.
		score = 0;
		
		up.start();
		down.start();
	}
	
	class Up extends Thread{ 
		private TeaseTeacher obj;	//��ü�� ��� ���� �׸�
		
		public Up(TeaseTeacher obj) {	//���� ��ü�� ������ ������� �׸��� ��� label1�� ���� �ʱ� ����.
			this.obj = obj;	
			
			label1 = new JLabel();
			label1.setSize(500 , 400);
			label1.setLocation(0 , 0);
			label1.setIcon(icon1);
		}
		
		boolean b = true;
		public void run() {
			music2();//¯�� ���� ���.
			r = new Random(); // Up�̹��� ��ȯ �ð� �������� �ϱ� ���� Random ��ü ����.

			while(b) {
					if(i%3 == 0) {
						original();
						i++;
							try {	
								Up.sleep(Math.abs(r.nextInt() % 1000) + 500);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
					}else if(i%3 == 1){
						ready();
						i++;
							try {			
								Up.sleep(400);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}	
					}else{
						change();
						i++; 
							if(label2.getIcon() == icon3) {	
								fail();
							}
								try {
									Up.sleep(Math.abs(r.nextInt() % 1000) + 500);
								} catch (InterruptedException e1) {
									System.out.println("�����尡 sleep ���� ����Ǿ����ϴ�.");
								}
						}
					}
				}
		
		public void original() {
			//label1�� �̹����� icon1�� �ٲ۴�.
			label1.setIcon(icon1);			
		}

		public void ready() {
			//label1�� �̹����� icon4�� �ٲ۴�.
			label1.setIcon(icon4);
		}
		
		public void change() {
			//label1�� �̹����� icon5�� �ٲ۴�.
			label1.setIcon(icon5);
		}
		
		public void fail() {

			label1.setIcon(icon6);
			label2.setIcon(icon7);
			
			timer1.stop();
			b = false;
			clip2.stop();
			music();
			new ScoreDialog(this.obj);
		}
		
		public void music() {
     	   try { 
     		   AudioInputStream stream = 
     				   AudioSystem.getAudioInputStream(new File("src/TeaseMusic/bgm.wav")); 
     		   clip = AudioSystem.getClip(); 
     		   clip.open( stream ); 
     		   clip.start();    
     	   } catch(Exception e) {
     		   e.printStackTrace(); 
     	   } 
		}
		
		public void music2() {
	 	   try { 
	 		   AudioInputStream stream = 
	 				   AudioSystem.getAudioInputStream(new File("src/TeaseMusic/bgm2.wav")); 
	 		   clip2 = AudioSystem.getClip(); 
	 		   clip2.open( stream ); 
	 		   clip2.start(); 	 		   
	 	   } catch(Exception e) { 
	 		   e.printStackTrace(); 
	 	   } 
		}
	}
	
	class Down extends Thread implements ActionListener{
		
		public Down() {	//label2�� ���� �ʱ� ����
			label2 = new JLabel();
			label2.setSize(500, 400);
			label2.setLocation(0 , 400);
			label2.setIcon(icon2);
			label2.requestFocus();
			label2.setFocusable(true);
			}
		
		public void run() {
			timer1 = new Timer(10 , this);	//������ �ø��� ���� Timer ��ü ����.
			
			label2.addKeyListener(new KeyListener() {
				
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER && label1.getIcon() != icon6) {
						if(label1.getIcon() != icon5) {
							timer1.start();
							label2.setIcon(icon3);
						}else{
							up.fail();
						}
					}
				}
				
				public void keyReleased(KeyEvent e) {		

					if(label1.getIcon() != icon6) {
						timer1.stop();
						label2.setIcon(icon2);
					}
				}
				
				public void keyTyped(KeyEvent e) {}
				
			});
		}
					
		public void actionPerformed(ActionEvent e) { 
			plusScore();
			writeScore();
		}
		
		public void plusScore() {
			//10�и������帶�� ���� 1 �� �߰�.
			score += 1;
		}
		
		public void writeScore() {
			//JFrame�� Ÿ��Ʋ�� ������ ǥ��.
			setTitle("���� : " + score);
		}
	}

	class ScoreDialog extends JDialog implements ActionListener{
		
		private static final long serialVersionUID = 1L;
		
		private TeaseTeacher obj;	//TeaseTeacher ��ü �ޱ� ���� �׸�
		 
		public ScoreDialog(TeaseTeacher obj) {
			this.obj = obj;	//TeaseTeacher�� ��ü�� �޾Ƽ� �׸��� ����.
			
			/*
			 * ScoreDialog�� ���� �ʱ⼳��.
			 */
			setSize(400 , 200);
			setTitle("���� Ȯ��");
			setLocation(50,290);
			
			scorepanel = new JPanel();
			scorepanel.setLayout(null);
			
			scorelabel = new JLabel("���� : " + score , SwingConstants.CENTER);
			scorelabel.setSize(400 , 100);
			scorelabel.setLocation(0 , 0);
			scorelabel.setFont(new Font("Serif", Font.BOLD, 30));
			restartbutton = new JButton("�ٽ� �ϱ�");
			restartbutton.setSize(200 , 61);
			restartbutton.setLocation(0 , 100);
			restartbutton.addActionListener(this);
			close = new JButton("�ݱ�");
			close.setSize(200 , 61);
			close.setLocation(200 , 100);
			close.addActionListener(this);
			
			scorepanel.add(scorelabel);
			scorepanel.add(restartbutton);
			scorepanel.add(close);
			
			add(scorepanel);
			setVisible(true);
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == restartbutton) {
				new TeaseTeacher(); //TeaseTeacher�� �����ڸ� �ٽ� ȣ���� ������ ���� ����.
				clip.stop();	//���� ���� �� ������ ���� ����.
				obj.up.interrupt();	//Up�� ������ ����
				obj.down.interrupt();	//Down�� ������ ����
				obj.dispose();			//�� ���� â�� ����.
			}else if(e.getSource() == close) {
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] args) {
		
		new StartGame();	//StartGame ȣ��.
	
	}
}