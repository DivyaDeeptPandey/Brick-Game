import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class BrickPattern1 {
      public static int pattern[][];
      public int brickWidth;
      public int brickHeight;
      public BrickPattern1() {
    	  pattern=new int[6][6];
    	  for (int i=0; i<6; i++){ 
              for (int j=0; j<=i; j++ ){ 
                  pattern[i][j]=1;
              } 
          } 
    	  brickWidth=200/5;
    	  brickHeight=150/5;
      }
      public void drawBrick(int value,int r,int c) {
    	  pattern[r][c]=value;
      }
      public void paint(Graphics2D g) {
    	  for (int i=0; i<6; i++){ 
              for (int j=0; j<6; j++ ){ 
    			  if(pattern[i][j]>0) {
    				  g.setColor(new Color(250,70,66));
    				  g.fill3DRect(j*brickWidth+220,i*brickHeight+80,brickWidth, brickHeight ,true);
    				  g.setColor(new Color(190,243,112));
    				  g.setStroke(new BasicStroke(1));
    				  g.drawRect(j*brickWidth+220,i*brickHeight+80,brickWidth, brickHeight );
    				  g.setColor(Color.BLACK);
    				  g.setStroke(new BasicStroke(2));
    				  g.drawRect(j*brickWidth+218,i*brickHeight+78,brickWidth+4, brickHeight+4 );
    			  }
    		  }
    	  }
      }
}
