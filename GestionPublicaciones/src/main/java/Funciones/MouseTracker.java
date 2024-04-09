package Funciones;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;


public class MouseTracker {
    private JLabel label;
    public MouseTracker(JLabel label) {
        this.label = label;
    }
    
    public void mouseMoved(MouseEvent e) {
        label.setText("Mouse position: (" + e.getX() + ", " + e.getY() + ")");
    }

}
