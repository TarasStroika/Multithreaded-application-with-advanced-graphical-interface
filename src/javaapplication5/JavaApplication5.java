package javaapplication5;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaApplication5 {
    
    public static class MyThread extends Thread {
    public int start;
    public int end;
    public BigInteger result;
    public volatile boolean running;
    public volatile boolean stopped;
    public volatile boolean cancelled;
    private static final int SLEEP_TIME=17;
    
    @Override
    public void run(){
        running = true;
        stopped = false;
        cancelled=false;
        int current;
        result=BigInteger.valueOf(0);
        synchronized(myFrame){
           start=myFrame.getNewStart();
           end=myFrame.getNewEnd();
        }
        current=start;
        while (current>0){
            while (current<end){
                if (!stopped){
                    result=result.add(BigInteger.valueOf(current));
                    current++;
                }
                if (cancelled) return;
                try{
                    Thread.sleep(SLEEP_TIME);
                }
                catch (InterruptedException qq) {
                    qq.printStackTrace();
                }
            }
            synchronized(myFrame){
                myFrame.update();
                start=myFrame.getNewStart();
                end=myFrame.getNewEnd();
            }
            current=start;
        }
        running = false;
    }

}
    
    private static NewJFrame1 myFrame;
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        myFrame = new NewJFrame1();
        myFrame.setVisible(true);
    }
    
}
