/*
 * Main for the Smart Mirror
 * 
 * 
 */

/**
 *
 * @author Chris Kazenske
 */

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class SmartMirror implements Runnable {
    
    public final static int minBetweenCalls = 5;
    int count = 1;
    public static JFrame f;
    
    /**
     * @param args the command line arguments
     */
    public SmartMirror() {
        
    }
    
    public void run(){
        while(true){
            try {
                Display d = new Display(new WeatherData(), new NewsData(count), f);
                f = d.getJFrame();
                f.setVisible(true);
                if (count < 4){
                    count++;
                }
                else{
                    count = 0;
                }
                Thread.sleep(SmartMirror.minBetweenCalls*60000);  
            }
            catch (InterruptedException e1) {
                System.out.println("Error. Couldn't sleep."); 
                System.exit(0);
            }
        }
    }
    public static void main(String[] args) {
        f = new JFrame();
        f.setUndecorated(true); //change to true when done
        new Thread(new SmartMirror()).start();   
    
    KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
    Action escapeAction = new AbstractAction() {
    // close the frame when the user presses escape
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);  
    }}; 
    f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
    f.getRootPane().getActionMap().put("ESCAPE", escapeAction);
    }
}
