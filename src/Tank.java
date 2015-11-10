import java.awt.*;
import java.awt.event.*;

import javax.naming.directory.DirContext;


public class Tank {
	public static final int XSPEED = 5;
	public static final int YSPEED = 5;
	
	public static final int WIDTH = 30;
	public static final int HEIGTH = 30;
	
	
	TankClient tc;
	private int x ,y;
	
	private boolean bL = false,bU = false,bR = false,bD = false;
	enum Direction{L,LU,U,RU,R,RD,D,LD,STOP};//�˸�����
	
	private Direction dir = Direction.STOP;
	private Direction ptDir = Direction.D;
	
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Tank(int x,int y,TankClient tc){
		this(x,y);
		this.tc = tc;
	}

	public void draw(Graphics g){
		Color c = g.getColor();//�Ȱ�ǰ��ɫ�ó���
		g.setColor(Color.RED);//������ɫ
		g.fillOval(x, y, WIDTH, HEIGTH);//��һ��Բ
		g.setColor(c);//��ǰ��ɫ��ԭ
		switch(ptDir){//�����������ң�б��
		case L:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGTH/2, x, y + Tank.HEIGTH/2);
			break;
		case LU:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGTH/2, x, y);
			break;
		case U:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGTH/2, x + Tank.WIDTH/2, y );
			break;
		case RU:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGTH/2, x + Tank.WIDTH, y );
			break;
		case R:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGTH/2, x + Tank.WIDTH, y + Tank.HEIGTH/2);	
			break;
		case RD:	
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGTH/2, x + Tank.WIDTH, y + Tank.HEIGTH);
			break;
		case D:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGTH/2, x + Tank.WIDTH/2, y + Tank.HEIGTH);
			break;
		case LD:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGTH/2, x, y + Tank.HEIGTH);
			break;
		}
		move();
	}
	
	void move(){
		switch(dir){//�����������ң�б��
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;		
			break;
		case RD:	
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += XSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP:
			break;
		
		}
		if(this.dir != Direction.STOP){
			this.ptDir = this.dir;
		}
		
	}
	
	public void keyPresessed(KeyEvent e){
		int key = e.getKeyCode();//��ȡ�������µ�ֵ
		switch(key){

		
		case KeyEvent.VK_LEFT:
			bL = true;
			break;
		case KeyEvent.VK_UP:
			bU = true;
			break;
		case KeyEvent.VK_RIGHT:
			bR = true;
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
			break;
		}
		locateDirection();
	}
	
	void locateDirection(){
		if(bL && !bU && !bR && !bD) dir = Direction.L;
		else if(bL && bU && !bR && !bD) dir = Direction.LU;
		else if(!bL && bU && !bR && !bD) dir = Direction.U;
		else if(!bL && bU && bR && !bD) dir = Direction.RU;
		else if(!bL && !bU && bR && !bD) dir = Direction.R;
		else if(!bL && !bU && bR && bD) dir = Direction.RD;
		else if(!bL && !bU && !bR && bD) dir = Direction.D;
		else if(bL && !bU && !bR && bD) dir = Direction.LD;
		else if(!bL && !bU && !bR && !bD) dir = Direction.STOP;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();//��ȡ�����ɿ���ֵ
		switch(key){
		case KeyEvent.VK_CONTROL://ctrl����
			//tc.m = fire();
		//	tc.missiles.add(fire());//���ڵ���ӵ�������
			fire();
			break;
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
		case KeyEvent.VK_UP:
			bU = false;
			break;
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		case KeyEvent.VK_DOWN:
			bD = false;
			break;
		}
		locateDirection();
		
	}
	
	public Missile fire(){
		int x = this.x +Tank.WIDTH/2 - Missile.WIDTH/2;//���÷���λ��
		int y = this.y +Tank.HEIGTH/2 - Missile.HEIGTH/2;
		Missile m = new Missile(x, y, ptDir,this.tc);//����
		tc.missiles.add(m);
		return m;
	}
	
}
