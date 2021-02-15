import javax.swing.JOptionPane;

public class PaddleMovLeft implements Runnable {
	private static boolean play;
    Thread t2;
    PaddleMovLeft() {
    	play=true;
    	t2=new Thread(this);
    	t2.start();
    }
    public void run() {
    	try {
    	  while(play && Gameplay.PaddleX>=9) {
    	     Gameplay.PaddleX-=Gameplay.PaddleMovSpeed;
    	     if(!Gameplay.play)
    	     Gameplay.BallX-=Gameplay.PaddleMovSpeed;
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
