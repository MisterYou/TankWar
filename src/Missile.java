import java.awt.*;


public class Missile {
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;
	
	public static final int WIDTH = 10;
	public static final int HEIGTH = 10;
	
	int x,y;
	Tank.Direction dir;
	
	private boolean live = true;
	private TankClient tc;
	
	
	public boolean isLive() {
		return live;
	}
	public Missile(int x, int y, Tank.Direction dir) {//子弹位置方向
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public Missile(int x,int y,Tank.Direction dir,TankClient tc){
		this(x,y,dir);
		this.tc = tc;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.black);
		g.fillOval(x, y, WIDTH, HEIGTH);
		g.setColor(c);
		
		move();
	}
	private void move() {
		switch(dir){//控制上下左右，斜。
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
		
		}
		if(x < 0 || y < 0 || x > TankClient.GAME_WIDTR || y > TankClient.GAME_HEIGHT){
			live = false;
			tc.missiles.remove(this);//销毁
		}
		
	}
}
