import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame{
	
	public static final int GAME_WIDTR = 800;
	public static final int GAME_HEIGHT = 600;
	
	//int x = 50,y = 50;//����λ�õ�ֵ
	Tank myTank = new Tank(50,50);
	Missile m = new Missile(50, 50, Tank.Direction.R);
	
	
	Image offScreenImage = null;//�����ͼƬ
	
	public void paint(Graphics g){ 
		m.draw(g);
		myTank.draw(g);
	}
	
	public void update(Graphics g) {
		if(offScreenImage == null){
			offScreenImage = this.createImage(GAME_WIDTR,GAME_HEIGHT);//����һ��ͼƬ
		}
		Graphics gOffScreen = offScreenImage.getGraphics(); //ͼƬ�ϵĻ���
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);//�����µ���ɫ
		gOffScreen.fillRect(0, 0, GAME_WIDTR, GAME_HEIGHT);//��һ��ʵ�ĵķ���
		gOffScreen.setColor(c);//�ػ�һ�Σ�������ˢһ��
		paint(gOffScreen);//������ͼƬ
		g.drawImage(offScreenImage, 0, 0, null); 
	}
	
	public void lunchFrame(){
		this.setLocation(200,100);//����λ��
		this.setSize(GAME_WIDTR,GAME_HEIGHT);//���ó���
		this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});//�رմ��ڣ��¼�����
		this.setResizable(false);//��ֹ�ı��С
		this.setBackground(Color.GREEN);//���ñ�����ɫ
		
		this.addKeyListener(new KeyMonitor());//��������
		setVisible(true);
		new Thread(new PaintThread()).start();
	}

	public static void main(String[] args) {
        TankClient tc = new TankClient();
        tc.lunchFrame();
	}
	
	private class PaintThread implements Runnable{//��һ���̲߳����ػ�
		public void run(){
			while(true){
				repaint();//�ػ�������paint�ػ�
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
	}

	private class KeyMonitor extends KeyAdapter{
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);//�ɿ���
		}

		public void keyPressed(KeyEvent e) {
			myTank.keyPresessed(e);//���¼�
		}
		
	}
}


















