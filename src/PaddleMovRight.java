import javax.swing.JOptionPane;

public class PaddleMovRight implements Runnable {
        private static boolean play;
        Thread t1;
	    PaddleMovRight() {
        	play=true;
        	t1=new Thread(this);
        	t1.start();
        }
	    public void run() {
	    	try {
	    	  while(play && Gameplay.PaddleX<=487) {
	    	     Gameplay.PaddleX+=Gameplay.PaddleMovSpeed;
	    	     if(!Gameplay.play)
	    	     Gameplay.BallX+=Gameplay.PaddleMovSpeed;
	    	     Thread.sleep(1000/Gameplay.PaddleMovSpeed);
	    	    }
	    	  }catch(Exception e) {
	    		  JOptionPane.showMessageDialog(null,e.getMessage());
	    	  }
	    }
	    public static void stop() {
	    	play=false;
	    }
}
