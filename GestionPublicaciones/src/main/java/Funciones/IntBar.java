package Funciones;
import javax.swing.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class IntBar extends JScrollBar implements AdjustmentListener {
    private JTextArea textArea;

    public IntBar(JTextArea textArea) {
        this.textArea = textArea;
        this.addAdjustmentListener(this);
    }
    
}