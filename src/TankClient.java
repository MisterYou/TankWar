import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame{
	
	public void lunchFrame(){
		this.setLocation(400,300);
		this.setSize(800,600);
		this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});//�رմ��ڣ��¼�����
		this.setResizable(false);//��ֹ�ı��С
		setVisible(true);
	}

	public static void main(String[] args) {
        TankClient tc = new TankClient();
        tc.lunchFrame();

	}

}
