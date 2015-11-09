import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame{
	
	public static final int GAME_WIDTR = 800;
	public static final int GAME_HEIGHT = 600;
	
	//int x = 50,y = 50;//设置位置的值
	Tank myTank = new Tank(50,50);
	Missile m = new Missile(50, 50, Tank.Direction.R);
	
	
	Image offScreenImage = null;//虚拟的图片
	
	public void paint(Graphics g){ 
		m.draw(g);
		myTank.draw(g);
	}
	
	public void update(Graphics g) {
		if(offScreenImage == null){
			offScreenImage = this.createImage(GAME_WIDTR,GAME_HEIGHT);//创建一张图片
		}
		Graphics gOffScreen = offScreenImage.getGraphics(); //图片上的画笔
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);//设置新的颜色
		gOffScreen.fillRect(0, 0, GAME_WIDTR, GAME_HEIGHT);//填一个实心的方块
		gOffScreen.setColor(c);//重画一次，背景就刷一次
		paint(gOffScreen);//画背后图片
		g.drawImage(offScreenImage, 0, 0, null); 
	}
	
	public void lunchFrame(){
		this.setLocation(200,100);//设置位置
		this.setSize(GAME_WIDTR,GAME_HEIGHT);//设置长宽
		this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});//关闭窗口，事件监听
		this.setResizable(false);//禁止改变大小
		this.setBackground(Color.GREEN);//设置背景颜色
		
		this.addKeyListener(new KeyMonitor());//监听键盘
		setVisible(true);
		new Thread(new PaintThread()).start();
	}

	public static void main(String[] args) {
        TankClient tc = new TankClient();
        tc.lunchFrame();
	}
	
	private class PaintThread implements Runnable{//用一个线程不断重画
		public void run(){
			while(true){
				repaint();//重画，调用paint重画
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
			myTank.keyReleased(e);//松开键
		}

		public void keyPressed(KeyEvent e) {
			myTank.keyPresessed(e);//按下键
		}
		
	}
}


















