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
 * setialVersionUID란 객체를 파일에 쓰거나 전송할 떄 직렬화 하기 위해 직렬화에 사용할
 * 고유 아이디를 지정해주는 것. 선언하지 않아도 JVM에서 자동으로 생성해주지만 직접 선언하는게
 * 권장.
 */

public class TeaseTeacher extends JFrame{

	private static final long serialVersionUID = 1L;
	Up up;	//Up 내부클래스 변수.
	Down down;	//Down 내부클래스 변수.
	
	JLabel label1, label2, scorelabel;	//label1 위의 이미지 붙임. label2 밑의 이미지 붙임. scorelabel 점수 표시.
	JButton restartbutton, close;	//ScoreDialog의 다시하기, 닫기 버튼.
	JPanel panel, scorepanel;	//panel 게임창의 패널, scorepanel ScoreDialog의 패널.
	
	Random 	r;	//위의 이미지 변환하는 시간 랜덤으로 하기 위한 Random 클래스 변수.
	Clip clip , clip2;	//음악을 넣기 위한 Clip 클래스 변수들. clip은 게임 끝나면 나올 음악, clip2는 게임 중 나올 음악.
	Timer timer1;	//점수 표시를 위한 Timer 클래스 변수.
	
	static int score;	//점수
	static int i = 3;		//사진이 순서대로 돌아가기 위해 필요한 변수.

	
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
	 * 이미지 아이콘 생성. icon1 : 수업 중인 선생님 , icon2 : 수업 중인 학생 , icon3 : 춤추는 학생
	 * 			    icon4 : '크흠'하는 선생님, icon5 : 돌아보는 선생님, icon6 :'나와'하는 선생님
	 * 				icon7 : 걸려서 놀라는 학생. 
	 */

	public TeaseTeacher(){	
		windowMaker();
		gameStart();
	}
	
	public void windowMaker() {	//JFrame의 창에 대한 설정.
		setSize(500, 800);
		setTitle("점수 : 0");
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true);
		
		up = new Up(this);	//this로 TeaseTeacher의 객체를 Up의 생성자에 넘겨준다.
		down = new Down();

		panel = new JPanel();
		
		panel.setLayout(null);
		panel.add(label1);
		panel.add(label2);
		
		add(panel);
	}
	
	public void gameStart() {	
		//score을 0으로 초기화해주고 up과 down에 있는 run 메서드 실행.
		score = 0;
		
		up.start();
		down.start();
	}
	
	class Up extends Thread{ 
		private TeaseTeacher obj;	//객체를 담기 위한 그릇
		
		public Up(TeaseTeacher obj) {	//받은 객체를 위에서 만들어준 그릇에 담고 label1에 대한 초기 설정.
			this.obj = obj;	
			
			label1 = new JLabel();
			label1.setSize(500 , 400);
			label1.setLocation(0 , 0);
			label1.setIcon(icon1);
		}
		
		boolean b = true;
		public void run() {
			music2();//짱구 음악 재생.
			r = new Random(); // Up이미지 변환 시간 랜덤으로 하기 위해 Random 객체 생성.

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
									System.out.println("스레드가 sleep 도중 종료되었습니다.");
								}
						}
					}
				}
		
		public void original() {
			//label1의 이미지를 icon1로 바꾼다.
			label1.setIcon(icon1);			
		}

		public void ready() {
			//label1의 이미지를 icon4로 바꾼다.
			label1.setIcon(icon4);
		}
		
		public void change() {
			//label1의 이미지를 icon5로 바꾼다.
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
		
		public Down() {	//label2에 대한 초기 설정
			label2 = new JLabel();
			label2.setSize(500, 400);
			label2.setLocation(0 , 400);
			label2.setIcon(icon2);
			label2.requestFocus();
			label2.setFocusable(true);
			}
		
		public void run() {
			timer1 = new Timer(10 , this);	//점수를 올리기 위한 Timer 객체 생성.
			
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
			//10밀리세컨드마다 점수 1 씩 추가.
			score += 1;
		}
		
		public void writeScore() {
			//JFrame의 타이틀에 점수를 표시.
			setTitle("점수 : " + score);
		}
	}

	class ScoreDialog extends JDialog implements ActionListener{
		
		private static final long serialVersionUID = 1L;
		
		private TeaseTeacher obj;	//TeaseTeacher 객체 받기 위한 그릇
		 
		public ScoreDialog(TeaseTeacher obj) {
			this.obj = obj;	//TeaseTeacher의 객체를 받아서 그릇에 저장.
			
			/*
			 * ScoreDialog에 대한 초기설정.
			 */
			setSize(400 , 200);
			setTitle("점수 확인");
			setLocation(50,290);
			
			scorepanel = new JPanel();
			scorepanel.setLayout(null);
			
			scorelabel = new JLabel("점수 : " + score , SwingConstants.CENTER);
			scorelabel.setSize(400 , 100);
			scorelabel.setLocation(0 , 0);
			scorelabel.setFont(new Font("Serif", Font.BOLD, 30));
			restartbutton = new JButton("다시 하기");
			restartbutton.setSize(200 , 61);
			restartbutton.setLocation(0 , 100);
			restartbutton.addActionListener(this);
			close = new JButton("닫기");
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
				new TeaseTeacher(); //TeaseTeacher의 생성자를 다시 호출해 게임을 새로 시작.
				clip.stop();	//게임 실패 시 나오던 음악 종료.
				obj.up.interrupt();	//Up의 쓰레드 중지
				obj.down.interrupt();	//Down의 쓰레드 중지
				obj.dispose();			//전 판의 창을 꺼줌.
			}else if(e.getSource() == close) {
				System.exit(0);
			}
		}
	}
	
	public static void main(String[] args) {
		
		new StartGame();	//StartGame 호출.
	
	}
}