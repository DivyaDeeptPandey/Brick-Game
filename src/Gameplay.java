import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Scanner;

import javax.swing.JPanel;

public class Gameplay extends JPanel{ 
	public static boolean play;
	public static int BallX=290;
	public static int BallRad=20;
	public static int BallY=435;
	public static int BallMovX=3;
	public static int BallMovY=3;
	public static int totBrick=21;
	public static int BallUpdateRate=100;
	public static Thread ballThread;
	public int time;
	public static Thread timeThread;
	public static int PaddleX=250;
	public static int PaddleMovSpeed=5;
	public static int score;
	private BrickPattern1 p1; 
	
	public Gameplay() {
		play=false;
		p1=new BrickPattern1();
		ballThread = new Thread (new Runnable() {
	         public void run() {
	            while (play) {
	               // Ball Movement
	               BallX -= BallMovX;
	               BallY -= BallMovY;
	               // Check if the ball moves over the bounds
	               if (BallX - BallRad +15< 0 ) {
	                  BallMovX = -BallMovX; // Reflect along normal
	                  BallX = BallRad; // Re-position the ball at the edge
	               } else if (BallX + BallRad > 590) {
	                  BallMovX = -BallMovX;
	                  BallX = 575 - BallRad;
	               }
	               if (BallY - BallRad+15 < 0) {
	                  BallMovY = -BallMovY;
	                  BallY= BallRad;
	               } 
	               Rectangle ballRect=new Rectangle(BallX,BallY,20,20);
	               if(ballRect.intersects(new Rectangle(PaddleX,455,100,15))) {
	            	   BallMovY = -BallMovY; 
	               }
	               if(BallY>500) {
	            	   synchronized (ballThread) {
							try {
								repaint();
								ballThread.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
	               }
	               if(totBrick==0 ) {
	            	   synchronized (ballThread) {
							try {
								repaint();
								ballThread.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
	               }
	               
	               A: for (int i=0; i<6; i++){ 
	                   for (int j=0; j<=i; j++ ){ 
	            		   if(p1.pattern[i][j]>0) {
	            			   Rectangle br=new Rectangle(j*p1.brickWidth+220,i*p1.brickHeight+80,p1.brickWidth,p1.brickHeight);
	            			   if(ballRect.intersects(br)) {
	            				   p1.drawBrick(0, i, j);
	            				   totBrick--;
	            				   score+=10;
	            				   if((BallX+BallRad-1)<=br.x || BallX+BallRad>=br.x+p1.brickWidth) {
	            					   BallMovX= -BallMovX;
	            				
	            				   }
	            				   else{
	            					   BallMovY= -BallMovY;
	            				   }
	            				   break A;
	            			      }
	            			   
	            		    }
	            		   }
	                 }
	               // Refresh the display
	               repaint(); // Callback paintComponent()
	               // Delay for timing control and give other threads a chance
	               try {
	                  Thread.sleep(1000/ BallUpdateRate);  // milliseconds
	               } catch (InterruptedException ex) { }
	            }
	         }
	      });
		timeThread =new Thread(new Runnable(){
			public void run() {
				while(true) {
					if(!play) {
						try {
							synchronized (timeThread) {
								timeThread.wait();
							}
						}catch(Exception e) {}
					}
					time++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
    
	public void paint (Graphics g) {
    	//painting background
    	g.setColor(new Color(190,243,112));
    	g.fillRect(5,5,585,490);
    	
    	//painting border
    	g.setColor(Color.black);
    	g.fillRect(0,0,5,500);
    	g.fillRect(0,0,600,5);
    	g.fillRect(591,0,5,500);
    	
    	//painting paddle
    	g.setColor(new Color(255,128,0));
		g.fillRect(PaddleX-2,453,104,19);
		g.setColor(new Color(64,0,128));
		g.fillRect(PaddleX,455,100,15);
		
		//[painting ball
		g.setColor(new Color(255,128,0));
		g.fillOval(BallX,BallY,BallRad,BallRad);
		
		//painting bricks
		p1.paint((Graphics2D)g);
		
		//painting score
		g.setColor(new Color(128,64,0));
		g.setFont(new Font("Britannic Bold",0,20));
		g.drawString("SCORE:"+score,480, 30);
		
		//painting timer
		g.setColor(new Color(128,64,0));
		g.setFont(new Font("Britannic Bold",0,20));
		g.drawString("TIME:"+time,30, 30);
		
		if(BallY>500) {
			Gameplay.BallX=Gameplay.PaddleX+40;
			Gameplay.BallY=435;
			g.setColor(new Color(255,128,0));
			g.fillOval(BallX,BallY,BallRad,BallRad);
			g.setColor(new Color(209,61,87));
            g.setFont(new Font("Britannic Bold",0,40));
            g.drawString("YOU LOOSE!",210,300);
            g.setFont(new Font("Britannic Bold",0,20));
            g.drawString("TOTAL SCORE:"+time*score,230,320);
            g.drawString("Press Enter to play again",200,340);
            time=0;
			score=0;
           }
		
		
        if(totBrick==0) {
        	Gameplay.BallX=Gameplay.PaddleX+40;
			Gameplay.BallY=435;
			g.setColor(new Color(105,220,88));
        	g.setFont(new Font("Britannic Bold",0,40));
        	g.drawString("YOU WON!",210,300);
        	g.setFont(new Font("Britannic Bold",0,20));
        	g.drawString("TOTAL SCORE:"+time*score,200,320);
        	g.drawString("Press Enter to play again",190,340);
        	time=0;
        	score=0;
        }
		
	
	}
    
}
