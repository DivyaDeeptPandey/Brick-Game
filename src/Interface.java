import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Interface {
   static Gameplay game=new Gameplay();
   static boolean pause=false;
   static int z;
   public static void main(String ar[]) {
	   JFrame frame=new JFrame();
	   frame.setBounds(350,100, 600,500);
	   frame.add(game);
	   frame.setVisible(true);
	   frame.setResizable(false);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   frame.addKeyListener(new KeyAdapter() {
		   public void keyPressed(KeyEvent e) {
			   if(e.getKeyCode()==10) {
				   Gameplay.BallMovY=-Gameplay.BallMovY;
				   Gameplay.play=true;
				   Gameplay.totBrick=21;
				   for (int i=0; i<6; i++){ 
			              for (int j=0; j<=i; j++ ){ 
			                  BrickPattern1.pattern[i][j]=1;
			              } 
			          } 
				   game.repaint();
				   synchronized (Gameplay.ballThread) {
					Gameplay.ballThread.notifyAll();
				 }
				   synchronized (Gameplay.timeThread) {
						Gameplay.timeThread.notifyAll();
					 }
			   }
			   
			   if(e.getKeyCode()==32) {
				   try {
				   Gameplay.play=true;
				   Gameplay.ballThread.start();
				   Gameplay.timeThread.start();
				   }catch(Exception e2) {
					   JOptionPane.showMessageDialog(null, e2.getMessage());
				   }
			   }
			   if(e.getKeyCode()==39) {
				   new PaddleMovRight();
				   game.repaint();
			   }
			   else if(e.getKeyCode()==37) {
				   new PaddleMovLeft();
				   game.repaint();
			   }
		   }
		   public void keyReleased(KeyEvent e) {
			   if(e.getKeyCode()==39) {
				   PaddleMovRight.stop();
			   }
			   else if(e.getKeyCode()==37) {
				   PaddleMovLeft.stop();
			   }
		   }
	});
	   
   }
}
