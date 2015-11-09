import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame{
	
	public static final int GAME_WIDTR = 800;
	public static final int GAME_HEIGHT = 600;
	
	int x = 50,y = 50;//����λ�õ�ֵ
	
	Image offScreenImage = null;//�����ͼƬ
	
	public void paint(Graphics g){
		Color c = g.getColor();//�Ȱ�ǰ��ɫ�ó���
		g.setColor(Color.RED);//������ɫ
		g.fillOval(x, y, 30, 30);//��һ��Բ
		g.setColor(c);//��ǰ��ɫ��ԭ
		y += 5;
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

}
